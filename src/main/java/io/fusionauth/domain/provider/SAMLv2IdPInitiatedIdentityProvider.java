/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
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

  public SAMLv2IdPInitiatedIdentityProvider secure() {
    emailClaim = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
