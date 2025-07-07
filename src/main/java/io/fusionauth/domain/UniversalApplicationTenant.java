/*
 * Copyright (c) 2025, FusionAuth, All Rights Reserved
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

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * An object that represents the mapping between a Universal Application and a Tenant.
 *
 * @author Lyle Schemmerling
 */
public class UniversalApplicationTenant implements Buildable<UniversalApplicationTenant> {
  public UUID applicationId;

  public Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public UUID tenantId;

  @JacksonConstructor
  public UniversalApplicationTenant() {
  }

  // copy constructor
  public UniversalApplicationTenant(UniversalApplicationTenant other) {
    this.id = other.id;
    this.tenantId = other.tenantId;
    this.applicationId = other.applicationId;
    this.insertInstant = other.insertInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.data = new LinkedHashMap<>(other.data);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniversalApplicationTenant that = (UniversalApplicationTenant) o;
    return Objects.equals(id, that.id)
           && Objects.equals(insertInstant, that.insertInstant)
           && Objects.equals(lastUpdateInstant, that.lastUpdateInstant)
           && Objects.equals(applicationId, that.applicationId)
           && Objects.equals(tenantId, that.tenantId)
           && Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, insertInstant, lastUpdateInstant, applicationId, tenantId, data);
  }
}
