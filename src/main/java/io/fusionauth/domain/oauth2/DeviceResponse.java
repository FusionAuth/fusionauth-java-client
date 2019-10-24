/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceResponse that = (DeviceResponse) o;
    return Objects.equals(deviceCode, that.deviceCode) &&
        Objects.equals(expiresIn, that.expiresIn) &&
        Objects.equals(interval, that.interval) &&
        Objects.equals(userCode, that.userCode) &&
        Objects.equals(verificationURI, that.verificationURI) &&
        Objects.equals(verificationURIComplete, that.verificationURIComplete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deviceCode, expiresIn, interval, userCode, verificationURI, verificationURIComplete);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
