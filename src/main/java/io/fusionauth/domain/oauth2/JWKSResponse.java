/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.oauth2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.jwks.domain.JSONWebKey;

/**
 * @author Daniel DeGroff
 */
public class JWKSResponse implements Buildable<JWKSResponse> {
  // Return an empty collection for this endpoint
  @JsonInclude(JsonInclude.Include.NON_ABSENT)
  public List<JSONWebKey> keys;

  @JacksonConstructor
  public JWKSResponse() {
  }

  public JWKSResponse(List<JSONWebKey> keys) {
    this.keys = keys;
  }
}
