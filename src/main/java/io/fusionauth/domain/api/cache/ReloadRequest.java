/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.cache;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class ReloadRequest {
  public String name;

  @JacksonConstructor
  public ReloadRequest() {
  }

  public ReloadRequest(String name) {
    this.name = name;
  }
}
