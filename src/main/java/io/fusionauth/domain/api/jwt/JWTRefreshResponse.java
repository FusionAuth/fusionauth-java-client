/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.jwt;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * API response for refreshing a JWT with a Refresh Token.
 * <p>
 * Using a different response object from RefreshTokenResponse because the retrieve response will return an object for refreshToken, and this is a
 * string.
 *
 * @author Daniel DeGroff
 */
public class JWTRefreshResponse implements RefreshResponse {
  public String refreshToken;

  public UUID refreshTokenId;

  public String token;

  @JacksonConstructor
  public JWTRefreshResponse() {
  }

  public JWTRefreshResponse(UUID refreshTokenId, String refreshToken, String token) {
    this.refreshTokenId = refreshTokenId;
    this.refreshToken = refreshToken;
    this.token = token;
  }
}
