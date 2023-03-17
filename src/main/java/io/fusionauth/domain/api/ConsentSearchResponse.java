/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Consent;
import io.fusionauth.domain.search.SearchResults;

/**
 * Consent search response
 *
 * @author Spencer Witt
 */
public class ConsentSearchResponse {
  public List<Consent> consents = new ArrayList<>();

  public long total;

  @JacksonConstructor
  public ConsentSearchResponse() {
  }

  public ConsentSearchResponse(SearchResults<Consent> searchResults) {
    consents = searchResults.results;
    total = searchResults.total;
  }
}
