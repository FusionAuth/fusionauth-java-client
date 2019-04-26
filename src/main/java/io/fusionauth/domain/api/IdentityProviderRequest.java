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
package io.fusionauth.domain.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.ExternalJWTIdentityProvider;
import io.fusionauth.domain.provider.FacebookIdentityProvider;
import io.fusionauth.domain.provider.GoogleIdentityProvider;
import io.fusionauth.domain.provider.OpenIdConnectIdentityProvider;
import io.fusionauth.domain.provider.SAMLv2IdentityProvider;
import io.fusionauth.domain.provider.TwitterIdentityProvider;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderRequest {
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type", defaultImpl = ExternalJWTIdentityProvider.class)
  @JsonSubTypes({
      @Type(value = ExternalJWTIdentityProvider.class, name = "ExternalJWT"),
      @Type(value = FacebookIdentityProvider.class, name = "Facebook"),
      @Type(value = GoogleIdentityProvider.class, name = "Google"),
      @Type(value = OpenIdConnectIdentityProvider.class, name = "OpenIDConnect"),
      @Type(value = TwitterIdentityProvider.class, name = "Twitter"),
      @Type(value = SAMLv2IdentityProvider.class, name = "SAMLv2")
  })
  public BaseIdentityProvider<?> identityProvider;

  @JacksonConstructor
  public IdentityProviderRequest() {
  }

  public IdentityProviderRequest(BaseIdentityProvider<?> identityProvider) {
    this.identityProvider = identityProvider;
  }
}
