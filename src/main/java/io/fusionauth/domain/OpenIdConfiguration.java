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
package io.fusionauth.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.ToString;

/**
 * OpenID Connect Configuration as described by the <a href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata">OpenID
 * Provider Metadata</a>.
 *
 * @author Daniel DeGroff
 */
public class OpenIdConfiguration implements Buildable<OpenIdConfiguration> {
  public String authorization_endpoint = "%s/oauth2/authorize";

  @SuppressWarnings("SpellCheckingInspection")
  public boolean backchannel_logout_supported = false;

  public List<String> claims_supported = new ArrayList<>(Arrays.asList("applicationId", "at_hash", "aud", "authenticationType", "birthdate", "c_hash", "email", "email_verified", "exp", "family_name", "given_name", "iat", "iss", "jti", "middle_name", "name", "nbf", "nonce", "phone_number", "phone_number_verified", "picture", "preferred_username", "roles", "sub"));

  public String device_authorization_endpoint = "%s/oauth2/device_authorize";

  public String end_session_endpoint = "%s/oauth2/logout";

  @SuppressWarnings("SpellCheckingInspection")
  public boolean frontchannel_logout_supported = true;

  public List<String> grant_types_supported = new ArrayList<>(Arrays.asList("authorization_code", "password", "implicit", "refresh_token", "urn:ietf:params:oauth:grant-type:device_code", "client_credentials"));

  public List<String> id_token_signing_alg_values_supported = new ArrayList<>(Arrays.asList("ES256", "ES384", "ES512", "HS256", "HS384", "HS512", "RS256", "RS384", "RS512"));

  public String issuer;

  public String jwks_uri = "%s/.well-known/jwks.json";

  public List<String> response_modes_supported = new ArrayList<>(Arrays.asList("form_post", "fragment", "query"));

  public List<String> response_types_supported = new ArrayList<>(Arrays.asList("code", "id_token", "token id_token"));

  public List<String> scopes_supported = new ArrayList<>(Arrays.asList("openid", "offline_access", "email", "phone", "profile"));

  public List<String> subject_types_supported = new ArrayList<>(Collections.singletonList("public"));

  public String token_endpoint = "%s/oauth2/token";

  public List<String> token_endpoint_auth_methods_supported = new ArrayList<>(Arrays.asList("client_secret_basic", "client_secret_post", "none"));

  public String userinfo_endpoint = "%s/oauth2/userinfo";

  public List<String> userinfo_signing_alg_values_supported = new ArrayList<>(Arrays.asList("ES256", "ES384", "ES512", "RS256", "RS384", "RS512", "HS256", "HS384", "HS512"));

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OpenIdConfiguration)) {
      return false;
    }
    OpenIdConfiguration that = (OpenIdConfiguration) o;
    return backchannel_logout_supported == that.backchannel_logout_supported &&
           frontchannel_logout_supported == that.frontchannel_logout_supported &&
           Objects.equals(authorization_endpoint, that.authorization_endpoint) &&
           Objects.equals(claims_supported, that.claims_supported) &&
           Objects.equals(device_authorization_endpoint, that.device_authorization_endpoint) &&
           Objects.equals(end_session_endpoint, that.end_session_endpoint) &&
           Objects.equals(grant_types_supported, that.grant_types_supported) &&
           Objects.equals(id_token_signing_alg_values_supported, that.id_token_signing_alg_values_supported) &&
           Objects.equals(issuer, that.issuer) &&
           Objects.equals(jwks_uri, that.jwks_uri) &&
           Objects.equals(response_modes_supported, that.response_modes_supported) &&
           Objects.equals(response_types_supported, that.response_types_supported) &&
           Objects.equals(scopes_supported, that.scopes_supported) &&
           Objects.equals(subject_types_supported, that.subject_types_supported) &&
           Objects.equals(token_endpoint, that.token_endpoint) &&
           Objects.equals(token_endpoint_auth_methods_supported, that.token_endpoint_auth_methods_supported) &&
           Objects.equals(userinfo_endpoint, that.userinfo_endpoint) &&
           Objects.equals(userinfo_signing_alg_values_supported, that.userinfo_signing_alg_values_supported);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorization_endpoint, backchannel_logout_supported, claims_supported, device_authorization_endpoint, end_session_endpoint, frontchannel_logout_supported, grant_types_supported, id_token_signing_alg_values_supported, issuer, jwks_uri, response_modes_supported, response_types_supported, scopes_supported, subject_types_supported, token_endpoint, token_endpoint_auth_methods_supported, userinfo_endpoint, userinfo_signing_alg_values_supported);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
