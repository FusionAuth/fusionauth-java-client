/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Authorization Grant types as defined by the <a href="https://tools.ietf.org/html/rfc6749">The OAuth 2.0 Authorization
 * Framework - RFC 6749</a>.
 * <p>
 * Specific names as defined by <a href="https://tools.ietf.org/html/rfc7591#section-4.1">
 * OAuth 2.0 Dynamic Client Registration Protocol - RFC 7591 Section 4.1</a>
 *
 * @author Daniel DeGroff
 */
public enum GrantType {
  // Authorization Code Grant
  authorization_code("authorization_code"),

  // Implicit Grant
  implicit("implicit"),

  // Resource Owner Password Credentials Grant
  password("password"),

  // Client Credentials Grant
  client_credentials("client_credentials"),

  // Refresh Token Grant
  refresh_token("refresh_token"),

  // Unknown
  unknown("unknown"),

  // Device Code
  device_code("urn:ietf:params:oauth:grant-type:device_code");

  private static final Map<String, GrantType> nameMap = new HashMap<>(GrantType.values().length);

  private final String grantName;

  GrantType(final String grantName) {
    this.grantName = grantName;
  }

  @JsonCreator
  public static GrantType forValue(String value) {
    GrantType grantType = nameMap.get(value);
    if (grantType != null) {
      return grantType;
    }

    return unknown;
  }

  @JsonValue
  public String grantName() {
    return grantName;
  }

  static {
    for (GrantType grantType : GrantType.values()) {
      nameMap.put(grantType.grantName(), grantType);
    }
  }
}

