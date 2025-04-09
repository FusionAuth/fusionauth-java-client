/*
 * Copyright (c) 2021-2025, FusionAuth, All Rights Reserved
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * @author Brett Guy
 */
public class TwoFactorStartRequest implements Buildable<TwoFactorStartRequest> {
  public UUID applicationId;

  public String code;

  public String loginId;

  public List<String> loginIdTypes = new ArrayList<>();

  public Map<String, Object> state;

  public String trustChallenge;

  public UUID userId;

  @JacksonConstructor
  public TwoFactorStartRequest() {
  }

  public TwoFactorStartRequest(UUID applicationId, String code, UUID userId) {
    this.applicationId = applicationId;
    this.code = code;
    this.userId = userId;
  }

  @SuppressWarnings("unused")
  public TwoFactorStartRequest(UUID applicationId, String code, String loginId) {
    this.applicationId = applicationId;
    this.code = code;
    this.loginId = loginId;
  }
}
