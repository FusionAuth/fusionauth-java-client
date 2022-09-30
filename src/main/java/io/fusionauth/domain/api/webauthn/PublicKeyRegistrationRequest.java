/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.ArrayList;
import java.util.List;

import io.fusionauth.domain.Buildable;

/**
 * Request to register a new public key with WebAuthn
 *
 * @author Spencer Witt
 */
public class PublicKeyRegistrationRequest implements Buildable<PublicKeyRegistrationRequest> {
  /**
   * Requested extension data for the registration ceremony
   */
  public WebAuthnExtensionsClientOutputs clientExtensionResults = new WebAuthnExtensionsClientOutputs();

  /**
   * The new credential ID in base64URL-encoded format
   */
  public String id;

  /**
   * The detailed client and attestation data from the registration ceremony
   */
  public AuthenticatorRegistrationResponse response;

  /**
   * The Relying Party ID
   */
  public String rpId;

  /**
   * List of supported transport methods for the chosen <i>authenticator</i>
   */
  public List<AuthenticatorTransport> transports = new ArrayList<>();

  /**
   * The credential type
   */
  public String type;
}
