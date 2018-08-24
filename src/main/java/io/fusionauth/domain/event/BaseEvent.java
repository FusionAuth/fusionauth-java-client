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

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base-class for all FusionAuth events.
 *
 * @author Brian Pontarelli
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @Type(value = UserActionEvent.class, name = "user.action"),
    @Type(value = UserCreateEvent.class, name = "user.create"),
    @Type(value = UserUpdateEvent.class, name = "user.update"),
    @Type(value = UserDeleteEvent.class, name = "user.delete"),
    @Type(value = UserDeactivateEvent.class, name = "user.deactivate"),
    @Type(value = UserReactivateEvent.class, name = "user.reactivate"),
    @Type(value = UserBulkCreateEvent.class, name = "user.bulk.create"),
    @Type(value = JWTRefreshTokenRevokeEvent.class, name = "jwt.refresh-token.revoke"),
    @Type(value = JWTPublicKeyUpdateEvent.class, name = "jwt.public-key.update"),
    @Type(value = TestEvent.class, name = "test")
})
public abstract class BaseEvent {
  public ZonedDateTime createInstant;

  public UUID id;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseEvent baseEvent = (BaseEvent) o;
    return Objects.equals(createInstant, baseEvent.createInstant) &&
        Objects.equals(id, baseEvent.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createInstant, id);
  }

  /**
   * @return The type of this event.
   */
  public abstract EventType type();
}
