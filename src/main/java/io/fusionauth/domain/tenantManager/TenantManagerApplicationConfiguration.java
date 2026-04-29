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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * Tenant Manager application configuration.
 */
public class TenantManagerApplicationConfiguration implements Buildable<TenantManagerApplicationConfiguration> {
  public UUID applicationId;

  @JacksonConstructor
  public TenantManagerApplicationConfiguration() {
  }

  public TenantManagerApplicationConfiguration(UUID applicationId) {
    this.applicationId = applicationId;
  }

  public TenantManagerApplicationConfiguration(TenantManagerApplicationConfiguration other) {
    this.applicationId = other.applicationId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantManagerApplicationConfiguration)) {
      return false;
    }
    TenantManagerApplicationConfiguration that = (TenantManagerApplicationConfiguration) o;
    return Objects.equals(applicationId, that.applicationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
