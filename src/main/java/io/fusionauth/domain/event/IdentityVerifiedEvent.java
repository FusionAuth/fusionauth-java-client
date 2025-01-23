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
 * Models the identity verified event
 *
 * @author Brady Wied
 */
public class IdentityVerifiedEvent extends BaseUserEvent implements Buildable<IdentityVerifiedEvent> {
  public final String loginId;

  public final String loginIdType;

  /**
   * Creates an event
   *
   * @param info        The event info object containing IP information, and possibly location from the request.
   * @param loginId     the login ID that was verified
   * @param loginIdType describes what loginId is
   * @param user        (Optional) user the identity was verified for.
   */
  public IdentityVerifiedEvent(EventInfo info, String loginId, String loginIdType, User user) {
    super(info, user);
    this.loginId = loginId;
    this.loginIdType = loginIdType;
  }

  @JacksonConstructor
  private IdentityVerifiedEvent() {
    // Jackson will set values for these, but final field are still good
    this.loginId = null;
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
    IdentityVerifiedEvent that = (IdentityVerifiedEvent) o;
    return Objects.equals(loginId, that.loginId) && Objects.equals(loginIdType, that.loginIdType);
  }

  @Override
  public UUID getLinkedObjectId() {
    // BaseUserEvent expects a user to exist and identities can get created before users exist
    return user != null ? super.getLinkedObjectId() : null;
  }

  @Override
  public EventType getType() {
    return EventType.IdentityVerified;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), loginId, loginIdType);
  }
}
