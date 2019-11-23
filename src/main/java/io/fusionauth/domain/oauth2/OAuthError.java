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

  public OAuthErrorType error;

  @JsonProperty("error_uri")
  public String errorURI;

  @JsonProperty("error_reason")
  public OAuthErrorReason reason;

  @JsonProperty("two_factor_id")
  public String twoFactorId;

  @JacksonConstructor
  public OAuthError() {
  }

  public OAuthError(OAuthErrorType error, OAuthErrorReason reason, String description) {
    this.error = error;
    this.reason = reason;
    this.description = description;
  }

  public OAuthError(OAuthErrorType error, String description) {
    this.description = description;
    this.error = error;
  }

  public String toString() {
    return ToString.toString(this);
  }

  public enum OAuthErrorReason {
    auth_code_not_found,

    access_token_malformed,
    access_token_expired,
    access_token_unavailable_for_processing,
    access_token_failed_processing,

    refresh_token_not_found,

    // Invalid request parameters
    invalid_client_id,
    invalid_user_credentials,
    invalid_grant_type,
    invalid_origin,
    invalid_origin_opaque,
    invalid_pkce_code_verifier,
    invalid_pkce_code_challenge,
    invalid_pkce_code_challenge_method,
    invalid_redirect_uri,
    invalid_response_mode,
    invalid_response_type,
    invalid_id_token_hint,
    invalid_post_logout_redirect_uri,
    invalid_device_code,
    invalid_user_code,
    invalid_additional_client_id,

    // Grant disabled
    grant_type_disabled,

    // Missing request parameters
    missing_client_id,
    missing_code,
    missing_device_code,
    missing_grant_type,
    missing_redirect_uri,
    missing_refresh_token,
    missing_response_type,
    missing_token,
    missing_user_code,
    missing_verification_uri,

    login_prevented,
    user_code_expired,
    user_expired,
    user_locked,
    user_not_found,

    // Bad client authentication
    client_authentication_missing,
    invalid_client_authentication_scheme,
    invalid_client_authentication,
    client_id_mismatch,

    unknown
  }

  public enum OAuthErrorType {
    // RFC 6749 Error Response types
    invalid_request,
    invalid_client,
    invalid_grant,
    // Described in section 5.3.3 of the OpenID Connect Core https://openid.net/specs/openid-connect-core-1_0.html#UserInfoError
    invalid_token,

    unauthorized_client,
    invalid_scope,
    server_error,
    unsupported_grant_type,
    unsupported_response_type,

    // RFC 6749 does not account for these states, so we invented them.
    change_password_required,
    two_factor_required,

    // RFC 8628 Device Authorization
    authorization_pending,
    expired_token
  }
}
