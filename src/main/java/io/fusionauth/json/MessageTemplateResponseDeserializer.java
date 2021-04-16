/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
