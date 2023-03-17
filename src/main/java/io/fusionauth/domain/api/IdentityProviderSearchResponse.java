/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.IdentityProviderSearchResponseDeserializer;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.search.SearchResults;

/**
 * Identity Provider response.
 *
 * @author Spencer Witt
 */
@JsonDeserialize(using = IdentityProviderSearchResponseDeserializer.class)
public class IdentityProviderSearchResponse {
  public List<BaseIdentityProvider<?>> identityProviders;

  public long total;

  @JacksonConstructor
  public IdentityProviderSearchResponse() {
  }

  public IdentityProviderSearchResponse(SearchResults<BaseIdentityProvider<?>> searchResults) {
    this.identityProviders = searchResults.results;
    this.total = searchResults.total;
  }
}
