/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;
import io.fusionauth.domain.jwt.RefreshToken;

/**
 * Models the Refresh Token Revoke Event. This event might be for a single token, a user
 * or an entire application.
 *
 * @author Brian Pontarelli
 */
public class JWTRefreshTokenRevokeEvent extends BaseEvent implements Buildable<JWTRefreshTokenRevokeEvent> {
  public UUID applicationId;

  public Map<UUID, Integer> applicationTimeToLiveInSeconds = new TreeMap<>();

  public RefreshToken refreshToken;

  public User user;

  public UUID userId;

  @JacksonConstructor
  public JWTRefreshTokenRevokeEvent() {
  }

  public JWTRefreshTokenRevokeEvent(EventInfo info, User user, UUID applicationId, int timeToLiveInSeconds) {
    super(info);
    this.applicationId = applicationId;
    this.applicationTimeToLiveInSeconds.put(applicationId, timeToLiveInSeconds);
    this.user = user;
    this.userId = user == null ? null : user.id;
  }

  public JWTRefreshTokenRevokeEvent(EventInfo info, User user, Map<UUID, Integer> applicationTimeToLiveInSeconds) {
    super(info);
    this.applicationTimeToLiveInSeconds.putAll(applicationTimeToLiveInSeconds);
    this.user = user;
    this.userId = user == null ? null : user.id;
  }

  public List<UUID> applicationIds() {
    return new ArrayList<>(applicationTimeToLiveInSeconds.keySet());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JWTRefreshTokenRevokeEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    JWTRefreshTokenRevokeEvent that = (JWTRefreshTokenRevokeEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(applicationTimeToLiveInSeconds, that.applicationTimeToLiveInSeconds) &&
           Objects.equals(user, that.user) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public EventType getType() {
    return EventType.JWTRefreshTokenRevoke;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, applicationTimeToLiveInSeconds, user, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
