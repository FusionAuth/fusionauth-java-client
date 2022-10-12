/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Contains extension output for requested extensions during a WebAuthn ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnExtensionsClientOutputs {
  public CredentialPropertiesOutput credProps = new CredentialPropertiesOutput();

  @JsonIgnore
  public boolean isDiscoverableCredential() {
    return credProps != null &&
           credProps.rk;
  }
}
