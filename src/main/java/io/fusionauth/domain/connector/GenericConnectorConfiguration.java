/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.connector;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.HTTPHeaders;

/**
 * Models a generic connector.
 *
 * @author Trevor Smith
 */
public class GenericConnectorConfiguration extends BaseConnectorConfiguration implements Buildable<GenericConnectorConfiguration> {
  public URI authenticationURL;

  public int connectTimeout;

  public HTTPHeaders headers = new HTTPHeaders();

  public String httpAuthenticationPassword;

  public String httpAuthenticationUsername;

  public int readTimeout;

  public UUID sslCertificateKeyId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GenericConnectorConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GenericConnectorConfiguration that = (GenericConnectorConfiguration) o;
    return connectTimeout == that.connectTimeout &&
           readTimeout == that.readTimeout &&
           Objects.equals(authenticationURL, that.authenticationURL) &&
           Objects.equals(headers, that.headers) &&
           Objects.equals(httpAuthenticationPassword, that.httpAuthenticationPassword) &&
           Objects.equals(httpAuthenticationUsername, that.httpAuthenticationUsername) &&
           Objects.equals(sslCertificateKeyId, that.sslCertificateKeyId);
  }

  @Override
  public ConnectorType getType() {
    return ConnectorType.Generic;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), authenticationURL, connectTimeout, headers, httpAuthenticationPassword, httpAuthenticationUsername, readTimeout, sslCertificateKeyId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
