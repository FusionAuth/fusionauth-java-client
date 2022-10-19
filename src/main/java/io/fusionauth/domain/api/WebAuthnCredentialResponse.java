/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * WebAuthn Credential API response
 *
 * @author Spencer Witt
 */
public class WebAuthnCredentialResponse {
  public WebAuthnCredential credential;

  public List<WebAuthnCredential> credentials;

  @JacksonConstructor
  public WebAuthnCredentialResponse() {
  }

  public WebAuthnCredentialResponse(WebAuthnCredential credential) {
    this.credential = credential;
  }

  public WebAuthnCredentialResponse(List<WebAuthnCredential> credentials) {
    this.credentials = credentials;
  }
}
