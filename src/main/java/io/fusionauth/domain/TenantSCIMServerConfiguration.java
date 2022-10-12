/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;


/**
 * @author Rob Davis
 */
public class TenantSCIMServerConfiguration extends Enableable implements Buildable<TenantSCIMServerConfiguration> {
  
  public UUID clientEntityTypeId;

  public Map<String, Object> schemas;

  
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