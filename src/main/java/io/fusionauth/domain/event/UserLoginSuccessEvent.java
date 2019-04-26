/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.User;
import io.fusionauth.domain.provider.BaseIdentityProvider;

/**
 * Models the User Login Success Event.
 *
 * @author Daniel DeGroff
 */
public class UserLoginSuccessEvent extends BaseEvent implements Buildable<UserLoginSuccessEvent> {
  public UUID applicationId;

  public String authenticationType;

  public UUID identityProviderId;

  public String identityProviderName;

  public User user;

  @JacksonConstructor
  public UserLoginSuccessEvent() {
  }

  public UserLoginSuccessEvent(UUID applicationId, String authenticationType, BaseIdentityProvider identityProvider, User user) {
    this.applicationId = applicationId;
    this.authenticationType = authenticationType;
    this.identityProviderId = identityProvider.id;
    this.identityProviderName = identityProvider.name;
    this.user = user;
  }

  public UserLoginSuccessEvent(UUID applicationId, String authenticationType, User user) {
    this.applicationId = applicationId;
    this.authenticationType = authenticationType;
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
    UserLoginSuccessEvent that = (UserLoginSuccessEvent) o;
    return super.equals(o) &&
        Objects.equals(applicationId, that.applicationId) &&
        Objects.equals(authenticationType, that.authenticationType) &&
        Objects.equals(identityProviderId, that.identityProviderId) &&
        Objects.equals(identityProviderName, that.identityProviderName) &&
        Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserLoginSuccess;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, authenticationType, identityProviderId, identityProviderName, user);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
