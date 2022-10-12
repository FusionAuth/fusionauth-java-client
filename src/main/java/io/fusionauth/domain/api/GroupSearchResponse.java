/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Group;
import io.fusionauth.domain.search.SearchResults;

/**
 * Search response for Groups
 *
 * @author Daniel DeGroff
 */
public class GroupSearchResponse {
  public List<Group> groups;

  public long total;

  @JacksonConstructor
  public GroupSearchResponse() {
  }

  public GroupSearchResponse(SearchResults<Group> searchResults) {
    this.groups = searchResults.results;
    this.total = searchResults.total;
  }
}
