/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.search.ApplicationSearchCriteria;

/**
 * Search request for Applications
 *
 * @author Spencer Witt
 */
public class ApplicationSearchRequest implements Buildable<ApplicationSearchRequest> {
  public ApplicationSearchCriteria search = new ApplicationSearchCriteria();

  @JacksonConstructor
  public ApplicationSearchRequest() {
  }

  public ApplicationSearchRequest(ApplicationSearchCriteria search) {
    this.search = search;
  }
}
