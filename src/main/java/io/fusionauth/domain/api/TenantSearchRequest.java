/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.search.TenantSearchCriteria;

/**
 * Search request for Tenants
 *
 * @author Mark Manes
 */
public class TenantSearchRequest implements Buildable<TenantSearchRequest> {
  public TenantSearchCriteria search = new TenantSearchCriteria();

  @JacksonConstructor
  public TenantSearchRequest() {
  }

  public TenantSearchRequest(TenantSearchCriteria search) {
    this.search = search;
  }
}
