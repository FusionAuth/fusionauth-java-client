/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * API request to start a WebAuthn registration ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterRequest implements Buildable<WebAuthnRegisterRequest> {
  /**
   * Optional display name for the new credential
   */
  public String credentialName;

  /**
   * The user agent string during registration
   */
  public String userAgent;

  /**
   * The user ID registering the credential
   */
  public UUID userId;

  /**
   * The workflow the new credential is intended to be used with. This will impact credential creation options
   */
  public WebAuthnWorkflow workflow;

  @JacksonConstructor
  public WebAuthnRegisterRequest() {
  }
}
