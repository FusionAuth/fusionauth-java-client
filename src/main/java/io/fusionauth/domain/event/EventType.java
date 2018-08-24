/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

import java.util.HashMap;
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

  JWTRefreshTokenRevoke("jwt.refresh-token.revoke"),

  JWTPublicKeyUpdate("jwt.public-key.update"),

  Test("test");

  private static Map<String, EventType> nameMap = new HashMap<>(EventType.values().length);

  private final String eventName;

  EventType(String eventName) {
    this.eventName = eventName;
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
