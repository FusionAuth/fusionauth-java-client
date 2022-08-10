/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;
import io.fusionauth.domain.message.MessageTemplate;
import io.fusionauth.domain.message.MessageType;
import io.fusionauth.domain.message.sms.SMSMessageTemplate;

public class MessageTemplateJacksonHelper {

  public static MessageType extractType(DeserializationContext ctxt, JsonParser p, JsonNode templateNode)
      throws IOException {
    JsonNode node = templateNode.at("/type");
    String type = node.asText();

    MessageType messengerType = MessageType.safeValueOf(type);
    if (messengerType == null) {
      // This does actually throw an exception, but this is how Jackson rolls.
      return (MessageType) ctxt.handleUnexpectedToken(BaseConnectorConfiguration.class, node.asToken(), p,
                                                      "Expected the type field to be one of " +
                                                      String.join(",", Arrays.toString(MessageType.values()) + ", but found [" +
                                                                       node.asText() + "]"));
    }

    return messengerType;
  }

  public static MessageTemplate newMessageTemplate(MessageType type) {
    //noinspection SwitchStatementWithTooFewBranches
    switch (type) {
      case SMS:
        return new SMSMessageTemplate();
      default:
        throw new IllegalStateException("Unexpected type [" + type + "]. This is a FusionAuth bug, someone forgot to add a case statement for a new type.");
    }
  }
}
