/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.UserComment;
import io.fusionauth.domain.search.SearchResults;

/**
 * User comment search response
 *
 * @author Spencer Witt
 */
public class UserCommentSearchResponse {
  public long total;

  public List<UserComment> userComments = new ArrayList<>();

  @JacksonConstructor
  public UserCommentSearchResponse() {
  }

  public UserCommentSearchResponse(SearchResults<UserComment> searchResults) {
    this.userComments = searchResults.results;
    this.total = searchResults.total;
  }
}
