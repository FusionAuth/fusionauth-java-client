/*
 * Copyright (c) 2020-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.messenger;

import java.net.URI;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.HTTPHeaders;

/**
 * @author Brett Guy
 */
public class GenericMessengerConfiguration extends BaseMessengerConfiguration implements Buildable<GenericMessengerConfiguration> {
  public Integer connectTimeout;

  public HTTPHeaders headers = new HTTPHeaders();

  public String httpAuthenticationPassword;

  public String httpAuthenticationUsername;

  public Integer readTimeout;

  public String sslCertificate;

  public URI url;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GenericMessengerConfiguration that = (GenericMessengerConfiguration) o;
    return Objects.equals(connectTimeout, that.connectTimeout) &&
           Objects.equals(headers, that.headers) &&
           Objects.equals(httpAuthenticationPassword, that.httpAuthenticationPassword) &&
           Objects.equals(httpAuthenticationUsername, that.httpAuthenticationUsername) &&
           Objects.equals(readTimeout, that.readTimeout) &&
           Objects.equals(sslCertificate, that.sslCertificate) &&
           Objects.equals(url, that.url);
  }

  @Override
  public MessengerType getType() {
    return MessengerType.Generic;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), connectTimeout, headers, httpAuthenticationPassword, httpAuthenticationUsername, readTimeout, sslCertificate, url);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
