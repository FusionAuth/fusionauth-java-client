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

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.ExternalJWTIdentityProvider;
import io.fusionauth.domain.provider.IdentityProviderOauth2Configuration;
import io.fusionauth.domain.provider.IdentityProviderType;
import io.fusionauth.domain.provider.OpenIdConnectIdentityProvider;

/**
 * @author Daniel DeGroff
 */
public class LookupResponse {
  public LookupIdentityProviderDetails identityProvider;

  @JacksonConstructor
  public LookupResponse() {
  }

  public LookupResponse(BaseIdentityProvider<?> identityProvider) {
    this.identityProvider = new LookupIdentityProviderDetails();
    this.identityProvider.id = identityProvider.id;
    this.identityProvider.name = identityProvider.name;
    this.identityProvider.type = identityProvider.getType();

    if (identityProvider instanceof ExternalJWTIdentityProvider) {
      this.identityProvider.oauth2 = ((ExternalJWTIdentityProvider) identityProvider).oauth2;
    }

    if (identityProvider instanceof OpenIdConnectIdentityProvider) {
      this.identityProvider.oauth2 = ((OpenIdConnectIdentityProvider) identityProvider).oauth2;
    }
  }

  public static class LookupIdentityProviderDetails {
    public UUID id;

    public String name;

    public IdentityProviderOauth2Configuration oauth2;

    public IdentityProviderType type;

  }
}
