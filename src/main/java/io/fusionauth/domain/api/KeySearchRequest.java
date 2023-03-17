/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.search.KeySearchCriteria;

/**
 * Search request for Keys
 *
 * @author Spencer Witt
 */
public class KeySearchRequest {
  public KeySearchCriteria search = new KeySearchCriteria();

  @JacksonConstructor
  public KeySearchRequest() {
  }

  public KeySearchRequest(KeySearchCriteria search) {
    this.search = search;
  }
}
