/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * API response for completing WebAuthn assertion
 *
 * @author Spencer Witt
 */
public class WebAuthnAssertResponse implements Buildable<WebAuthnAssertResponse> {
  public WebAuthnCredential credential;

  @JacksonConstructor
  public WebAuthnAssertResponse() {
  }

  public WebAuthnAssertResponse(WebAuthnCredential credential) {
    this.credential = credential;
  }
}
