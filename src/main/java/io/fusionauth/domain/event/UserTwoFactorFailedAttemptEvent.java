/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models the User Two Factor Failed Attempt Event. Fired when a user fails a two-factor challenge.
 */
public class UserTwoFactorFailedAttemptEvent extends BaseUserEvent implements Buildable<UserTwoFactorFailedAttemptEvent>, NonTransactionalEvent {
  public UUID applicationId;

  public String clientRisk;

  public String messageType;

  public String method;

  @JacksonConstructor
  public UserTwoFactorFailedAttemptEvent() {
  }

  public UserTwoFactorFailedAttemptEvent(EventInfo info, String clientRisk, String messageType, String method, UUID applicationId, User user) {
    super(info, user);
    this.clientRisk = clientRisk;
    this.applicationId = applicationId;
    this.method = method;
    this.messageType = messageType;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserTwoFactorFailedAttemptEvent that = (UserTwoFactorFailedAttemptEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(method, that.method) &&
           Objects.equals(messageType, that.messageType) &&
           Objects.equals(clientRisk, that.clientRisk);
  }

  @Override
  public EventType getType() {
    return EventType.UserTwoFactorFailedAttempt;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, messageType, method, clientRisk);
  }

  @Override
  public String toString() {
    return com.inversoft.json.ToString.toString(this);
  }
}
