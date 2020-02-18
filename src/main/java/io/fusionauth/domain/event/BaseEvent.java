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

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.inversoft.json.ToString;

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
    @Type(value = UserEmailVerifiedEvent.class, name = "user.email.verified"),
    @Type(value = UserReactivateEvent.class, name = "user.reactivate"),
    @Type(value = UserBulkCreateEvent.class, name = "user.bulk.create"),
    @Type(value = UserLoginFailedEvent.class, name = "user.login.failed"),
    @Type(value = UserLoginSuccessEvent.class, name = "user.login.success"),
    @Type(value = UserPasswordBreachEvent.class, name = "user.password.breach"),
    @Type(value = UserRegistrationCreateEvent.class, name = "user.registration.create"),
    @Type(value = UserRegistrationUpdateEvent.class, name = "user.registration.update"),
    @Type(value = UserRegistrationDeleteEvent.class, name = "user.registration.delete"),
    @Type(value = UserRegistrationVerifiedEvent.class, name = "user.registration.verified"),
    @Type(value = JWTRefreshTokenRevokeEvent.class, name = "jwt.refresh-token.revoke"),
    @Type(value = JWTPublicKeyUpdateEvent.class, name = "jwt.public-key.update"),
    @Type(value = TestEvent.class, name = "test")
})
public abstract class BaseEvent {
  public ZonedDateTime createInstant;

  public UUID id;

  public UUID tenantId;

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
        Objects.equals(id, baseEvent.id) &&
        Objects.equals(tenantId, baseEvent.tenantId) &&
        Objects.equals(getType(), baseEvent.getType());
  }

  /**
   * @return The type of this event.
   */
  @JsonIgnore
  public abstract EventType getType();

  @Override
  public int hashCode() {
    return Objects.hash(createInstant, id, tenantId, getType());
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
