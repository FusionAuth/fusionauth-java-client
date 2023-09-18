/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
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

    if (p.getCodec() instanceof ObjectMapper) {
      // If the available codec supports it, read the JsonNode directly into the object
      ((ObjectMapper) p.getCodec()).readerForUpdating(req.connector).readValue(connectorNode);
    } else if (p.getCodec() instanceof ObjectReader) {
      // Otherwise read the JsonNode and assign it to the request
      req.connector = ((ObjectReader) p.getCodec()).readValue(connectorNode, req.connector.getClass());
    }
    return req;
  }
}
