/*
 * Copyright (c) 2020-2026, FusionAuth, All Rights Reserved
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
import com.inversoft.error.Errors;
import io.fusionauth.client.json.PreviewMessageTemplateResponseDeserializer;
import io.fusionauth.domain.message.Message;
import io.fusionauth.domain.message.sms.SMSMessage;

/**
 * @author Michael Sleevi
 */
@JsonDeserialize(using = PreviewMessageTemplateResponseDeserializer.class)
public class PreviewMessageTemplateResponse {
  public Errors errors;

  /**
   * @deprecated value will be null if the requested template is of type {@code VoiceMessageTemplate}. Use {@code previewMessage} instead
   */
  @Deprecated
  public SMSMessage message;

  public Message previewMessage;
}
