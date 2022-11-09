/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * API response for completing WebAuthn credential registration or assertion
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterCompleteResponse implements Buildable<WebAuthnRegisterCompleteResponse> {
  public WebAuthnCredential credential;

  @JacksonConstructor
  public WebAuthnRegisterCompleteResponse() {
  }

  public WebAuthnRegisterCompleteResponse(WebAuthnCredential credential) {
    this.credential = credential;
  }
}
