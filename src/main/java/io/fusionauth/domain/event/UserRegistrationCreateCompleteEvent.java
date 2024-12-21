/*
 * Copyright (c) 2021-2024, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.UserRegistration;

/**
 * Models the User Created Registration Event.
 * <p>
 * This is different than the user.registration.create event in that it will be sent after the user has been created. This event cannot be made
 * transactional.
 *
 * @author Daniel DeGroff
 */
public class UserRegistrationCreateCompleteEvent extends BaseUserEvent implements Buildable<UserRegistrationCreateCompleteEvent>, NonTransactionalEvent {
  public UUID applicationId;

  public UserRegistration registration;

  @JacksonConstructor
  public UserRegistrationCreateCompleteEvent() {
  }

  public UserRegistrationCreateCompleteEvent(EventInfo info, UUID applicationId, UserRegistration registration, User user) {
    super(info, user);
    this.applicationId = applicationId;
    this.registration = registration;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserRegistrationCreateCompleteEvent that = (UserRegistrationCreateCompleteEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(registration, that.registration);
  }

  @Override
  public EventType getType() {
    return EventType.UserRegistrationCreateComplete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, registration);
  }
}
