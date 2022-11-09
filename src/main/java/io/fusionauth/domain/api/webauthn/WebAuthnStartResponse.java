/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;

/**
 * API response for starting a WebAuthn authentication ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnStartResponse {
  public PublicKeyCredentialRequestOptions options;

  @JacksonConstructor
  public WebAuthnStartResponse() {
  }

  public WebAuthnStartResponse(PublicKeyCredentialRequestOptions options) {
    this.options = options;
  }
}
