/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.oauth2.ProvidedScopePolicy;

/**
 * Application configuration overrides for one OAuth resource requested by a CIMD client registration.
 */
public class CimdClientRegistrationResourceOverride implements Buildable<CimdClientRegistrationResourceOverride> {
  public ProvidedScopePolicy providedScopePolicy;

  public List<ApplicationOAuthScope> scopes = new ArrayList<>();

  @JacksonConstructor
  public CimdClientRegistrationResourceOverride() {
  }

  public CimdClientRegistrationResourceOverride(CimdClientRegistrationResourceOverride other) {
    this.providedScopePolicy = other.providedScopePolicy == null ? null : new ProvidedScopePolicy(other.providedScopePolicy);
    this.scopes = other.scopes.stream().map(ApplicationOAuthScope::new).collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CimdClientRegistrationResourceOverride that = (CimdClientRegistrationResourceOverride) o;
    return Objects.equals(providedScopePolicy, that.providedScopePolicy) &&
           Objects.equals(scopes, that.scopes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(providedScopePolicy, scopes);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
