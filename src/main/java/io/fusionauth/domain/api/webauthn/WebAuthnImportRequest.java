/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * API request to import an existing WebAuthn credential
 *
 * @author Spencer Witt
 */
public class WebAuthnImportRequest implements Buildable<WebAuthnImportRequest> {
  /**
   * The WebAuthn credential to import. Required fields on credential:
   * <ul>
   *   <li>userId</li>
   *   <li>credentialId</li>
   *   <li>publicKey</li>
   *   <li>alg</li>
   * </ul>
   * Other fields not specified will be populated with the same defaults as a credential registered through a WebAuthn ceremony.
   */
  public WebAuthnCredential credential;
}
