/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.jwt;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * @author Daniel DeGroff
 */
public class RefreshRequest extends BaseEventRequest implements Buildable<RefreshRequest> {
  public String refreshToken;

  public String token;

  @JacksonConstructor
  public RefreshRequest() {
  }

  public RefreshRequest(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public RefreshRequest(EventInfo eventInfo, String refreshToken) {
    super(eventInfo);
    this.refreshToken = refreshToken;
  }
}
