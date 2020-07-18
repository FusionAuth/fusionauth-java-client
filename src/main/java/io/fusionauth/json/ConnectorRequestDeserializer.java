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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.ConnectorRequest;
import io.fusionauth.domain.connector.ConnectorType;

/**
 * Custom JSON deserializer for ConnectorRequest.
 *
 * @author Trevor Smith
 */
public class ConnectorRequestDeserializer extends StdDeserializer<ConnectorRequest> {
  public ConnectorRequestDeserializer() {
    super(ConnectorRequest.class);
  }

  @Override
  public ConnectorRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new ConnectorRequest());
  }

  @Override
  public ConnectorRequest deserialize(JsonParser p, DeserializationContext ctxt, ConnectorRequest req)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    JsonNode connectorNode = node.at("/connector");

    if (req.connector == null) {
      ConnectorType connectorType = ConnectorJacksonHelper.extractType(ctxt, p, connectorNode);
      req.connector = ConnectorJacksonHelper.newConnector(connectorType);
    }

    ((ObjectMapper) p.getCodec()).readerForUpdating(req.connector).readValue(connectorNode);
    return req;
  }
}
