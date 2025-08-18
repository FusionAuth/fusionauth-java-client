/*
 * Copyright (c) 2020-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;
import io.fusionauth.domain.connector.ConnectorType;
import io.fusionauth.domain.connector.FusionAuthConnectorConfiguration;
import io.fusionauth.domain.connector.GenericConnectorConfiguration;
import io.fusionauth.domain.connector.LDAPConnectorConfiguration;

/**
 * @author Trevor Smith
 */
public class ConnectorJacksonHelper {
  public static ConnectorType extractType(DeserializationContext ctxt, JsonParser p, JsonNode connectorNode)
      throws IOException {
    JsonNode node = connectorNode.at("/type");
    String type = node.asText(ConnectorType.Generic.name());

    ConnectorType connectorType = ConnectorType.safeValueOf(type);
    if (connectorType == null) {
      // This does actually throw an exception, but this is how Jackson rolls.
      String sorted = Arrays.stream(ConnectorType.values()).map(Enum::name).sorted().collect(Collectors.joining(", "));
      return (ConnectorType) ctxt.handleUnexpectedToken(BaseConnectorConfiguration.class, node.asToken(), p,
                                                        "Expected the type field to be one of [" + sorted + "], but found [" + node.asText() + "]");
    }

    return connectorType;
  }

  public static BaseConnectorConfiguration newConnector(ConnectorType type) {
    switch (type) {
      case FusionAuth:
        return new FusionAuthConnectorConfiguration();
      case Generic:
        return new GenericConnectorConfiguration();
      case LDAP:
        return new LDAPConnectorConfiguration();
      default:
        throw new IllegalStateException("Unexpected type [" + type + "]. This is a FusionAuth bug, someone forgot to add a case statement for a new type.");
    }
  }
}
