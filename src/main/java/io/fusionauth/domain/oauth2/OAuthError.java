/*
 * Copyright (c) 2018,FusionAuth, All Rights Reserved
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class OAuthError implements OAuthResponse {
  @JsonProperty("change_password_id")
  public String changePasswordId;

  @JsonProperty("error_description")
  public String description;

  @JsonProperty("error")
  public OAuthErrorType error;

  @JsonProperty("error_uri")
  public String errorURI;

  @JsonProperty("two_factor_id")
  public String twoFactorId;

  @JacksonConstructor
  public OAuthError() {
  }

  public OAuthError(OAuthErrorType error, String description) {
    this.description = description;
    this.error = error;
  }

  public String toString() {
    return ToString.toString(this);
  }

  public enum OAuthErrorType {
    invalid_request,
    invalid_client,
    invalid_grant,
    invalid_token,
    unauthorized_client,
    invalid_scope,
    server_error,
    change_password_required,
    two_factor_required,
    unsupported_grant_type
  }
}
