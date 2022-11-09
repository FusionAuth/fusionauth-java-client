/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.webauthn.PublicKeyCredentialCreationOptions;

/**
 * API response for starting a WebAuthn registration ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterStartResponse {
  public PublicKeyCredentialCreationOptions options;

  @JacksonConstructor
  public WebAuthnRegisterStartResponse() {
  }

  public WebAuthnRegisterStartResponse(PublicKeyCredentialCreationOptions options) {
    this.options = options;
  }
}
