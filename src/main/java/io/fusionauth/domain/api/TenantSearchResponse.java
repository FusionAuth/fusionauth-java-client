/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Tenant;
import io.fusionauth.domain.search.SearchResults;

/**
 * Tenant search response
 *
 * @author Mark Manes
 */
public class TenantSearchResponse {
  public List<Tenant> tenants;

  public long total;

  @JacksonConstructor
  public TenantSearchResponse() {
  }

  public TenantSearchResponse(SearchResults<Tenant> searchResults) {
    tenants = searchResults.results;
    total = searchResults.total;
  }
}
