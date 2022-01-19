/*
 * Copyright (c) 2018-2020, FusionAuth, All Rights Reserved
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Models the event types that FusionAuth produces.
 *
 * @author Brian Pontarelli
 */
public enum EventType {
  JWTPublicKeyUpdate("jwt.public-key.update"),

  // TODO : 2.0 : Rename -> refresh-token.revoke
  JWTRefreshTokenRevoke("jwt.refresh-token.revoke"),

  JWTRefresh("jwt.refresh"),

  AuditLogCreate("audit-log.create"),

  EventLogCreate("event-log.create"),

  KickstartSuccess("kickstart.success"),

  UserAction("user.action"),

  UserBulkCreate("user.bulk.create"),

  UserCreate("user.create"),

  UserCreateComplete("user.create.complete"),

  UserDeactivate("user.deactivate"),

  UserDelete("user.delete"),

  UserDeleteComplete("user.delete.complete"),

  UserLoginIdDuplicateOnCreate("user.loginId.duplicate.create"),

  UserLoginIdDuplicateOnUpdate("user.loginId.duplicate.update"),

  UserEmailUpdate("user.email.update"),

  UserEmailVerified("user.email.verified"),

  UserLoginFailed("user.login.failed"),

  UserLoginNewDevice("user.login.new-device"),

  UserLoginSuccess("user.login.success"),

  UserLoginSuspicious("user.login.suspicious"),

  UserPasswordBreach("user.password.breach"),

  UserPasswordResetSend("user.password.reset.send"),

  UserPasswordResetStart("user.password.reset.start"),

  UserPasswordResetSuccess("user.password.reset.success"),

  UserPasswordUpdate("user.password.update"),

  UserReactivate("user.reactivate"),

  UserRegistrationCreate("user.registration.create"),

  UserRegistrationCreateComplete("user.registration.create.complete"),

  UserRegistrationDelete("user.registration.delete"),

  UserRegistrationDeleteComplete("user.registration.delete.complete"),

  UserRegistrationUpdate("user.registration.update"),

  UserRegistrationUpdateComplete("user.registration.update.complete"),

  UserRegistrationVerified("user.registration.verified"),

  UserTwoFactorMethodAdd("user.two-factor.method.add"),

  UserTwoFactorMethodRemove("user.two-factor.method.remove"),

  UserUpdate("user.update"),

  UserUpdateComplete("user.update.complete"),

  Test("test");

  private static final Map<String, EventType> nameMap = new HashMap<>(EventType.values().length);

  private final String eventName;

  EventType(String eventName) {
    this.eventName = eventName;
  }

  /**
   * @return Return all available types in displayable order.
   */
  public static List<EventType> allTypes() {
    return Arrays.asList(EventType.AuditLogCreate,
                         EventType.EventLogCreate,
                         EventType.JWTPublicKeyUpdate,
                         EventType.JWTRefreshTokenRevoke,
                         EventType.JWTRefresh,
                         EventType.KickstartSuccess,
                         EventType.UserAction,
                         EventType.UserBulkCreate,
                         EventType.UserCreate,
                         EventType.UserCreateComplete,
                         EventType.UserDeactivate,
                         EventType.UserDelete,
                         EventType.UserDeleteComplete,
                         EventType.UserEmailUpdate,
                         EventType.UserEmailVerified,
                         EventType.UserLoginIdDuplicateOnCreate,
                         EventType.UserLoginIdDuplicateOnUpdate,
                         EventType.UserLoginFailed,
                         EventType.UserLoginNewDevice,
                         EventType.UserLoginSuccess,
                         EventType.UserLoginSuspicious,
                         EventType.UserPasswordBreach,
                         EventType.UserPasswordResetSend,
                         EventType.UserPasswordResetStart,
                         EventType.UserPasswordResetSuccess,
                         EventType.UserPasswordUpdate,
                         EventType.UserReactivate,
                         EventType.UserRegistrationCreate,
                         EventType.UserRegistrationCreateComplete,
                         EventType.UserRegistrationDelete,
                         EventType.UserRegistrationDeleteComplete,
                         EventType.UserRegistrationUpdate,
                         EventType.UserRegistrationUpdateComplete,
                         EventType.UserRegistrationVerified,
                         EventType.UserTwoFactorMethodAdd,
                         EventType.UserTwoFactorMethodRemove,
                         EventType.UserUpdate,
                         EventType.UserUpdateComplete
    );
  }

  @JsonCreator
  public static EventType forValue(String value) {
    return nameMap.get(value);
  }

  @JsonValue
  public String eventName() {
    return eventName;
  }

  public boolean isTransactionalEvent() {
    try {
      return !(Class.forName(getClass().getPackage().getName() + "." + name() + "Event").getConstructor().newInstance() instanceof NonTransactionalEvent);
    } catch (Exception ignore) {
    }

    return false;
  }

  static {
    for (EventType eventType : EventType.values()) {
      nameMap.put(eventType.eventName(), eventType);
    }
  }
}
