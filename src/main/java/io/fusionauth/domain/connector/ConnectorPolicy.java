/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.connector;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Trevor Smith
 */
public class ConnectorPolicy implements Buildable<ConnectorPolicy> {
  public UUID connectorId = BaseConnectorConfiguration.FUSIONAUTH_CONNECTOR_ID;

  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public Map<String, Object> data = new LinkedHashMap<>();

  @JsonMerge(OptBoolean.FALSE)
  public Set<String> domains = new HashSet<>(Collections.singletonList("*"));

  public boolean migrate;

  @JacksonConstructor
  public ConnectorPolicy() {
  }

  public ConnectorPolicy(ConnectorPolicy other) {
    this.migrate = other.migrate;
    this.connectorId = other.connectorId;
    this.data.putAll(other.data);
    this.domains.addAll(other.domains);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ConnectorPolicy)) {
      return false;
    }
    ConnectorPolicy that = (ConnectorPolicy) o;
    return migrate == that.migrate &&
           Objects.equals(connectorId, that.connectorId) &&
           Objects.equals(data, that.data) &&
           Objects.equals(domains, that.domains);
  }

  @Override
  public int hashCode() {
    return Objects.hash(migrate, connectorId, data, domains);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}