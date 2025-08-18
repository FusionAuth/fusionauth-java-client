/*
 * Copyright (c) 2021-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.PreviewMessageTemplateRequest;
import io.fusionauth.domain.message.MessageType;

/**
 * @author Mikey Sleevi
 */
public class PreviewMessageTemplateRequestDeserializer extends StdDeserializer<PreviewMessageTemplateRequest> {
  public PreviewMessageTemplateRequestDeserializer() {
    super(PreviewMessageTemplateRequest.class);
  }

  @Override
  public PreviewMessageTemplateRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new PreviewMessageTemplateRequest());
  }

  @Override
  public PreviewMessageTemplateRequest deserialize(JsonParser p, DeserializationContext ctxt, PreviewMessageTemplateRequest req)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    JsonNode mNode = node.at("/messageTemplate");
    // TODO : MFA Review : This seems jenky carrying along locale in this custom deserializer...? Perhaps there's a better way?
    JsonNode lNode = node.at("/locale");

    if (req.messageTemplate == null) {
      MessageType mType = MessageTemplateJacksonHelper.extractType(ctxt, p, mNode);
      req.messageTemplate = MessageTemplateJacksonHelper.newMessageTemplate(mType);
    }

    if (req.locale == null) {
      req.locale = new Locale(lNode.asText());
    }

    ((ObjectMapper) p.getCodec()).readerForUpdating(req.messageTemplate).readValue(mNode);
    return req;
  }
}
