/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

/**
 * Identifies the WebAuthn workflow. This will affect the parameters used for credential creation
 * and request based on the Tenant configuration.
 *
 * @author Spencer Witt
 */
public enum WebAuthnWorkflow {
  /**
   * Used for passwordless re-authentication on a previously used device.
   */
  reAuthentication,

  /**
   * Used for passwordless authentication on a new device.
   */
  bootstrap,

  /**
   * Used as a second factor during the authentication process.
   */
  twoFactor,

  /**
   * Used for self-service credential registration.
   */
  general
}
