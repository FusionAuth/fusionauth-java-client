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

import java.util.List;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models an event where a user is being created with an "in-use" login Id (email, username, or other identities).
 *
 * @author Daniel DeGroff
 */
public class UserLoginIdDuplicateOnCreateEvent extends BaseUserEvent implements Buildable<UserLoginIdDuplicateOnCreateEvent>, NonTransactionalEvent {
  public String duplicateEmail;

  public List<IdentityInfo> duplicateIdentities;

  public String duplicateUsername;

  public User existing;

  @JacksonConstructor
  public UserLoginIdDuplicateOnCreateEvent() {
  }

  public UserLoginIdDuplicateOnCreateEvent(EventInfo info, String duplicateEmail, String duplicateUsername, List<IdentityInfo> duplicateIdentities, User existing, User user) {
    super(info, user);
    this.duplicateEmail = duplicateEmail;
    this.duplicateUsername = duplicateUsername;
    this.duplicateIdentities = duplicateIdentities;
    this.existing = existing;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserLoginIdDuplicateOnCreateEvent that = (UserLoginIdDuplicateOnCreateEvent) o;
    return Objects.equals(duplicateEmail, that.duplicateEmail) &&
           Objects.equals(duplicateIdentities, that.duplicateIdentities) &&
           Objects.equals(duplicateUsername, that.duplicateUsername) &&
           Objects.equals(existing, that.existing);
  }

  @Override
  public EventType getType() {
    return EventType.UserLoginIdDuplicateOnCreate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), duplicateEmail, duplicateIdentities, duplicateUsername, existing);
  }
}
