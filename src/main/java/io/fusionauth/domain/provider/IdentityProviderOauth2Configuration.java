/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.provider;

import java.net.URI;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderOauth2Configuration implements Buildable<IdentityProviderOauth2Configuration> {
  /**
   * <code>authorization_endpoint</code> is the OpenId well-known name for the Authorization endpoint.
   */
  public URI authorization_endpoint;

  /**
   * Allow override of the default client authentication method client_secret_basic
   * see https://openid.net/specs/openid-connect-core-1_0.html#ClientAuthentication
   */
  
  public ClientAuthenticationMethod clientAuthenticationMethod = ClientAuthenticationMethod.client_secret_basic;

  public String client_id;

  public String client_secret;

  public String emailClaim = "email";

  public String emailVerifiedClaim = "email_verified";

  public URI issuer;

  public String scope;

  /**
   * <code>token_endpoint</code> is the OpenId well-known name for the Token endpoint.
   */
  public URI token_endpoint;

  public String uniqueIdClaim = "sub";

  public URI userinfo_endpoint;

  public String usernameClaim = "preferred_username";

  @JacksonConstructor
  public IdentityProviderOauth2Configuration() {
  }

  public IdentityProviderOauth2Configuration(IdentityProviderOauth2Configuration other) {
    authorization_endpoint = other.authorization_endpoint;
    clientAuthenticationMethod = other.clientAuthenticationMethod;
    client_id = other.client_id;
    client_secret = other.client_secret;
    emailClaim = other.emailClaim;
    emailVerifiedClaim = other.emailVerifiedClaim;
    issuer = other.issuer;
    scope = other.scope;
    token_endpoint = other.token_endpoint;
    uniqueIdClaim = other.uniqueIdClaim;
    userinfo_endpoint = other.userinfo_endpoint;
    usernameClaim = other.usernameClaim;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdentityProviderOauth2Configuration)) {
      return false;
    }
    IdentityProviderOauth2Configuration that = (IdentityProviderOauth2Configuration) o;
    return Objects.equals(authorization_endpoint, that.authorization_endpoint) &&
           clientAuthenticationMethod == that.clientAuthenticationMethod &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(client_secret, that.client_secret) &&
           Objects.equals(emailClaim, that.emailClaim) &&
           Objects.equals(emailVerifiedClaim, that.emailVerifiedClaim) &&
           Objects.equals(issuer, that.issuer) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(token_endpoint, that.token_endpoint) &&
           Objects.equals(userinfo_endpoint, that.userinfo_endpoint) &&
           Objects.equals(usernameClaim, that.usernameClaim);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorization_endpoint, clientAuthenticationMethod, client_id, client_secret, emailClaim, emailVerifiedClaim, issuer, scope, token_endpoint, userinfo_endpoint, usernameClaim);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum ClientAuthenticationMethod {
    none,
    client_secret_basic,
    client_secret_post
  }
}
