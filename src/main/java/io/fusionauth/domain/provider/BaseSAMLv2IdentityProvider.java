/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.Objects;
import java.util.UUID;


/**
 * @author Lyle Schemmerling
 */
public abstract class BaseSAMLv2IdentityProvider<D extends BaseIdentityProviderApplicationConfiguration> extends BaseIdentityProvider<D> {
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
    return useNameIdForEmail == that.useNameIdForEmail
           && Objects.equals(emailClaim, that.emailClaim)
           && Objects.equals(keyId, that.keyId)
           && Objects.equals(uniqueIdClaim, that.uniqueIdClaim)
           && Objects.equals(usernameClaim, that.usernameClaim);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), emailClaim, keyId, uniqueIdClaim, useNameIdForEmail, usernameClaim);
  }
}
