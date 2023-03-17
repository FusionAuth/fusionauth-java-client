/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Theme;
import io.fusionauth.domain.search.SearchResults;

/**
 * Search response for Themes
 *
 * @author Mark Manes
 */
public class ThemeSearchResponse {
  public List<Theme> themes;

  public long total;

  @JacksonConstructor
  public ThemeSearchResponse() {
  }

  public ThemeSearchResponse(SearchResults<Theme> searchResults) {
    themes = searchResults.results;
    total = searchResults.total;
  }
}
