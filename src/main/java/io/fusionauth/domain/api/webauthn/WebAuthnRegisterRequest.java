/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.UUID;

import io.fusionauth.domain.Buildable;

/**
 * API request to start a WebAuthn registration ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterRequest implements Buildable<WebAuthnRegisterRequest> {
  /**
   * Display name for the new credential
   */
  public String displayName;

  /**
   * The optional name for the new credential. This will be defaulted to a unique four character string if not provided
   */
  public String name;

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
}
