/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;

/**
 * Request to complete the WebAuthn registration ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnLoginRequest extends BaseLoginRequest implements Buildable<WebAuthnLoginRequest> {
  /**
   * Details on the challenge signing
   */
  public WebAuthnPublicKeyAuthenticationRequest credential;

  /**
   * The request origin
   */
  public String origin;

  /**
   * The Relying Party Id
   */
  @JsonProperty("rpId")
  public String relyingPartyId;

  public String twoFactorTrustId;
}
