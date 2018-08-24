/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.net.URI;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.oauth2.OAuth2Configuration;

/**
 * @author Daniel DeGroff
 */
public class OAuthConfigurationResponse {
  /**
   * Not application specific, HTTP Session inactive timeout configuration.
   */
  public int httpSessionMaxInactiveInterval;

  /**
   * Global default logout URL used if an application specific one is not provided.
   */
  public URI logoutURL;

  public OAuth2Configuration oauthConfiguration;

  public OAuthConfigurationResponse(int httpSessionMaxInactiveInterval, URI logoutURL, OAuth2Configuration oauthConfiguration) {
    this.httpSessionMaxInactiveInterval = httpSessionMaxInactiveInterval;
    this.logoutURL = logoutURL;
    this.oauthConfiguration = oauthConfiguration;
  }

  @JacksonConstructor
  public OAuthConfigurationResponse() {
  }
}
