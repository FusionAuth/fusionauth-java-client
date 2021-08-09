/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;

/**
 * Models the User Created Registration Event (and can be converted to JSON).
 * <p>
 * This is different than the user.registration.create event in that it will be sent after the user has been created. This event cannot be made transactional.
 *
 * @author Daniel DeGroff
 */
public class UserRegistrationCreateCompleteEvent extends BaseEvent implements Buildable<UserRegistrationCreateCompleteEvent>, NonTransactionalEvent {
  public UUID applicationId;

  public UserRegistration registration;

  public User user;

  @JacksonConstructor
  public UserRegistrationCreateCompleteEvent() {
  }

  public UserRegistrationCreateCompleteEvent(EventInfo info, UUID applicationId, UserRegistration registration, User user) {
    super(info);
    this.applicationId = applicationId;
    this.registration = registration;
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegistrationCreateCompleteEvent that = (UserRegistrationCreateCompleteEvent) o;
    return super.equals(o) &&
           Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(registration, that.registration) &&
           Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserRegistrationCreateComplete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, registration, user);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
