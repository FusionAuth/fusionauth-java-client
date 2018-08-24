/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.oauth2;

import java.util.LinkedHashMap;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class IntrospectResponse extends LinkedHashMap<String, Object> {
  @JacksonConstructor
  public IntrospectResponse() {
    put("active", false);
  }

  public IntrospectResponse(boolean active) {
    put("active", active);
  }
}
