/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.List;

import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * API request to import an existing WebAuthn credential(s)
 *
 * @author Spencer Witt
 */
public class WebAuthnImportRequest implements Buildable<WebAuthnImportRequest> {
  /**
   * The WebAuthn credential(s) to import. Required fields on credential:
   * <ul>
   *   <li>userId</li>
   *   <li>credentialId</li>
   *   <li>publicKey</li>
   *   <li>algorithm</li>
   * </ul>
   * Other fields not specified will be populated with the same defaults as a credential registered through a WebAuthn ceremony.
   */
  public List<WebAuthnCredential> credentials;

  /**
   * If {@code true}, database constraints will be validated before import is attempted. Otherwise, database constraint violations will result in
   * database exceptions
   */
  public boolean validateDbConstraints;
}
