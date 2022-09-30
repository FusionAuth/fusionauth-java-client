/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.UUID;

import io.fusionauth.domain.Buildable;

/**
 * API response for completing WebAuthn credential registration
 *
 * @author Spencer Witt
 */
public class WebAuthnCompleteResponse implements Buildable<WebAuthnCompleteResponse> {
  /**
   * The database ID for the new credential
   */
  public UUID credentialId;
}
