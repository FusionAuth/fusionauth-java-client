/*
 * Copyright (c) 2026-2026, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.inversoft.error.Errors;
import io.fusionauth.domain.api.PreviewMessageTemplateResponse;
import io.fusionauth.domain.message.sms.SMSMessage;

/**
 * @author Daniel King
 */
public class PreviewMessageTemplateResponseDeserializer extends StdDeserializer<PreviewMessageTemplateResponse> {
  public PreviewMessageTemplateResponseDeserializer() {
    super(PreviewMessageTemplateResponse.class);
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

    JsonNode previewNode = node.at("/previewMessage");
    if (!previewNode.isMissingNode()) {
      response.previewMessage = previewNode.asText();
    }

    JsonNode messageNode = node.at("/message");
    if (!messageNode.isMissingNode()) {
      response.message = new SMSMessage();
      ((ObjectMapper) p.getCodec()).readerForUpdating(response.message).readValue(messageNode);
    }

    return response;
  }
}
