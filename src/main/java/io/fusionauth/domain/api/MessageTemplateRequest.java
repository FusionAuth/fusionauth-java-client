/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.MessageTemplateRequestDeserializer;
import io.fusionauth.domain.message.MessageTemplate;

/**
 * A Message Template Request to the API
 *
 * @author Michael Sleevi
 */
@JsonDeserialize(using = MessageTemplateRequestDeserializer.class)
public class MessageTemplateRequest {
  public MessageTemplate messageTemplate;

  @JacksonConstructor
  public MessageTemplateRequest() {
  }

  public MessageTemplateRequest(MessageTemplate messageTemplate) {
    this.messageTemplate = messageTemplate;
  }
}
