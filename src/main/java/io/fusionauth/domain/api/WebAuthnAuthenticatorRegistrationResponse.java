/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import io.fusionauth.domain.Buildable;

/**
 * The <i>authenticator's</i> response for the registration ceremony in its encoded format
 *
 * @author Spencer Witt
 */
public class WebAuthnAuthenticatorRegistrationResponse implements Buildable<WebAuthnAuthenticatorRegistrationResponse> {
  /**
   * The attestation data in CBOR that has been base64URL-encoded for transport
   */
  public String attestationObject;

  /**
   * Base64URL-encoded client data for the registration ceremony
   */
  public String clientDataJSON;
}
