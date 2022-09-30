/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import io.fusionauth.domain.Buildable;

/**
 * Request to authenticate with WebAuthn
 *
 * @author Spencer Witt
 */
public class PublicKeyAuthenticationRequest implements Buildable<PublicKeyAuthenticationRequest> {
  /**
   * Requested extension data for the authentication ceremony
   */
  public WebAuthnExtensionsClientOutputs clientExtensionResults = new WebAuthnExtensionsClientOutputs();

  /**
   * The selected credential ID in base64URL-encoded format
   */
  public String id;

  /**
   * The detailed client and signature data from the authentication ceremony
   */
  public AuthenticatorAuthenticationResponse response;

  /**
   * The Relying Party ID
   */
  public String rpId;

  /**
   * The credential type
   */
  public String type;
}
