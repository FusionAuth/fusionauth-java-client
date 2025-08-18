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

/**
 * Models the user identity update event
 *
 * @author Brent Halsey
 */
public class UserIdentityUpdateEvent extends BaseUserEvent implements Buildable<UserIdentityUpdateEvent> {
  public final String loginIdType;

  public final String newLoginId;

  public final String previousLoginId;

  /**
   * Creates an event
   *
   * @param info            The event info object containing IP information, and possibly location from the request.
   * @param previousLoginId The previous login Id that was updated
   * @param newLoginId      The updated login Id
   * @param loginIdType     The type of previousLoginId and newLoginId (e.g., "email", "phoneNumber", "username").
   * @param user            User whose identity was updated.
   */
  public UserIdentityUpdateEvent(EventInfo info, String previousLoginId, String newLoginId, String loginIdType, User user) {
    super(info, user);
    this.previousLoginId = previousLoginId;
    this.newLoginId = newLoginId;
    this.loginIdType = loginIdType;
  }

  @JacksonConstructor
  private UserIdentityUpdateEvent() {
    // Jackson will set values for these, but final fields are still good
    this.previousLoginId = null;
    this.newLoginId = null;
    this.loginIdType = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    UserIdentityUpdateEvent that = (UserIdentityUpdateEvent) o;
    return
        Objects.equals(previousLoginId, that.previousLoginId) &&
        Objects.equals(newLoginId, that.newLoginId) &&
        Objects.equals(loginIdType, that.loginIdType);
  }

  @Override
  public UUID getLinkedObjectId() {
    // BaseUserEvent expects a user to exist and identities can get created before users exist
    return user != null ? super.getLinkedObjectId() : null;
  }

  @Override
  public EventType getType() {
    return EventType.UserIdentityUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), previousLoginId, newLoginId, loginIdType);
  }
}
