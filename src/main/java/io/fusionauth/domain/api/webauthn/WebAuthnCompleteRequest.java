/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;

/**
 * Request to complete the WebAuthn registration ceremony for a new credential
 *
 * @author Spencer Witt
 */
public class WebAuthnCompleteRequest implements Buildable<WebAuthnCompleteRequest> {
  /**
   * Details on the new public key credential
   */
  public PublicKeyRegistrationRequest credential;

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
