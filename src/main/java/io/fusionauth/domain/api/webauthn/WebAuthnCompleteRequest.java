/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

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
   * The User's loginId. This will prefer email address if supported
   */
  public String loginId;

  /**
   * The request origin
   */
  public String origin;

  /**
   * The Relying Party ID
   */
  public String rpId;
}
