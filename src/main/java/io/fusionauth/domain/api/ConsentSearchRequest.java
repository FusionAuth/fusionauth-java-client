/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.search.ConsentSearchCriteria;

/**
 * Search request for Consents
 *
 * @author Spencer Witt
 */
public class ConsentSearchRequest {
  public ConsentSearchCriteria search = new ConsentSearchCriteria();

  @JacksonConstructor
  public ConsentSearchRequest() {
  }

  public ConsentSearchRequest(ConsentSearchCriteria search) {
    this.search = search;
  }
}
