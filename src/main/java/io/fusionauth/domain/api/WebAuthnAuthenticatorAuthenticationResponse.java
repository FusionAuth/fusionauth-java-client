/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import io.fusionauth.domain.Buildable;

/**
 * The <i>authenticator's</i> response for the authentication ceremony in its encoded format
 *
 * @author Spencer Witt
 */
public class WebAuthnAuthenticatorAuthenticationResponse implements Buildable<WebAuthnAuthenticatorAuthenticationResponse> {
  /**
   * The authenticator data in CBOR that has been base64URL-encoded for transport
   */
  public String authenticatorData;

  /**
   * Base64URL-encoded client data for the authentication ceremony
   */
  public String clientDataJSON;

  /**
   * The base64URL-encoded signature generated by the <i>authenticator</i> for this authentication ceremony
   */
  public String signature;

  /**
   * The base64URL-encoded user handle for this authentication ceremony, if present
   */
  public String userHandle;
}
