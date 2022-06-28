/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.api.webauthn.enums.AuthenticatorTransport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Request to register a new public key with WebAuthn
 *
 * @author Spencer Witt
 */
public class PublicKeyRegistrationRequest implements Buildable<PublicKeyRegistrationRequest> {
  /**
   * Requested extension data for the registration ceremony
   */
  public HashMap<String, String> clientExtensionResults;

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

  @JacksonConstructor
  public PublicKeyRegistrationRequest() {
  }
}
