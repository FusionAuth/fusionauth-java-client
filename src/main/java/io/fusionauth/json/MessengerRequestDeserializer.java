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
