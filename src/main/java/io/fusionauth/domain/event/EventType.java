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
  UserDelete("user.delete"),

  UserCreate("user.create"),

  UserUpdate("user.update"),

  UserDeactivate("user.deactivate"),

  UserBulkCreate("user.bulk.create"),

  UserReactivate("user.reactivate"),

  UserAction("user.action"),

  // 2.0 Rename -> refresh-token.revoke
  JWTRefreshTokenRevoke("jwt.refresh-token.revoke"),

  JWTRefresh("jwt.refresh"),

  JWTPublicKeyUpdate("jwt.public-key.update"),

  UserLoginSuccess("user.login.success"),

  UserLoginFailed("user.login.failed"),

  UserRegistrationCreate("user.registration.create"),

  UserRegistrationUpdate("user.registration.update"),

  UserRegistrationDelete("user.registration.delete"),

  UserRegistrationVerified("user.registration.verified"),

  UserEmailVerified("user.email.verified"),

  UserPasswordBreach("user.password.breach"),

  // TODO : Future : Add an event for each time we cut an Event Log? This way we could push out events to a 3rd party system?

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
    return Arrays.asList(EventType.JWTPublicKeyUpdate,
                         EventType.JWTRefreshTokenRevoke,
                         EventType.JWTRefresh,
                         EventType.UserLoginSuccess,
                         EventType.UserLoginFailed,
                         EventType.UserAction,
                         EventType.UserBulkCreate,
                         EventType.UserCreate,
                         EventType.UserRegistrationCreate,
                         EventType.UserRegistrationUpdate,
                         EventType.UserRegistrationDelete,
                         EventType.UserRegistrationVerified,
                         EventType.UserDeactivate,
                         EventType.UserDelete,
                         EventType.UserReactivate,
                         EventType.UserUpdate,
                         EventType.UserEmailVerified,
                         EventType.UserPasswordBreach);
  }

  /**
   * This returns all event types with the exception of the UserAction because the transaction for that event is configured per event.
   *
   * @return Return all available types in displayable order that can be globally configured for a transaction setting.
   */
  public static List<EventType> allTypesForTransactionConfiguration() {
    return Arrays.asList(EventType.JWTPublicKeyUpdate,
                         EventType.JWTRefreshTokenRevoke,
                         EventType.JWTRefresh,
                         EventType.UserLoginSuccess,
                         EventType.UserLoginFailed,
                         EventType.UserBulkCreate,
                         EventType.UserCreate,
                         EventType.UserRegistrationCreate,
                         EventType.UserRegistrationUpdate,
                         EventType.UserRegistrationDelete,
                         EventType.UserRegistrationVerified,
                         EventType.UserDeactivate,
                         EventType.UserDelete,
                         EventType.UserReactivate,
                         EventType.UserUpdate,
                         EventType.UserEmailVerified,
                         EventType.UserPasswordBreach);
  }

  @JsonCreator
  public static EventType forValue(String value) {
    return nameMap.get(value);
  }

  @JsonValue
  public String eventName() {
    return eventName;
  }

  static {
    for (EventType eventType : EventType.values()) {
      nameMap.put(eventType.eventName(), eventType);
    }
  }
}
