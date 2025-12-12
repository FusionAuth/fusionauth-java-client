/*
 * Copyright (c) 2025, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.MultiFactorAction;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * Check the status of two-factor authentication for a user, with more options than on a GET request.
 */
public class TwoFactorStatusRequest extends BaseEventRequest {
  // required
  public final UUID userId;

  public MultiFactorAction action = MultiFactorAction.login;

  public UUID applicationId;

  public String token;

  public String twoFactorTrustId;

  public TwoFactorStatusRequest(UUID userId) {
    this.userId = userId;
  }

  @JacksonConstructor
  private TwoFactorStatusRequest() {
    // will be overridden by Jackson
    this.userId = null;
  }
}
