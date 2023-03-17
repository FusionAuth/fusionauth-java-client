/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Key;
import io.fusionauth.domain.search.SearchResults;

/**
 * Key search response
 *
 * @author Spencer Witt
 */
public class KeySearchResponse {
  public List<Key> keys = new ArrayList<>();

  public long total;

  @JacksonConstructor
  public KeySearchResponse() {
  }

  public KeySearchResponse(SearchResults<Key> searchResults) {
    this.keys = searchResults.results;
    this.total = searchResults.total;
  }
}
