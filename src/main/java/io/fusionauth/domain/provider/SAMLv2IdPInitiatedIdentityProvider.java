/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * SAML v2 IdP Initiated identity provider configuration.
 *
 * @author Daniel DeGroff
 */
public class SAMLv2IdPInitiatedIdentityProvider extends BaseIdentityProvider<SAMLv2IdPInitiatedApplicationConfiguration>
    implements Buildable<SAMLv2IdPInitiatedIdentityProvider> {
  @InternalJSONColumn
  public String emailClaim;

  @InternalJSONColumn
  public String issuer;

  /**
   * The default key used for SAML Request Signature Verification if one cannot be found in the <code>KeyInfo</code> XML element in the SAML response.
   */
  public UUID keyId;

  @InternalJSONColumn
  public String uniqueIdClaim;

  @InternalJSONColumn
  public boolean useNameIdForEmail;

  @InternalJSONColumn
  public String usernameClaim;

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
    return useNameIdForEmail == that.useNameIdForEmail &&
           Objects.equals(emailClaim, that.emailClaim) &&
           Objects.equals(issuer, that.issuer) &&
           Objects.equals(keyId, that.keyId) &&
           Objects.equals(uniqueIdClaim, that.uniqueIdClaim) &&
           Objects.equals(usernameClaim, that.usernameClaim);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.SAMLv2IdPInitiated;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(),
                        emailClaim,
                        issuer,
                        keyId,
                        uniqueIdClaim,
                        useNameIdForEmail,
                        usernameClaim);
  }

  @Override
  public void normalize() {
    super.normalize();
  }

  public SAMLv2IdPInitiatedIdentityProvider secure() {
    emailClaim = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
