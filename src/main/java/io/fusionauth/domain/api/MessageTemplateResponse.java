/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.MessageTemplateResponseDeserializer;
import io.fusionauth.domain.message.MessageTemplate;

/**
 * @author Michael Sleevi
 */
@JsonDeserialize(using = MessageTemplateResponseDeserializer.class)
public class MessageTemplateResponse {
  public MessageTemplate messageTemplate;

  public List<MessageTemplate> messageTemplates;

  @JacksonConstructor
  public MessageTemplateResponse() {
  }

  public MessageTemplateResponse(MessageTemplate messageTemplate) {
    this.messageTemplate = messageTemplate;
  }

  public MessageTemplateResponse(List<MessageTemplate> messageTemplates) {
    this.messageTemplates = messageTemplates;
  }
}
