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
  authorization_code,

  // Implicit Grant
  implicit,

  // Resource Owner Password Credentials Grant
  password,

  // Client Credentials Grant
  client_credentials,

  // Refresh Token Grant
  refresh_token,

  // Unknown
  unknown;

  /**
   * Lookup the Grant Type from the provided <code>response_type</code>.
   *
   * @param response_type the response_type
   * @return the grant type
   */
  public static GrantType lookupResponseType(String response_type) {
    switch (response_type) {
      case "code":
        return authorization_code;
      case "token":
      case "token id_token":
      case "id_token":
      case "id_token token":
        return implicit;
      default:
        return unknown;
    }
  }
}

