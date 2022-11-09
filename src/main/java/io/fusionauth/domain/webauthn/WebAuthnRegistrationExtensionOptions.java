/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import io.fusionauth.domain.Buildable;

/**
 * Options to request extensions during credential registration
 *
 * @author Spencer Witt
 */
public class WebAuthnRegistrationExtensionOptions implements Buildable<WebAuthnRegistrationExtensionOptions> {
  /**
   * Set to {@code true} to request information on whether the new credential is client-side discoverable
   */
  public boolean credProps;
}
