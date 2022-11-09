/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;

/**
 * Request to complete the WebAuthn registration ceremony for a new credential,.
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterCompleteRequest implements Buildable<WebAuthnRegisterCompleteRequest> {
  /**
   * Details on the new public key credential
   */
  public WebAuthnPublicKeyRegistrationRequest credential;

  /**
   * The request origin
   */
  public String origin;

  /**
   * The Relying Party Id
   */
  @JsonProperty("rpId")
  public String relyingPartyId;

  /**
   * The User's database identifier
   */
  public UUID userId;
}
