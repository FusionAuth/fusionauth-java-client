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
package io.fusionauth.domain.api.identityProvider;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.ExternalJWTIdentityProvider;
import io.fusionauth.domain.provider.IdentityProviderOauth2Configuration;
import io.fusionauth.domain.provider.IdentityProviderType;
import io.fusionauth.domain.provider.OpenIdConnectIdentityProvider;
import io.fusionauth.domain.provider.SAMLv2IdentityProvider;

/**
 * @author Daniel DeGroff
 */
public class LookupResponse {
  public IdentityProviderDetails identityProvider;

  @JacksonConstructor
  public LookupResponse() {
  }

  public LookupResponse(BaseIdentityProvider<?> identityProvider) {
    this.identityProvider = new IdentityProviderDetails();
    this.identityProvider.id = identityProvider.id;
    this.identityProvider.name = identityProvider.name;
    this.identityProvider.type = identityProvider.getType();
    // Add all enabled application Ids
    identityProvider.applicationConfiguration.entrySet().stream()
                                             .filter(map -> map.getValue().enabled)
                                             .forEach(entry -> this.identityProvider.applicationIds.add(entry.getKey()));

    if (identityProvider instanceof ExternalJWTIdentityProvider) {
      this.identityProvider.oauth2 = ((ExternalJWTIdentityProvider) identityProvider).oauth2;
    }

    if (identityProvider instanceof OpenIdConnectIdentityProvider) {
      this.identityProvider.oauth2 = ((OpenIdConnectIdentityProvider) identityProvider).oauth2;
      this.identityProvider.oauth2.emailClaim = null;
    }

    if (identityProvider instanceof SAMLv2IdentityProvider) {
      this.identityProvider.idpEndpoint = ((SAMLv2IdentityProvider) identityProvider).idpEndpoint;
    }
  }

  public static class IdentityProviderDetails {
    public List<UUID> applicationIds = new ArrayList<>();

    public UUID id;

    public URI idpEndpoint;

    public String name;

    public IdentityProviderOauth2Configuration oauth2;

    public IdentityProviderType type;

  }
}
