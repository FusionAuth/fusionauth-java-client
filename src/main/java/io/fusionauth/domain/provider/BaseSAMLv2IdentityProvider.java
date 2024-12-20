/*
 * Copyright (c) 2023-2024, FusionAuth, All Rights Reserved
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


/**
 * @author Lyle Schemmerling
 */
public abstract class BaseSAMLv2IdentityProvider<D extends BaseIdentityProviderApplicationConfiguration> extends BaseIdentityProvider<D> {
  public SAMLv2AssertionDecryptionConfiguration assertionDecryptionConfiguration = new SAMLv2AssertionDecryptionConfiguration();

  public String emailClaim;

  /**
   * The default key used for SAML Request Signature Verification if one cannot be found in the <code>KeyInfo</code> XML element in the SAML response.
   */
  public UUID keyId;

  public String uniqueIdClaim;

  public boolean useNameIdForEmail;

  public String usernameClaim;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseSAMLv2IdentityProvider<?>)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    BaseSAMLv2IdentityProvider<?> that = (BaseSAMLv2IdentityProvider<?>) o;
    return Objects.equals(assertionDecryptionConfiguration, that.assertionDecryptionConfiguration)
           && Objects.equals(emailClaim, that.emailClaim)
           && Objects.equals(keyId, that.keyId)
           && Objects.equals(uniqueIdClaim, that.uniqueIdClaim)
           && useNameIdForEmail == that.useNameIdForEmail
           && Objects.equals(usernameClaim, that.usernameClaim);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), assertionDecryptionConfiguration, emailClaim, keyId, uniqueIdClaim, useNameIdForEmail, usernameClaim);
  }
}
