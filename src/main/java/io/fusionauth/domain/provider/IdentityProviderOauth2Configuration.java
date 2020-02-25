/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

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
  @InternalJSONColumn
  public ClientAuthenticationMethod clientAuthenticationMethod;

  public String client_id;

  public String client_secret;

  public URI issuer;

  public String scope;

  /**
   * <code>token_endpoint</code> is the OpenId well-known name for the Token endpoint.
   */
  public URI token_endpoint;

  public URI userinfo_endpoint;

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
        Objects.equals(client_id, that.client_id) &&
        Objects.equals(client_secret, that.client_secret) &&
        Objects.equals(issuer, that.issuer) &&
        Objects.equals(scope, that.scope) &&
        Objects.equals(token_endpoint, that.token_endpoint) &&
        Objects.equals(userinfo_endpoint, that.userinfo_endpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorization_endpoint, client_id, client_secret, issuer, scope, token_endpoint, userinfo_endpoint);
  }

  public IdentityProviderOauth2Configuration secure() {
    client_secret = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class ClientAuthenticationMethod {
    public boolean client_secret_post;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ClientAuthenticationMethod)) {
        return false;
      }
      ClientAuthenticationMethod that = (ClientAuthenticationMethod) o;
      return client_secret_post == that.client_secret_post;
    }

    @Override
    public int hashCode() {
      return Objects.hash(client_secret_post);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
