/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
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

/**
 * Models the Refresh Token Revoke Event (and can be converted to JSON). This event might be for a single token, a user
 * or an entire application.
 *
 * @author Brian Pontarelli
 */
public class JWTRefreshTokenRevokeEvent extends BaseEvent implements Buildable<JWTRefreshTokenRevokeEvent>, ApplicationEvent {
  public UUID applicationId;

  public Map<UUID, Integer> applicationTimeToLiveInSeconds = new TreeMap<>();

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

  @Override
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
