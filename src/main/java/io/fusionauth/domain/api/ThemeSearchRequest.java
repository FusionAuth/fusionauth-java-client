/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.search.ThemeSearchCriteria;

/**
 * Search request for Themes.
 *
 * @author Mark Manes
 */
public class ThemeSearchRequest implements Buildable<ThemeSearchRequest> {
  public ThemeSearchCriteria search = new ThemeSearchCriteria();

  @JacksonConstructor
  public ThemeSearchRequest() {
  }

  public ThemeSearchRequest(ThemeSearchCriteria search) {
    this.search = search;
  }
}
