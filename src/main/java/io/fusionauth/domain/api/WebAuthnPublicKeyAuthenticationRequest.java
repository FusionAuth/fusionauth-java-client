/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.webauthn.WebAuthnExtensionsClientOutputs;

/**
 * Request to authenticate with WebAuthn
 *
 * @author Spencer Witt
 */
public class WebAuthnPublicKeyAuthenticationRequest implements Buildable<WebAuthnPublicKeyAuthenticationRequest> {
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
  public WebAuthnAuthenticatorAuthenticationResponse response;

  /**
   * The credential type
   */
  public String type;
}
