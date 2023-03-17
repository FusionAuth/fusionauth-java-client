/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.search.IdentityProviderSearchCriteria;

/**
 * Search request for Identity Providers
 *
 * @author Spencer Witt
 */
public class IdentityProviderSearchRequest {
  public IdentityProviderSearchCriteria search = new IdentityProviderSearchCriteria();

  @JacksonConstructor
  public IdentityProviderSearchRequest() {
  }

  public IdentityProviderSearchRequest(IdentityProviderSearchCriteria search) {
    this.search = search;
  }
}
