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
package io.fusionauth.domain.connector;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * Models an LDAP connector.
 *
 * @author Trevor Smith
 */
public class LDAPConnectorConfiguration extends BaseConnectorConfiguration implements Buildable<LDAPConnectorConfiguration> {
  public URI authenticationURL;

  public String baseStructure;

  public int connectTimeout;

  public String identifyingAttribute;

  public LambdaConfiguration lambdaConfiguration = new LambdaConfiguration();

  public String loginIdAttribute;

  public int readTimeout;

  public List<String> requestedAttributes = new ArrayList<>();

  public LDAPSecurityMethod securityMethod;

  public String systemAccountDN;

  public String systemAccountPassword;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LDAPConnectorConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    LDAPConnectorConfiguration that = (LDAPConnectorConfiguration) o;
    return connectTimeout == that.connectTimeout &&
           readTimeout == that.readTimeout &&
           Objects.equals(authenticationURL, that.authenticationURL) &&
           Objects.equals(baseStructure, that.baseStructure) &&
           Objects.equals(loginIdAttribute, that.loginIdAttribute) &&
           Objects.equals(identifyingAttribute, that.identifyingAttribute) &&
           Objects.equals(lambdaConfiguration, that.lambdaConfiguration) &&
           Objects.equals(requestedAttributes, that.requestedAttributes) &&
           securityMethod == that.securityMethod &&
           Objects.equals(systemAccountDN, that.systemAccountDN) &&
           Objects.equals(systemAccountPassword, that.systemAccountPassword);
  }

  @Override
  public ConnectorType getType() {
    return ConnectorType.LDAP;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), authenticationURL, baseStructure, connectTimeout, loginIdAttribute, identifyingAttribute, lambdaConfiguration, readTimeout, requestedAttributes, securityMethod, systemAccountDN, systemAccountPassword);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum LDAPSecurityMethod {
    None,
    LDAPS,
    StartTLS
  }

  public static class LambdaConfiguration {
    public UUID reconcileId;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof LambdaConfiguration)) {
        return false;
      }
      LambdaConfiguration that = (LambdaConfiguration) o;
      return Objects.equals(reconcileId, that.reconcileId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(reconcileId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
