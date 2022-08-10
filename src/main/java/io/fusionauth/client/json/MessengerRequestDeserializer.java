/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    ((ObjectMapper) p.getCodec()).readerForUpdating(req.messenger).readValue(mNode);
    return req;
  }
}
