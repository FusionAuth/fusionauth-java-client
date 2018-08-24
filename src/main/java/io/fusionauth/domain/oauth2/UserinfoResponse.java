/*
 * Copyright (c) 2018,FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.oauth2;

import java.util.LinkedHashMap;
import java.util.Map;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class UserinfoResponse extends LinkedHashMap<String, Object> {
  @JacksonConstructor
  public UserinfoResponse() {
  }

  public UserinfoResponse(Map<String, Object> claims) {
    putAll(claims);
  }
}
