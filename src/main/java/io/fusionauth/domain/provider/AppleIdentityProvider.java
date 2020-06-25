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
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.CORSConfiguration;
import io.fusionauth.domain.RequiresCORSConfiguration;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.jwks.JSONWebKeyInfoProvider;
import io.fusionauth.domain.util.HTTPMethod;

/**
 * @author Daniel DeGroff
 */
public class AppleIdentityProvider extends BaseIdentityProvider<AppleApplicationConfiguration> implements Buildable<AppleIdentityProvider>, JSONWebKeyInfoProvider, RequiresCORSConfiguration {
  public static final URI ISSUER = URI.create("https://appleid.apple.com");

  public static final URI JWKS_URI = URI.create("https://appleid.apple.com/auth/keys");

  @InternalJSONColumn
  public String buttonText;

  public UUID keyId;

  @InternalJSONColumn
  public String scope;

  @InternalJSONColumn
  public String servicesId;

  @InternalJSONColumn
  public String teamId;

  @Override
  @JsonIgnore
  public CORSConfiguration corsConfiguration() {
    return new CORSConfiguration().with(override -> override.allowedMethods.add(HTTPMethod.POST))
                                  .with(override -> override.allowedOrigins.add(issuer()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AppleIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    AppleIdentityProvider that = (AppleIdentityProvider) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(keyId, that.keyId) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(servicesId, that.servicesId) &&
           Objects.equals(teamId, that.teamId);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Apple;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, keyId, scope, servicesId, teamId);
  }

  @Override
  public URI issuer() {
    return ISSUER;
  }

  @Override
  public URI jwksURI() {
    return JWKS_URI;
  }

  public String lookupButtonText(String clientId) {
    return lookup(() -> buttonText, () -> app(clientId, app -> app.buttonText));
  }

  public String lookupButtonText(UUID applicationId) {
    return lookup(() -> buttonText, () -> app(applicationId, app -> app.buttonText));
  }

  public UUID lookupKeyId(UUID applicationId) {
    return lookup(() -> keyId, () -> app(applicationId, app -> app.keyId));
  }

  public String lookupScope(String clientId) {
    return lookup(() -> scope, () -> app(clientId, app -> app.scope));
  }

  public String lookupScope(UUID applicationId) {
    return lookup(() -> scope, () -> app(applicationId, app -> app.scope));
  }

  public String lookupServicesId(String clientId) {
    return lookup(() -> servicesId, () -> app(clientId, app -> app.servicesId));
  }

  public String lookupServicesId(UUID applicationId) {
    return lookup(() -> servicesId, () -> app(applicationId, app -> app.servicesId));
  }

  public String lookupTeamId(String clientId) {
    return lookup(() -> teamId, () -> app(clientId, app -> app.teamId));
  }

  public String lookupTeamId(UUID applicationId) {
    return lookup(() -> teamId, () -> app(applicationId, app -> app.teamId));
  }

  @Override
  public void normalize() {
    super.normalize();
  }

  @Override
  public AppleIdentityProvider secure() {
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
