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
 * Models the User Two Factor Success Event. Fired when a user successfully completes a two-factor challenge.
 */
public class UserTwoFactorSuccessEvent extends BaseUserEvent implements Buildable<UserTwoFactorSuccessEvent>, NonTransactionalEvent {
  public UUID applicationId;

  public String clientRisk;

  public String messageType;

  public String method;

  @JacksonConstructor
  public UserTwoFactorSuccessEvent() {
  }

  public UserTwoFactorSuccessEvent(EventInfo info, String clientRisk, String messageType, String method, UUID applicationId, User user) {
    super(info, user);
    this.applicationId = applicationId;
    this.messageType = messageType;
    this.method = method;
    this.clientRisk = clientRisk;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserTwoFactorSuccessEvent that = (UserTwoFactorSuccessEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(messageType, that.messageType) &&
           Objects.equals(method, that.method) &&
           Objects.equals(clientRisk, that.clientRisk);
  }

  @Override
  public EventType getType() {
    return EventType.UserTwoFactorSuccess;
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
