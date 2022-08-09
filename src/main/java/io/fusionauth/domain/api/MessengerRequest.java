/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.MessengerRequestDeserializer;
import io.fusionauth.domain.messenger.BaseMessengerConfiguration;

/**
 * @author Brett Guy
 */
@JsonDeserialize(using = MessengerRequestDeserializer.class)
public class MessengerRequest {
  public BaseMessengerConfiguration messenger;

  @JacksonConstructor
  public MessengerRequest() {
  }

  public MessengerRequest(BaseMessengerConfiguration messenger) {
    this.messenger = messenger;
  }
}
