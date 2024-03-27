/*
 * Copyright (c) 2023-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.oauth2;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.Requirable;

/**
 * The policy for provided scope handling
 *
 * @author Spencer Witt
 */
public class ProvidedScopePolicy implements Buildable<ProvidedScopePolicy> {
  /**
   * Policy to control the OIDC <code>address</code> scope
   */
  public Requirable address = new Requirable(true, false);

  /**
   * Policy to control the OIDC <code>email</code> scope
   */
  public Requirable email = new Requirable(true, false);

  /**
   * Policy to control the OIDC <code>phone</code> scope
   */
  public Requirable phone = new Requirable(true, false);

  /**
   * Policy to control the OIDC <code>profile</code> scope
   */
  public Requirable profile = new Requirable(true, false);

  @JacksonConstructor
  public ProvidedScopePolicy() {
  }

  public ProvidedScopePolicy(ProvidedScopePolicy other) {
    this.address = new Requirable(other.address);
    this.email = new Requirable(other.email);
    this.phone = new Requirable(other.phone);
    this.profile = new Requirable(other.profile);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProvidedScopePolicy)) {
      return false;
    }
    ProvidedScopePolicy that = (ProvidedScopePolicy) o;
    return Objects.equals(address, that.address) &&
           Objects.equals(email, that.email) &&
           Objects.equals(phone, that.phone) &&
           Objects.equals(profile, that.profile);
  }

  @JsonIgnore
  public Requirable getScopePolicy(String scope) {
    //noinspection EnhancedSwitchMigration
    switch (scope) {
      case "address":
        return address;
      case "email":
        return email;
      case "phone":
        return phone;
      case "profile":
        return profile;
      default:
        return null;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, email, phone, profile);
  }
}
