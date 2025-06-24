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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Lyle Schemmerling
 */
public class UniversalApplicationTenant implements Buildable<UniversalApplicationTenant> {
  public UUID tenantId;

  @JacksonConstructor
  public UniversalApplicationTenant() {
  }

  public UniversalApplicationTenant(UUID tenantId) {
    this.tenantId = tenantId;
  }

  // copy constructor
  public UniversalApplicationTenant(UniversalApplicationTenant other) {
    this.tenantId = other.tenantId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniversalApplicationTenant that = (UniversalApplicationTenant) o;
    return Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(tenantId);
  }
}
