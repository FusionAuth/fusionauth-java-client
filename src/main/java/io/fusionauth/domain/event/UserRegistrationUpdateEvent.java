/*
 * Copyright (c) 2019-2025, FusionAuth, All Rights Reserved
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
 *
 * @author Daniel DeGroff
 */
public class UserRegistrationUpdateEvent extends BaseUserEvent implements Buildable<UserRegistrationUpdateEvent> {
  public UUID applicationId;

  public UserRegistration original;

  public UserRegistration registration;

  /**
   * Construct a new event, with only the original/current registration being updated
   *
   * @param info          event info
   * @param applicationId application the registration is for
   * @param original      original registration before updates
   * @param registration  updated registration
   * @param user          user affected. This user will be copied and all of its registrations will be removed
   */
  public UserRegistrationUpdateEvent(EventInfo info, UUID applicationId, UserRegistration original, UserRegistration registration, User user) {
    super(info, user);
    this.user.getRegistrations().clear();
    this.applicationId = applicationId;
    this.original = original;
    this.registration = registration;
  }

  @JacksonConstructor
  private UserRegistrationUpdateEvent() {
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserRegistrationUpdateEvent that = (UserRegistrationUpdateEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(original, that.original) &&
           Objects.equals(registration, that.registration);
  }

  @Override
  public EventType getType() {
    return EventType.UserRegistrationUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, original, registration);
  }
}
