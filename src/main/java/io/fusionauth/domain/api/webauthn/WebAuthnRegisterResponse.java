/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;

/**
 * API response for starting a WebAuthn registration ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterResponse {
  public PublicKeyCredentialCreationOptions options;

  @JacksonConstructor
  public WebAuthnRegisterResponse() {
  }

  public WebAuthnRegisterResponse(PublicKeyCredentialCreationOptions options) {
    this.options = options;
  }
}
