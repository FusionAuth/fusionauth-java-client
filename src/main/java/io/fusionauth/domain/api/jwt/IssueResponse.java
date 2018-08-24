/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.jwt;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class IssueResponse {
  public String refreshToken;

  public String token;

  @JacksonConstructor
  public IssueResponse() {
  }

  public IssueResponse(String token) {
    this.token = token;
  }
}
