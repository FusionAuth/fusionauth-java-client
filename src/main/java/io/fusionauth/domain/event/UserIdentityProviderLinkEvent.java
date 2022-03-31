/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models the User IdP Link Event.
 *
 * @author Rob Davis
 */
public class UserIdentityProviderLinkEvent extends BaseEvent implements Buildable<UserIdentityProviderLinkEvent>, NonTransactionalEvent {
  public String identityProviderName;

  public User user;

  @JacksonConstructor
  public UserIdentityProviderLinkEvent() {
  }

  public UserIdentityProviderLinkEvent(EventInfo info, String identityProviderName, User user) {
    super(info);
    this.identityProviderName = identityProviderName;
    this.user = user;
  }

  @Override
  public EventType getType() {
    return EventType.UserIdentityProviderLink;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
