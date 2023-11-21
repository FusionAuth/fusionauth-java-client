/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.fusionauth.jwt.domain.JWT;

/**
 * A singleton cache of JWTs that have been revoked. This should be connected via a Webhook that listens for the
 * <code>jwt.refresh-token.revoke</code> event from FusionAuth.
 *
 * @author Brian Pontarelli
 */
public class JWTManager {
  private static final ScheduledThreadPoolExecutor executorService;

  // Application Id -> Revocation Context
  // - Used to revoke Refresh Tokens by Application
  private static final Map<UUID, ZonedDateTime> revokedByApplication = new ConcurrentHashMap<>();

  // User Id -> Revocation Context
  // - Used to revoke Refresh Tokens by Application
  private static final Map<UUID, Map<UUID, ZonedDateTime>> revokedByUser = new ConcurrentHashMap<>();

  // Refresh Token Id -> Expiration
  // - Used to revoke individual Refresh Tokens
  private static final Map<UUID, ZonedDateTime> revokedRefreshTokens = new ConcurrentHashMap<>();

  /**
   * Determines if a given JWT object is valid or not. This checks if the subject in the JWT is in the list of revoked
   * subjects (user ids) and if the expiration of the JWT is before the expiration in the Map of revoked JWTs.
   *
   * @param jwt The JWT to check.
   * @return True if the JWT is valid, false it not.
   */
  public static boolean isValid(JWT jwt) {
    boolean result;

    // 1. Look for revoked Refresh Tokens by Refresh Token Id
    try {
      String refreshTokenId = jwt.getString("sid");
      if (refreshTokenId != null) {
        ZonedDateTime expiration = revokedRefreshTokens.get(UUID.fromString(refreshTokenId));
        if (expiration != null) {
          result = expiration.isBefore(jwt.expiration);
          if (!result) {
            return false;
          }
        }
      }
    } catch (Exception ignore) {
    }

    // 2. Look for revoked Refresh Tokens by Application
    try {
      String applicationId = jwt.getString("applicationId");
      if (applicationId != null) {
        ZonedDateTime expiration = revokedByApplication.get(UUID.fromString(applicationId));
        if (expiration != null) {
          result = expiration.isBefore(jwt.expiration);
          if (!result) {
            return false;
          }
        }

        // 3. Look for revoked Refresh Tokens by User Id
        String userId = jwt.subject;
        if (userId != null) {
          Map<UUID, ZonedDateTime> context = revokedByUser.get(UUID.fromString(userId));
          if (context != null) {
            expiration = context.get(UUID.fromString(applicationId));
            if (expiration != null) {
              result = expiration.isBefore(jwt.expiration);
              if (!result) {
                return false;
              }
            }
          }
        }
      }

    } catch (Exception ignore) {
    }

    return true;
  }

  public static void reset() {
    revokedByApplication.clear();
    revokedByUser.clear();
    revokedRefreshTokens.clear();
  }

  public static void revokeByApplication(UUID applicationId, int durationsInSeconds) {
    ZonedDateTime expiration = ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(durationsInSeconds);
    revokedByApplication.put(applicationId, expiration);
  }

  public static void revokeByRefreshToken(UUID refreshTokenId, int durationInSeconds) {
    ZonedDateTime expiration = ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(durationInSeconds);
    revokedRefreshTokens.put(refreshTokenId, expiration);
  }

  public static void revokedByUser(UUID userId, Map<UUID, Integer> durationsInSeconds) {
    Map<UUID, ZonedDateTime> context = revokedByUser.computeIfAbsent(userId, key -> new HashMap<>());
    for (UUID applicationId : durationsInSeconds.keySet()) {
      ZonedDateTime expiration = ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(durationsInSeconds.get(applicationId));
      context.put(applicationId, expiration);
    }
  }

  static {
    executorService = new ScheduledThreadPoolExecutor(1, r -> {
      Thread t = new Thread(r);
      t.setName("JWTManager Thread");
      t.setDaemon(true);
      return t;
    });


    executorService.schedule(() -> {
      ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
      revokedByUser.values().forEach(m -> m.entrySet().removeIf(e -> e.getValue().isBefore(now)));
      revokedByApplication.entrySet().removeIf(e -> e.getValue().isBefore(now));
      revokedRefreshTokens.entrySet().removeIf(e -> e.getValue().isBefore(now));
    }, 7, TimeUnit.SECONDS);
  }
}
