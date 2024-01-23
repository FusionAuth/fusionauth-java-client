/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.oauth2;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.jwt.DeviceInfo;
import io.fusionauth.domain.provider.PendingIdPLink;

/**
 * @author Daniel DeGroff
 */
public class DeviceUserCodeResponse implements Buildable<DeviceUserCodeResponse> {
  public String client_id;

  public DeviceInfo deviceInfo;

  @JsonProperty("expires_in")
  public Integer expiresIn;

  public PendingIdPLink pendingIdPLink;

  public UUID tenantId;

  public String user_code;
}