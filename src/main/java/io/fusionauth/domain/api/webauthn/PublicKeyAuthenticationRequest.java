/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.fasterxml.jackson.annotation.JsonProperty;
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
   * The selected credential Id in base64URL-encoded format
   */
  public String id;

  /**
   * The Relying Party Id
   */
  @JsonProperty("rpId")
  public String relyingPartyId;

  /**
   * The detailed client and signature data from the authentication ceremony
   */
  public AuthenticatorAuthenticationResponse response;

  /**
   * The credential type
   */
  public String type;
}
