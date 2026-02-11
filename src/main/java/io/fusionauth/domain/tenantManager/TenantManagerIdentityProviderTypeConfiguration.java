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
package io.fusionauth.domain.tenantManager;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.Enableable;
import io.fusionauth.domain.provider.IdentityProviderLinkingStrategy;
import io.fusionauth.domain.provider.IdentityProviderType;

/**
 * Configuration object for identity provider types allowed in Tenant Manager
 */
public class TenantManagerIdentityProviderTypeConfiguration extends Enableable implements Buildable<TenantManagerIdentityProviderTypeConfiguration> {
  @JsonIgnore
  public Map<String, Object> data = new LinkedHashMap<>();

  public Map<String, String> defaultAttributeMappings = new HashMap<>();

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public IdentityProviderLinkingStrategy linkingStrategy;

  public IdentityProviderType type;

  @JacksonConstructor
  public TenantManagerIdentityProviderTypeConfiguration() {
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
    TenantManagerIdentityProviderTypeConfiguration that = (TenantManagerIdentityProviderTypeConfiguration) o;
    return Objects.equals(defaultAttributeMappings, that.defaultAttributeMappings) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           linkingStrategy == that.linkingStrategy &&
           type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), defaultAttributeMappings, insertInstant, lastUpdateInstant, linkingStrategy, type);
  }
}
