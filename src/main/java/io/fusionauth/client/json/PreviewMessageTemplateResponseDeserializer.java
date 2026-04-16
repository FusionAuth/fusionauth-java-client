/*
 * Copyright (c) 2026-2026, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.inversoft.error.Errors;
import io.fusionauth.domain.api.PreviewMessageTemplateResponse;
import io.fusionauth.domain.message.Message;
import io.fusionauth.domain.message.MessageType;
import io.fusionauth.domain.message.sms.SMSMessage;
import io.fusionauth.domain.message.voice.VoiceMessage;

/**
 * @author Daniel King
 */
public class PreviewMessageTemplateResponseDeserializer extends StdDeserializer<PreviewMessageTemplateResponse> {
  public PreviewMessageTemplateResponseDeserializer() {
    super(PreviewMessageTemplateResponse.class);
  }

  private static MessageType extractType(DeserializationContext ctxt, JsonParser p, JsonNode messageNode) throws IOException {
    JsonNode node = messageNode.at("/type");
    MessageType type = MessageType.safeValueOf(node.asText());
    if (type == null) {
      String sorted = Arrays.stream(MessageType.values()).map(Enum::name).sorted().collect(Collectors.joining(", "));
      return (MessageType) ctxt.handleUnexpectedToken(Message.class, node.asToken(), p,
                                                      "Expected the type field to be one of [" + sorted + "], but found [" + node.asText() + "]");
    }

    return type;
  }

  @Override
  public PreviewMessageTemplateResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new PreviewMessageTemplateResponse());
  }

  @Override
  public PreviewMessageTemplateResponse deserialize(JsonParser p, DeserializationContext ctxt, PreviewMessageTemplateResponse response)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    JsonNode errorsNode = node.at("/errors");
    if (!errorsNode.isMissingNode()) {
      response.errors = p.getCodec().treeToValue(errorsNode, Errors.class);
    }

    JsonNode messageNode = node.at("/previewMessage");
    MessageType messageType = extractType(ctxt, p, messageNode);
    if (messageType == MessageType.Voice) {
      response.previewMessage = new VoiceMessage();
    } else {
      SMSMessage message = new SMSMessage();
      response.message = message;
      response.previewMessage = message;
    }
    ((ObjectMapper) p.getCodec()).readerForUpdating(response.previewMessage).readValue(messageNode);

    return response;
  }
}
