/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

/**
 * Describes the authenticator attachment modality preference for a WebAuthn workflow. See {@link AuthenticatorAttachment}
 *
 * @author Spencer Witt
 */
public enum AuthenticatorAttachmentPreference {
  PLATFORM,

  CROSS_PLATFORM,

  /**
   * Selecting this option will leave {@link AuthenticatorSelectionCriteria}'s <code>authenticatorAttachment</code> parameter unset during credential
   * creation indicating the Relying Party has no preference regarding the authenticator attachment modality.
   */
  EITHER
}
