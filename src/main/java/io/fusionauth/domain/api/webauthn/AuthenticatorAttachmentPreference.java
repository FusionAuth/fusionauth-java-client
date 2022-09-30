/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import io.fusionauth.domain.api.webauthn.AuthenticatorAttachment;
import io.fusionauth.domain.api.webauthn.AuthenticatorSelectionCriteria;

/**
 * Describes the authenticator attachment modality preference for a WebAuthn workflow. See {@link AuthenticatorAttachment}
 *
 * @author Spencer Witt
 */
public enum AuthenticatorAttachmentPreference {
  /**
   * Selecting this option will use {@link AuthenticatorAttachment#platform} for the {@link AuthenticatorSelectionCriteria#authenticatorAttachment}
   * parameter during credential creation
   */
  platform,

  /**
   * Selecting this option will use {@link AuthenticatorAttachment#crossPlatform} for the
   * {@link AuthenticatorSelectionCriteria#authenticatorAttachment}
   * parameter during credential creation
   */
  crossPlatform,

  /**
   * Selecting this option will leave the {@link AuthenticatorSelectionCriteria#authenticatorAttachment} parameter unset during credential
   * creation indicating the Relying Party has no preference regarding the authenticator attachment modality.
   */
  either
}
