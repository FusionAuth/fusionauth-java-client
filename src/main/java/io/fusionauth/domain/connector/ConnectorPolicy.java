/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.connector;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Trevor Smith
 */
public class ConnectorPolicy implements Buildable<ConnectorPolicy>, _InternalJSONColumn {
  public UUID connectorId = BaseConnectorConfiguration.FUSIONAUTH_CONNECTOR_ID;

  public Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public Set<String> domains = new HashSet<>(Collections.singletonList("*"));

  @InternalJSONColumn
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