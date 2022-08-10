/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.MissingNode;
import io.fusionauth.domain.api.MessageTemplateResponse;
import io.fusionauth.domain.message.MessageTemplate;
import io.fusionauth.domain.message.MessageType;

/**
 * @author Mikey Sleevi
 */
public class MessageTemplateResponseDeserializer extends StdDeserializer<MessageTemplateResponse> {
  public MessageTemplateResponseDeserializer() {
    super(MessageTemplateResponse.class);
  }

  @Override
  public MessageTemplateResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new MessageTemplateResponse());
  }

  @Override
  public MessageTemplateResponse deserialize(JsonParser p, DeserializationContext ctxt, MessageTemplateResponse resp) throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    JsonNode messageTemplateNode = node.at("/messageTemplate");
    if (!messageTemplateNode.getClass().equals(MissingNode.class)) {
      MessageType messageType = MessageTemplateJacksonHelper.extractType(ctxt, p, messageTemplateNode);
      resp.messageTemplate = MessageTemplateJacksonHelper.newMessageTemplate(messageType);
      ((ObjectMapper) p.getCodec()).readerForUpdating(resp.messageTemplate).readValue(messageTemplateNode);
    }

    JsonNode messageTemplateNodes = node.at("/messageTemplates");
    if (!messageTemplateNodes.getClass().equals(MissingNode.class) && messageTemplateNodes.isArray()) {
      resp.messageTemplates = new ArrayList<>();
      for (JsonNode messageTemplate : messageTemplateNodes) {
        MessageType messageType = MessageTemplateJacksonHelper.extractType(ctxt, p, messageTemplate);
        MessageTemplate baseMessageTemplate = MessageTemplateJacksonHelper.newMessageTemplate(messageType);
        ((ObjectMapper) p.getCodec()).readerForUpdating(baseMessageTemplate).readValue(messageTemplate);
        resp.messageTemplates.add(baseMessageTemplate);
      }
    }

    return resp;
  }
}
