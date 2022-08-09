/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.Locale;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.PreviewMessageTemplateRequestDeserializer;
import io.fusionauth.domain.message.MessageTemplate;

/**
 * @author Michael Sleevi
 */
@JsonDeserialize(using = PreviewMessageTemplateRequestDeserializer.class)
public class PreviewMessageTemplateRequest {
  public Locale locale;

  public MessageTemplate messageTemplate;

  @JacksonConstructor
  public PreviewMessageTemplateRequest() {
  }

  public PreviewMessageTemplateRequest(MessageTemplate messageTemplate, Locale locale) {
    this.messageTemplate = messageTemplate;
    this.locale = locale;
  }
}
