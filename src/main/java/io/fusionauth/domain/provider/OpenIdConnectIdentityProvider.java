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
package io.fusionauth.domain.provider;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class OpenIdConnectIdentityProvider extends BaseIdentityProvider<OpenIdConnectApplicationConfiguration> implements Buildable<OpenIdConnectIdentityProvider>, DomainBasedIdentityProvider {
  public final Set<String> domains = new HashSet<>();

  @InternalJSONColumn
  public URI buttonImageURL;

  @InternalJSONColumn
  public String buttonText;

  @InternalJSONColumn
  public IdentityProviderOauth2Configuration oauth2 = new IdentityProviderOauth2Configuration();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OpenIdConnectIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    OpenIdConnectIdentityProvider that = (OpenIdConnectIdentityProvider) o;
    return Objects.equals(domains, that.domains) &&
           Objects.equals(buttonImageURL, that.buttonImageURL) &&
           Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(lambdaConfiguration, that.lambdaConfiguration) &&
           Objects.equals(oauth2, that.oauth2);
  }

  @Override
  public Set<String> getDomains() {
    return domains;
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.OpenIDConnect;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), domains, buttonImageURL, buttonText, oauth2);
  }

  public URI lookupButtonImageURL(String clientId) {
    return lookup(() -> buttonImageURL, () -> app(clientId, app -> app.buttonImageURL));
  }

  public URI lookupButtonImageURL(UUID applicationId) {
    return lookup(() -> buttonImageURL, () -> app(applicationId, app -> app.buttonImageURL));
  }

  public String lookupButtonText(String clientId) {
    return lookup(() -> buttonText, () -> app(clientId, app -> app.buttonText));
  }

  public String lookupButtonText(UUID applicationId) {
    return lookup(() -> buttonText, () -> app(applicationId, app -> app.buttonText));
  }

  public String lookupClientId(String clientId) {
    return lookup(() -> oauth2.client_id, () -> app(clientId, app -> app.oauth2.client_id));
  }

  public String lookupClientId(UUID applicationId) {
    return lookup(() -> oauth2.client_id, () -> app(applicationId, app -> app.oauth2.client_id));
  }

  public String lookupClientSecret(UUID applicationId) {
    return lookup(() -> oauth2.client_secret, () -> app(applicationId, app -> app.oauth2.client_secret));
  }

  public String lookupScope(String clientId) {
    return lookup(() -> oauth2.scope, () -> app(clientId, app -> app.oauth2.scope));
  }

  public String lookupScope(UUID applicationId) {
    return lookup(() -> oauth2.scope, () -> app(applicationId, app -> app.oauth2.scope));
  }

  @Override
  public void normalize() {
    super.normalize();
    normalizeDomains();
  }

  @Override
  public OpenIdConnectIdentityProvider secure() {
    if (oauth2 != null) {
      oauth2.secure();
    }

    domains.clear();
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
