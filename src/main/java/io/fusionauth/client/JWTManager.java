/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.primeframework.jwt.domain.JWT;

/**
 * A singleton cache of JWTs that have been revoked. This should be connected via a Webhook that listens for the
 * <code>jwt.refresh-token.revoke</code> event from FusionAuth.
 *
 * @author Brian Pontarelli
 */
public class JWTManager {
  private static final ScheduledThreadPoolExecutor executorService;

  private static final Map<UUID, ZonedDateTime> revokedJWTs = new ConcurrentHashMap<>();

  /**
   * Determines if a given JWT object is valid or not. This checks if the subject in the JWT is in the list of revoked
   * subjects (user ids) and if the expiration of the JWT is before the expiration in the Map of revoked JWTs.
   *
   * @param jwt The JWT to check.
   * @return True if the JWT is valid, false it not.
   */
  public static boolean isValid(JWT jwt) {
    UUID userId = UUID.fromString(jwt.subject);
    ZonedDateTime expiration = revokedJWTs.get(userId);
    return expiration == null || expiration.isBefore(jwt.expiration);
  }

  /**
   * Makes a user as having their JWTs revoked for the given number of seconds starting at the instant this method is
   * called. This manages the duration by allowing all JWTs whose expiration instant is after <code>now +
   * durationSeconds</code>.
   *
   * @param userId          The id of the user to revoke their JWTs
   * @param durationSeconds The duration of the JWTs in seconds. This value should come from FusionAuth based on the
   *                        application the JWT is for.
   */
  public static void revoke(UUID userId, int durationSeconds) {
    ZonedDateTime expiration = ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(durationSeconds);
    revokedJWTs.put(userId, expiration);
  }

  static {
    executorService = new ScheduledThreadPoolExecutor(1, r -> {
      Thread t = new Thread(r);
      t.setName("JWTManager Thread");
      t.setDaemon(true);
      return t;
    });

    executorService.schedule(() -> revokedJWTs.entrySet().removeIf(e -> e.getValue().isBefore(ZonedDateTime.now(ZoneOffset.UTC))), 7, TimeUnit.SECONDS);
  }
}
