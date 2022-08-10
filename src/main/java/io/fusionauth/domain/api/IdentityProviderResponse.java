/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.IdentityProviderResponseDeserializer;
import io.fusionauth.domain.provider.BaseIdentityProvider;

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
