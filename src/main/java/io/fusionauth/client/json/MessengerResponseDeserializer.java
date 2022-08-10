/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.MessengerResponse;
import io.fusionauth.domain.messenger.BaseMessengerConfiguration;
import io.fusionauth.domain.messenger.MessengerType;

/**
 * Custom JSON de-serializer for MessengerResponse.
 *
 * @author Brett Guy
 */
public class MessengerResponseDeserializer extends StdDeserializer<MessengerResponse> {
  public MessengerResponseDeserializer() {
    super(MessengerResponse.class);
  }

  @Override
  public MessengerResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new MessengerResponse());
  }

  @Override
  public MessengerResponse deserialize(JsonParser p, DeserializationContext ctxt, MessengerResponse resp) throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    JsonNode messengerNode = node.at("/messenger");
    if (messengerNode != null) {
      MessengerType messengerType = MessengerJacksonHelper.extractType(ctxt, p, messengerNode);
      resp.messenger = MessengerJacksonHelper.newMessenger(messengerType);
      ((ObjectMapper) p.getCodec()).readerForUpdating(resp.messenger).readValue(messengerNode);
    }

    JsonNode messengerNodes = node.at("/messengers");
    if (messengerNodes != null && messengerNodes.isArray()) {
      resp.messengers = new ArrayList<>();
      for (JsonNode messenger : messengerNodes) {
        MessengerType messengerType = MessengerJacksonHelper.extractType(ctxt, p, messenger);
        BaseMessengerConfiguration baseMessengerConfiguration = MessengerJacksonHelper.newMessenger(messengerType);
        ((ObjectMapper) p.getCodec()).readerForUpdating(baseMessengerConfiguration).readValue(messenger);
        resp.messengers.add(baseMessengerConfiguration);
      }
    }

    return resp;
  }
}
