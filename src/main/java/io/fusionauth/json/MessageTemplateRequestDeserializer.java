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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    ((ObjectMapper) p.getCodec()).readerForUpdating(req.messageTemplate).readValue(mNode);
    return req;
  }
}
