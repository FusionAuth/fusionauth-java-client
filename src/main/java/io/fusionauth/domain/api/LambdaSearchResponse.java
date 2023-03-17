/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Lambda;
import io.fusionauth.domain.search.SearchResults;

/**
 * Lambda search response
 *
 * @author Mark Manes
 */
public class LambdaSearchResponse {
  public List<Lambda> lambdas;

  public long total;

  @JacksonConstructor
  public LambdaSearchResponse() {
  }

  public LambdaSearchResponse(SearchResults<Lambda> searchResults) {
    lambdas = searchResults.results;
    total = searchResults.total;
  }
}
