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
   * Used for passwordless authentication on a new device.
   */
  bootstrap,

  /**
   * Used for self-service credential registration.
   */
  general,

  /**
   * Used for passwordless re-authentication on a previously used device.
   */
  reauthentication
}
