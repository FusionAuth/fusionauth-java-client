/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
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
