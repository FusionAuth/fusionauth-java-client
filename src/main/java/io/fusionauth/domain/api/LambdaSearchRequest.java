/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.search.LambdaSearchCriteria;

/**
 * Search request for Lambdas
 *
 * @author Mark Manes
 */
public class LambdaSearchRequest implements Buildable<LambdaSearchRequest> {
  public LambdaSearchCriteria search = new LambdaSearchCriteria();

  @JacksonConstructor
  public LambdaSearchRequest() {
  }

  public LambdaSearchRequest(LambdaSearchCriteria search) {
    this.search = search;
  }
}
