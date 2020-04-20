/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * Models the JWT Refresh Event. This event will be fired when a JWT is "refreshed" (generated) using a Refresh Token.
 *
 * @author Daniel DeGroff
 */
public class JWTRefreshEvent extends BaseEvent implements Buildable<JWTRefreshEvent> {
  public UUID applicationId;

  public String original;

  public String refreshToken;

  public String token;

  public UUID userId;

  @JacksonConstructor
  public JWTRefreshEvent() {
  }

  public JWTRefreshEvent(UUID applicationId, String token, String original, String refreshToken, UUID userId) {
    this.applicationId = applicationId;
    this.token = token;
    this.original = original;
    this.refreshToken = refreshToken;
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JWTRefreshEvent)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    JWTRefreshEvent that = (JWTRefreshEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(original, that.original) &&
           Objects.equals(refreshToken, that.refreshToken) &&
           Objects.equals(token, that.token) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public EventType getType() {
    return EventType.JWTRefresh;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, original, refreshToken, token, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
