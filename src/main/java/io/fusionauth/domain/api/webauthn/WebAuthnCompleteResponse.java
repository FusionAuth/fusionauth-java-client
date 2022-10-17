/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * API response for completing WebAuthn credential registration or assertion
 *
 * @author Spencer Witt
 */
public class WebAuthnCompleteResponse implements Buildable<WebAuthnCompleteResponse> {
  public WebAuthnCredential credential;

  @JacksonConstructor
  public WebAuthnCompleteResponse() {
  }

  public WebAuthnCompleteResponse(WebAuthnCredential credential) {
    this.credential = credential;
  }
}
