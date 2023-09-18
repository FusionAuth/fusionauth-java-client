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
import io.fusionauth.domain.api.MessageTemplateRequest;
import io.fusionauth.domain.message.MessageType;

/**
 * @author Mikey Sleevi
 */
public class MessageTemplateRequestDeserializer extends StdDeserializer<MessageTemplateRequest> {
  public MessageTemplateRequestDeserializer() {
    super(MessageTemplateRequest.class);
  }

  @Override
  public MessageTemplateRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new MessageTemplateRequest());
  }

  @Override
  public MessageTemplateRequest deserialize(JsonParser p, DeserializationContext ctxt, MessageTemplateRequest req)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    JsonNode mNode = node.at("/messageTemplate");

    if (req.messageTemplate == null) {
      MessageType mType = MessageTemplateJacksonHelper.extractType(ctxt, p, mNode);
      req.messageTemplate = MessageTemplateJacksonHelper.newMessageTemplate(mType);
    }

    if (p.getCodec() instanceof ObjectMapper) {
      // If the available codec supports it, read the JsonNode directly into the object
      ((ObjectMapper) p.getCodec()).readerForUpdating(req.messageTemplate).readValue(mNode);
    } else if (p.getCodec() instanceof ObjectReader) {
      // Otherwise read the JsonNode and assign it to the request
      req.messageTemplate = ((ObjectReader) p.getCodec()).readValue(mNode, req.messageTemplate.getClass());
    }
    return req;
  }
}
