/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
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

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inversoft.json.JacksonConstructor;

/**
 * @author Trevor Smith
 */
public class DeviceResponse implements OAuthResponse {
  @JsonProperty("device_code")
  public String deviceCode;

  @JsonProperty("expires_in")
  public Integer expiresIn;

  @JsonProperty("interval")
  public Integer interval;

  @JsonProperty("user_code")
  public String userCode;

  @JsonProperty("verification_uri")
  public URI verificationURI;

  @JsonProperty("verification_uri_complete")
  public URI verificationURIComplete;

  @JacksonConstructor
  public DeviceResponse() {
  }

  public DeviceResponse(String deviceCode, Integer expiresIn, Integer interval, String userCode, URI verificationURI,
                        URI verificationURIComplete) {
    this.deviceCode = deviceCode;
    this.expiresIn = expiresIn;
    this.interval = interval;
    this.userCode = userCode;
    this.verificationURI = verificationURI;
    this.verificationURIComplete = verificationURIComplete;
  }
}
