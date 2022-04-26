/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.user;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * @author Daniel DeGroff
 */
public class VerifyEmailRequest extends BaseEventRequest {
  public String oneTimeCode;

  public UUID userId;

  public String verificationId;

  @JacksonConstructor
  public VerifyEmailRequest() {
  }

  public VerifyEmailRequest(String oneTimeCode, String verificationId) {
    this.oneTimeCode = oneTimeCode;
    this.verificationId = verificationId;
  }

  public VerifyEmailRequest(String verificationId) {
    this.verificationId = verificationId;
  }

  public VerifyEmailRequest(EventInfo eventInfo, String oneTimeCode, String verificationId) {
    super(eventInfo);
    this.oneTimeCode = oneTimeCode;
    this.verificationId = verificationId;
  }

  public VerifyEmailRequest(EventInfo eventInfo, String verificationId) {
    super(eventInfo);
    this.verificationId = verificationId;
  }

  public VerifyEmailRequest(UUID userId) {
    this.userId = userId;
  }
}
