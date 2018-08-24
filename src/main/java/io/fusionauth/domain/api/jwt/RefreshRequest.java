/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.jwt;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class RefreshRequest {

  public String refreshToken;

  @JacksonConstructor
  public RefreshRequest() {
  }

  public RefreshRequest(String token) {
    this.refreshToken = token;
  }
}
