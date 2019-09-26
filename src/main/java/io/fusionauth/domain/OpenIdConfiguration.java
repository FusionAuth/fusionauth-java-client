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
package io.fusionauth.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OpenID Configuration as described by the <a href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata">OpenID
 * Provider Metadata</a>.
 *
 * @author Daniel DeGroff
 */
public class OpenIdConfiguration implements Buildable<OpenIdConfiguration> {
  public String authorization_endpoint = "%s/oauth2/authorize";

  public boolean backchannel_logout_supported = false;

  public List<String> claims_supported = new ArrayList<>(Arrays.asList("applicationId", "aud", "authenticationType", "birthdate", "email", "email_verified", "exp", "family_name", "given_name", "iat", "iss", "middle_name", "name", "nbf", "phone_number", "picture", "preferred_username", "roles", "sub"));

  public String end_session_endpoint = "%s/oauth2/logout";

  public boolean frontchannel_logout_supported = true;

  public List<String> grant_types_supported = new ArrayList<>(Arrays.asList("authorization_code", "password", "implicit", "refresh_token"));

  public List<String> id_token_signing_alg_values_supported = new ArrayList<>(Arrays.asList("ES256", "ES384", "ES512", "HS256", "HS384", "HS512", "RS256", "RS384", "RS512"));

  public String issuer;

  public String jwks_uri = "%s/.well-known/jwks.json";

  public List<String> response_types_supported = new ArrayList<>(Arrays.asList("code", "id_token", "token id_token"));

  public List<String> scopes_supported = new ArrayList<>(Arrays.asList("openid", "offline_access"));

  public List<String> subject_types_supported = new ArrayList<>(Arrays.asList("public"));

  public String token_endpoint = "%s/oauth2/token";

  public List<String> token_endpoint_auth_methods_supported = new ArrayList<>(Arrays.asList("client_secret_basic", "client_secret_post", "none"));

  public String userinfo_endpoint = "%s/oauth2/userinfo";

  public List<String> userinfo_signing_alg_values_supported = new ArrayList<>(Arrays.asList("ES256", "ES384", "ES512", "RS256", "RS384", "RS512", "HS256", "HS384", "HS512"));
}
