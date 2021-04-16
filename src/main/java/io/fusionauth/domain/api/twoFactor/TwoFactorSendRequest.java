/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.twoFactor;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class TwoFactorSendRequest implements Buildable<TwoFactorSendRequest> {
  public String email;

  public String method;

  public String methodId;

  public String mobilePhone;

  public UUID userId;

  @JacksonConstructor
  public TwoFactorSendRequest() {
  }

  public TwoFactorSendRequest(String methodId) {
    this.methodId = methodId;
  }

  public TwoFactorSendRequest(String method, UUID userId) {
    this.method = method;
    this.userId = userId;
  }

  public TwoFactorSendRequest(UUID userId) {
    this.userId = userId;
  }
}
