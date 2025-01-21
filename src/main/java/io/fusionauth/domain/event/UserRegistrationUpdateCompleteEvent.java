/*
 * Copyright (c) 2021-2025, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.UserRegistration;

/**
 * Models the User Update Registration Event.
 * <p>
 * This is different than user.registration.update in that it is sent after this event completes, this cannot be transactional.
 *
 * @author Daniel DeGroff
 */
public class UserRegistrationUpdateCompleteEvent extends BaseUserEvent implements Buildable<UserRegistrationUpdateCompleteEvent>, NonTransactionalEvent {
  public UUID applicationId;

  public UserRegistration original;

  public UserRegistration registration;

  @JacksonConstructor
  public UserRegistrationUpdateCompleteEvent() {
  }

  public UserRegistrationUpdateCompleteEvent(EventInfo info, UUID applicationId, UserRegistration original, UserRegistration registration,
                                             User user) {
    super(info, user);
    this.user.getRegistrations().clear();
    this.applicationId = applicationId;
    this.original = original;
    this.registration = registration;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserRegistrationUpdateCompleteEvent that = (UserRegistrationUpdateCompleteEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(original, that.original) &&
           Objects.equals(registration, that.registration);
  }

  @Override
  public EventType getType() {
    return EventType.UserRegistrationUpdateComplete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, original, registration);
  }
}
