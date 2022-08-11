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
  public WebAuthnCredential webauthnCredential;

  public List<WebAuthnCredential> webauthnCredentials;

  @JacksonConstructor
  public WebAuthnCredentialResponse() {
  }

  public WebAuthnCredentialResponse(WebAuthnCredential webauthnCredential) {
    this.webauthnCredential = webauthnCredential;
  }

  public WebAuthnCredentialResponse(List<WebAuthnCredential> webauthnCredentials) {
    this.webauthnCredentials = webauthnCredentials;
  }
}
