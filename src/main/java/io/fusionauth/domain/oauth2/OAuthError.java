/*
 * Copyright (c) 2018-2025, FusionAuth, All Rights Reserved
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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.TwoFactorMethod;

/**
 * @author Daniel DeGroff
 */
public class OAuthError implements OAuthResponse {
  @JsonProperty("change_password_id")
  public String changePasswordId;

  @JsonProperty("error_description")
  public String description;

  public OAuthErrorType error;

  @SuppressWarnings("unused")
  @JsonProperty("error_uri")
  public String errorURI;

  @JsonProperty("two_factor_methods")
  public List<TwoFactorMethod> methods;

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
    access_token_invalid,
    access_token_required,

    refresh_token_not_found,
    refresh_token_type_not_supported, // Deprecated
    id_token_invalid,
    unsupported_token_type,
    token_type_hint_mismatch,

    // Invalid request parameters
    invalid_client_id,
    invalid_expires_in,
    invalid_user_credentials,
    invalid_grant_type,
    invalid_origin,
    invalid_origin_opaque,
    invalid_pkce_code_verifier,
    invalid_pkce_code_challenge,
    invalid_pkce_code_challenge_method,
    invalid_prompt,
    invalid_redirect_uri,
    invalid_response_mode,
    invalid_response_type,
    invalid_id_token_hint,
    invalid_post_logout_redirect_uri,
    invalid_device_code,
    invalid_user_code,
    invalid_additional_client_id,
    invalid_target_entity_scope,
    invalid_entity_permission_scope,
    invalid_user_id,
    invalid_tenant_id,

    // Grant disabled
    grant_type_disabled,

    // Missing request parameters
    missing_client_id,
    missing_client_secret,
    missing_code,
    missing_code_challenge,
    missing_code_verifier,
    missing_device_code,
    missing_grant_type,
    missing_redirect_uri,
    missing_refresh_token,
    missing_response_type,
    missing_token,
    missing_user_code,
    missing_user_id,
    missing_verification_uri,
    missing_tenant_id,

    login_prevented,
    not_licensed,
    user_code_expired,
    user_expired,
    user_locked,
    user_not_found,

    // Bad client authentication
    client_authentication_missing,
    invalid_client_authentication_scheme,
    invalid_client_authentication,
    client_id_mismatch,

    // Change password reasons
    change_password_administrative,
    change_password_breached,
    change_password_expired,
    change_password_validation,

    unknown,
    missing_required_scope,
    unknown_scope,
    consent_canceled,

    // reasons for login_required
    authentication_required,
    email_verification_required,
    multi_factor_challenge_required,
    phone_verification_required,
    registration_missing_requirement,
    registration_required,
    registration_verification_required,

    // reasons for consent_required
    consent_required
  }

  public enum OAuthErrorType {
    // RFC 6749 Error Response types
    invalid_request,
    invalid_client,
    invalid_grant,
    // Described in section 5.3.3 of the OpenID Connect Core https://openid.net/specs/openid-connect-core-1_0.html#UserInfoError
    invalid_token,

    // OpenID Connect Core section 3.1.2.6
    consent_required,
    interaction_required,
    login_required,

    unauthorized_client,
    invalid_scope,
    server_error,
    unsupported_grant_type,
    unsupported_response_type,
    access_denied,

    // RFC 6749 does not account for these states, so we invented them.
    change_password_required,
    not_licensed,
    two_factor_required,

    // RFC 8628 Device Authorization, section 3.5
    authorization_pending,
    expired_token,

    // Used in Introspect validation. See section 2.1 of RFC 7662 https://tools.ietf.org/html/rfc7662
    // - Values for 'token_type_hint' are defined by RFC 7009 "OAuth Token Type Hints". https://tools.ietf.org/html/rfc7009
    // - Validation for this field is described in section 4.1.1 of RFC 7009.
    unsupported_token_type
  }
}
