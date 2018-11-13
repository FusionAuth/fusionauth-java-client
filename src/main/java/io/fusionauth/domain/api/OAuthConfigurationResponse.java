/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
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
