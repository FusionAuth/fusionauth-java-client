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
package io.fusionauth.domain.provider;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.User;

/**
 * @author Daniel DeGroff
 */
public class PendingIdPLink implements Buildable<PendingIdPLink> {
  public String displayName;

  public String email;

  public UUID identityProviderId;

  public String identityProviderName;

  public String identityProviderType;

  public String identityProviderUserId;

  public User user;

  public String username;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PendingIdPLink that = (PendingIdPLink) o;
    return Objects.equals(displayName, that.displayName) && Objects.equals(email, that.email) && Objects.equals(user, that.user) && Objects.equals(identityProviderId, that.identityProviderId) && Objects.equals(identityProviderName, that.identityProviderName) && Objects.equals(identityProviderType, that.identityProviderType) && Objects.equals(identityProviderUserId, that.identityProviderUserId) && Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, email, user, identityProviderId, identityProviderName, identityProviderType, identityProviderUserId, username);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}