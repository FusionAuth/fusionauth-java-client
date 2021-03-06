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

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.json.IdentityProviderResponseDeserializer;

/**
 * @author Daniel DeGroff
 */
@JsonDeserialize(using = IdentityProviderResponseDeserializer.class)
public class IdentityProviderResponse {
  public BaseIdentityProvider<?> identityProvider;

  public List<BaseIdentityProvider<?>> identityProviders;

  @JacksonConstructor
  public IdentityProviderResponse() {
  }

  public IdentityProviderResponse(List<BaseIdentityProvider<?>> identityProviders) {
    this.identityProviders = identityProviders;
  }

  public IdentityProviderResponse(BaseIdentityProvider<?> identityProvider) {
    this.identityProvider = identityProvider;
  }
}
