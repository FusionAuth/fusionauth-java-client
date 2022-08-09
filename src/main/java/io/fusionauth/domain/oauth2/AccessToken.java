/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.oauth2;

import java.net.URI;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class AccessToken implements OAuthResponse, Buildable<AccessToken> {
  @JsonIgnore
  public String clientId;

  /**
   * The lifetime in seconds of the access token. The time the token will expire from the time the response was
   * generated.
   */
  @JsonProperty("expires_in")
  public Integer expiresIn;

  @JsonProperty("id_token")
  public String idToken;

  @JsonIgnore
  public URI redirectURI;

  @JsonProperty("refresh_token")
  public String refreshToken;

  @JsonProperty("refresh_token_id")
  public UUID refreshTokenId;

  public String scope;

  /**
   * The access token issued by the authorization server.
   */
  @JsonProperty("access_token")
  public String token;

  /**
   * Token type.
   */
  @JsonProperty("token_type")
  public TokenType tokenType;

  public UUID userId;

  @JsonIgnore
  private ZonedDateTime createInstant;

  @JacksonConstructor
  public AccessToken() {
  }

  public AccessToken(String token, String clientId, Integer expiresIn, URI redirectURI, TokenType tokenType, UUID userId) {
    this.clientId = clientId;
    this.createInstant = ZonedDateTime.now(ZoneOffset.UTC);
    this.expiresIn = expiresIn;
    this.redirectURI = redirectURI;
    this.token = token;
    this.tokenType = tokenType;
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessToken that = (AccessToken) o;
    return Objects.equals(clientId, that.clientId) &&
           Objects.equals(expiresIn, that.expiresIn) &&
           Objects.equals(idToken, that.idToken) &&
           Objects.equals(redirectURI, that.redirectURI) &&
           Objects.equals(refreshToken, that.refreshToken) &&
           Objects.equals(refreshTokenId, that.refreshTokenId) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(token, that.token) &&
           tokenType == that.tokenType &&
           Objects.equals(userId, that.userId) &&
           Objects.equals(createInstant, that.createInstant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId,
                        expiresIn,
                        idToken,
                        redirectURI,
                        refreshToken,
                        refreshTokenId,
                        scope,
                        token,
                        tokenType,
                        userId,
                        createInstant);
  }

  /**
   * Accept 'Bearer' and 'bearer' equally for the token type when we deserialize this from a JSON response.
   *
   * @param tokenType the incoming token type
   */
  public void setTokenType(String tokenType) {
    this.tokenType = TokenType.fromName(tokenType);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
