/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.json;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.ConnectorResponse;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;
import io.fusionauth.domain.connector.ConnectorType;

/**
 * Custom JSON de-serializer for ConnectorResponse.
 *
 * @author Trevor Smith
 */
public class ConnectorResponseDeserializer extends StdDeserializer<ConnectorResponse> {
  public ConnectorResponseDeserializer() {
    super(ConnectorResponse.class);
  }

  @Override
  public ConnectorResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new ConnectorResponse());
  }

  @Override
  public ConnectorResponse deserialize(JsonParser p, DeserializationContext ctxt, ConnectorResponse resp) throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    JsonNode connectorNode = node.at("/connector");
    if (connectorNode != null) {
      ConnectorType connectorType = ConnectorJacksonHelper.extractType(ctxt, p, connectorNode);
      resp.connector = ConnectorJacksonHelper.newConnector(connectorType);
      ((ObjectMapper) p.getCodec()).readerForUpdating(resp.connector).readValue(connectorNode);
    }

    JsonNode connectorNodes = node.at("/connectors");
    if (connectorNodes != null && connectorNodes.isArray()) {
      resp.connectors = new ArrayList<>();
      for (JsonNode connector : connectorNodes) {
        ConnectorType connectorType = ConnectorJacksonHelper.extractType(ctxt, p, connector);
        BaseConnectorConfiguration baseConnectorConfiguration = ConnectorJacksonHelper.newConnector(connectorType);
        ((ObjectMapper) p.getCodec()).readerForUpdating(baseConnectorConfiguration).readValue(connector);
        resp.connectors.add(baseConnectorConfiguration);
      }
    }

    return resp;
  }
}
