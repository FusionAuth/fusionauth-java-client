/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.search.GroupSearchCriteria;

/**
 * Search request for Groups.
 *
 * @author Daniel DeGroff
 */
public class GroupSearchRequest implements Buildable<GroupSearchRequest> {
  public GroupSearchCriteria search = new GroupSearchCriteria();

  @JacksonConstructor
  public GroupSearchRequest() {
  }

  public GroupSearchRequest(GroupSearchCriteria search) {
    this.search = search;
  }
}
