/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.search.UserCommentSearchCriteria;

/**
 * Search request for user comments
 *
 * @author Spencer Witt
 */
public class UserCommentSearchRequest {
  public UserCommentSearchCriteria search = new UserCommentSearchCriteria();

  @JacksonConstructor
  public UserCommentSearchRequest() {
  }
}
