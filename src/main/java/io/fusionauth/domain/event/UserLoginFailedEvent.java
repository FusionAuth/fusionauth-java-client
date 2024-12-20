/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.User;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.UserLoginFailedReason;
import io.fusionauth.domain.UserLoginFailedReasonCode;

/**
 * Models the User Login Failed Event.
 *
 * @author Daniel DeGroff
 */
public class UserLoginFailedEvent extends BaseUserEvent implements Buildable<UserLoginFailedEvent> {
  public UUID applicationId;

  public String authenticationType;

  @Deprecated
  public String ipAddress;

  public UserLoginFailedReason reason = new UserLoginFailedReason(UserLoginFailedReasonCode.Credentials);

  @JacksonConstructor
  public UserLoginFailedEvent() {
  }

  @Deprecated // Eligible for removal in 2025
  public UserLoginFailedEvent(EventInfo info, UUID applicationId, String authenticationType, User user) {
    super(info, user);
    this.applicationId = applicationId;
    this.authenticationType = authenticationType;

    // Maintain the old JSON format
    if (info != null && info.ipAddress != null) {
      ipAddress = info.ipAddress;
    }
  }

  public UserLoginFailedEvent(EventInfo info, UUID applicationId, String authenticationType, UserLoginFailedReason reason, User user) {
    super(info, user);
    this.applicationId = applicationId;
    this.authenticationType = authenticationType;
    this.user = user;

    // Maintain the old JSON format
    if (info != null && info.ipAddress != null) {
      ipAddress = info.ipAddress;
    }

    this.reason = reason;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserLoginFailedEvent that = (UserLoginFailedEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(authenticationType, that.authenticationType) &&
           Objects.equals(ipAddress, that.ipAddress) &&
           Objects.equals(reason, that.reason);
  }

  @Override
  public EventType getType() {
    return EventType.UserLoginFailed;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, authenticationType, ipAddress, reason);
  }
}
