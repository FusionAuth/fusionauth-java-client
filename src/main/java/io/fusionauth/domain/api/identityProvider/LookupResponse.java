/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.identityProvider;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.IdentityProvider;

/**
 * @author Daniel DeGroff
 */
public class LookupResponse {
  public IdentityProvider identityProvider;

  @JacksonConstructor
  public LookupResponse() {
  }

  public LookupResponse(IdentityProvider identityProvider) {
    this.identityProvider = identityProvider;
  }
}
