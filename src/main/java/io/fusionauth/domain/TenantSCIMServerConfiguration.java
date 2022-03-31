/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;

/**
 * @author Rob Davis
 */
public class TenantSCIMServerConfiguration extends Enableable implements Buildable<TenantSCIMServerConfiguration> {
  @ExcludeFromDatabaseDataColumn
  public UUID clientEntityTypeId;

  public Map<String, Object> schemas;

  @ExcludeFromDatabaseDataColumn
  public UUID serverEntityTypeId;

  @JacksonConstructor
  public TenantSCIMServerConfiguration() {
  }

  public TenantSCIMServerConfiguration(TenantSCIMServerConfiguration other) {
    this.clientEntityTypeId = other.clientEntityTypeId;
    this.enabled = other.enabled;
    this.schemas = other.schemas != null ? new LinkedHashMap<>(other.schemas) : null;
    this.serverEntityTypeId = other.serverEntityTypeId;
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
    TenantSCIMServerConfiguration that = (TenantSCIMServerConfiguration) o;
    return Objects.equals(clientEntityTypeId, that.clientEntityTypeId) &&
           Objects.equals(schemas, that.schemas) &&
           Objects.equals(serverEntityTypeId, that.serverEntityTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), clientEntityTypeId, schemas, serverEntityTypeId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}