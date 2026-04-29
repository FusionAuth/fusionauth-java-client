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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * Configuration object for the Tenant Manager.
 */
public class TenantManagerConfiguration implements Buildable<TenantManagerConfiguration> {
  public List<TenantManagerApplicationConfiguration> applicationConfigurations = new ArrayList<>();

  public UUID attributeFormId;

  public String brandName;

  @JsonIgnore
  public Map<String, Object> data = new HashMap<>();

  public Map<String, TenantManagerIdentityProviderTypeConfiguration> identityProviderTypeConfigurations = new HashMap<>();

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  @JacksonConstructor
  public TenantManagerConfiguration() {
  }

  public TenantManagerConfiguration(TenantManagerConfiguration other) {
    this.applicationConfigurations.addAll(other.applicationConfigurations.stream().map(TenantManagerApplicationConfiguration::new).collect(Collectors.toList()));
    this.attributeFormId = other.attributeFormId;
    this.brandName = other.brandName;
    if (other.data != null) {
      this.data.putAll(other.data);
    }
    other.identityProviderTypeConfigurations.forEach((k, v) ->
                                                         this.identityProviderTypeConfigurations.put(k, new TenantManagerIdentityProviderTypeConfiguration(v)));
    this.insertInstant = other.insertInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantManagerConfiguration)) {
      return false;
    }
    TenantManagerConfiguration that = (TenantManagerConfiguration) o;
    return Objects.equals(applicationConfigurations, that.applicationConfigurations) &&
           Objects.equals(attributeFormId, that.attributeFormId) &&
           Objects.equals(brandName, that.brandName) &&
           Objects.equals(data, that.data) &&
           Objects.equals(identityProviderTypeConfigurations, that.identityProviderTypeConfigurations) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationConfigurations, attributeFormId, brandName, data, identityProviderTypeConfigurations, insertInstant, lastUpdateInstant);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
