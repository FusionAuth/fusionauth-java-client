/*
 * Copyright (c) 2020-2024, FusionAuth, All Rights Reserved
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
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;
import io.fusionauth.domain.messenger.BaseMessengerConfiguration;
import io.fusionauth.domain.messenger.GenericMessengerConfiguration;
import io.fusionauth.domain.messenger.KafkaMessengerConfiguration;
import io.fusionauth.domain.messenger.MessengerType;
import io.fusionauth.domain.messenger.TwilioMessengerConfiguration;

/**
 * @author Brett Guy
 */
public class MessengerJacksonHelper {

  public static MessengerType extractType(DeserializationContext ctxt, JsonParser p, JsonNode connectorNode)
      throws IOException {
    JsonNode node = connectorNode.at("/type");
    String type = node.asText(MessengerType.Twilio.name());

    MessengerType messengerType = MessengerType.safeValueOf(type);
    if (messengerType == null) {
      // This does actually throw an exception, but this is how Jackson rolls.
      return (MessengerType) ctxt.handleUnexpectedToken(BaseConnectorConfiguration.class, node.asToken(), p,
                                                        "Expected the type field to be one of " +
                                                        String.join(",", Arrays.toString(MessengerType.values()) + ", but found [" +
                                                                         node.asText() + "]"));
    }

    return messengerType;
  }

  public static BaseMessengerConfiguration newMessenger(MessengerType type) {
    switch (type) {
      case Generic:
        return new GenericMessengerConfiguration();
      case Twilio:
        return new TwilioMessengerConfiguration();
      case Kafka:
        return new KafkaMessengerConfiguration();
      default:
        throw new IllegalStateException("Unexpected type [" + type + "]. This is a FusionAuth bug, someone forgot to add a case statement for a new type.");
    }
  }
}
