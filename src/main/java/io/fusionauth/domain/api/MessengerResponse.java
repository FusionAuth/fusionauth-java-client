/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.MessengerResponseDeserializer;
import io.fusionauth.domain.messenger.BaseMessengerConfiguration;

/**
 * @author Brett Guy
 */
@JsonDeserialize(using = MessengerResponseDeserializer.class)
public class MessengerResponse {
  public BaseMessengerConfiguration messenger;

  public List<BaseMessengerConfiguration> messengers;

  @JacksonConstructor
  public MessengerResponse() {
  }

  public MessengerResponse(BaseMessengerConfiguration messenger) {
    this.messenger = messenger;
  }

  public MessengerResponse(List<BaseMessengerConfiguration> messengers) {
    this.messengers = messengers;
  }
}
