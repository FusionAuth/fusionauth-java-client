/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.IdentityProviderLink;
import io.fusionauth.domain.User;

/**
 * Models the User Identity Provider Unlink Event.
 *
 * @author Rob Davis
 */
public class UserIdentityProviderUnlinkEvent extends BaseEvent implements Buildable<UserIdentityProviderUnlinkEvent>, NonTransactionalEvent {
  public IdentityProviderLink identityProviderLink;

  public User user;

  @JacksonConstructor
  public UserIdentityProviderUnlinkEvent() {
  }

  public UserIdentityProviderUnlinkEvent(EventInfo info, IdentityProviderLink identityProviderLink, User user) {
    super(info);
    this.identityProviderLink = identityProviderLink;
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
    if (!super.equals(o)) {
      return false;
    }
    UserIdentityProviderUnlinkEvent that = (UserIdentityProviderUnlinkEvent) o;
    return Objects.equals(identityProviderLink, that.identityProviderLink) && Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserIdentityProviderUnlink;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), identityProviderLink, user);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
