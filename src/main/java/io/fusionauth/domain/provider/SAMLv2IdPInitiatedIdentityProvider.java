/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.provider;

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * SAML v2 IdP Initiated identity provider configuration.
 *
 * @author Daniel DeGroff
 */
public class SAMLv2IdPInitiatedIdentityProvider extends BaseSAMLv2IdentityProvider<SAMLv2IdPInitiatedApplicationConfiguration>
    implements Buildable<SAMLv2IdPInitiatedIdentityProvider> {

  public String issuer;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SAMLv2IdPInitiatedIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SAMLv2IdPInitiatedIdentityProvider that = (SAMLv2IdPInitiatedIdentityProvider) o;
    return Objects.equals(issuer, that.issuer);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.SAMLv2IdPInitiated;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), issuer);
  }

  @Override
  public void normalize() {
    super.normalize();
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
