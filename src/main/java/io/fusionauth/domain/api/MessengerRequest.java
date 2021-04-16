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
package io.fusionauth.domain.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.messenger.BaseMessengerConfiguration;
import io.fusionauth.json.MessengerRequestDeserializer;

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
