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
package io.fusionauth.domain.oauth2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import static io.fusionauth.domain.util.Normalizer.removeEmpty;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Daniel DeGroff
 */
public class OAuth2Configuration implements Buildable<OAuth2Configuration> {
  public List<URI> authorizedOriginURLs = new ArrayList<>();

  public List<URI> authorizedRedirectURLs = new ArrayList<>();

  public String clientId;

  public String clientSecret;

  /**
   * Logout redirect URL when calling the <code>/oauth2/logout</code> endpoint. If this is left null,
   * <code>Application.oauthConfiguration.logoutURL</code> will be used instead.
   */
  public URI logoutURL;

  public OAuth2Configuration() {
  }

  public OAuth2Configuration(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OAuth2Configuration)) {
      return false;
    }
    OAuth2Configuration that = (OAuth2Configuration) o;
    return Objects.equals(authorizedOriginURLs, that.authorizedOriginURLs) &&
        Objects.equals(authorizedRedirectURLs, that.authorizedRedirectURLs) &&
        Objects.equals(clientId, that.clientId) &&
        Objects.equals(clientSecret, that.clientSecret) &&
        Objects.equals(logoutURL, that.logoutURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizedOriginURLs, authorizedRedirectURLs, clientId, clientSecret, logoutURL);
  }

  public void normalize() {
    removeEmpty(authorizedOriginURLs);
    removeEmpty(authorizedRedirectURLs);
    clientId = trim(clientId);
    clientSecret = trim(clientSecret);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
