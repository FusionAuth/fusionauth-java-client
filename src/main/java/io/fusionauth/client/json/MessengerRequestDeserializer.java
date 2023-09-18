/*
 * Copyright (c) 2020-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.MessengerRequest;
import io.fusionauth.domain.messenger.MessengerType;

/**
 * @author Brett Guy
 */
public class MessengerRequestDeserializer extends StdDeserializer<MessengerRequest> {
  public MessengerRequestDeserializer() {
    super(MessengerRequest.class);
  }

  @Override
  public MessengerRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new MessengerRequest());
  }

  @Override
  public MessengerRequest deserialize(JsonParser p, DeserializationContext ctxt, MessengerRequest req)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    JsonNode mNode = node.at("/messenger");

    if (req.messenger == null) {
      MessengerType mType = MessengerJacksonHelper.extractType(ctxt, p, mNode);
      req.messenger = MessengerJacksonHelper.newMessenger(mType);
    }

    if (p.getCodec() instanceof ObjectMapper) {
      // If the available codec supports it, read the JsonNode directly into the object
      ((ObjectMapper) p.getCodec()).readerForUpdating(req.messenger).readValue(mNode);
    } else if (p.getCodec() instanceof ObjectReader) {
      // Otherwise read the JsonNode and assign it to the request
      req.messenger = ((ObjectReader) p.getCodec()).readValue(mNode, req.messenger.getClass());
    }
    return req;
  }
}
