/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * Used by the Relying Party to specify their requirements for authenticator attributes. Fields use the deprecated "resident key" terminology to refer
 * to client-side discoverable credentials to maintain backwards compatibility with WebAuthn Level 1.
 *
 * @author Spencer Witt
 */
public class AuthenticatorSelectionCriteria implements Buildable<AuthenticatorSelectionCriteria> {
  /**
   * If present, eligible <i>authenticators</i> will be filtered to those with the specified <i>attachment modality</i>
   */
  public AuthenticatorAttachment authenticatorAttachment;

  /**
   * Retained for backwards compatibility with WebAuthn Level 1. Relying Parties should set to true if and only if
   * {@link AuthenticatorSelectionCriteria#residentKey} is set to {@link ResidentKeyRequirement#required}. This behavior is enforced by the type.
   * <p>
   * <i>Authenticators</i> will treat this value as <code>false</code> if it is not supplied.
   */
  public Boolean requireResidentKey;

  /**
   * Specifies the extent to which the Relying Party wishes to create client-side discoverable credentials. If no value is given, the
   * <i>authenticator</i> will treat the value as {@link ResidentKeyRequirement#required} if {@link AuthenticatorSelectionCriteria#requireResidentKey}
   * is
   * <code>true</code>, or {@link ResidentKeyRequirement#discouraged} if it is <code>false</code> or absent.
   */
  public ResidentKeyRequirement residentKey;

  /**
   * Specifies the Relying Party's requirements for user verification. <i>Authenticators</i> default this to
   * {@link UserVerificationRequirement#preferred}.
   */
  public UserVerificationRequirement userVerification;

  @JacksonConstructor
  public AuthenticatorSelectionCriteria() {
  }

  public AuthenticatorSelectionCriteria(AuthenticatorAttachment authenticatorAttachment, ResidentKeyRequirement residentKey,
                                        UserVerificationRequirement userVerification) {
    this(authenticatorAttachment, residentKey == ResidentKeyRequirement.required, residentKey, userVerification);
  }

  public AuthenticatorSelectionCriteria(AuthenticatorAttachment authenticatorAttachment, ResidentKeyRequirement residentKey) {
    this(authenticatorAttachment, residentKey, UserVerificationRequirement.preferred);
  }

  private AuthenticatorSelectionCriteria(AuthenticatorAttachment authenticatorAttachment, boolean requireResidentKey,
                                         ResidentKeyRequirement residentKey,
                                         UserVerificationRequirement userVerification) {
    this.authenticatorAttachment = authenticatorAttachment;
    this.requireResidentKey = requireResidentKey;
    this.residentKey = residentKey;
    this.userVerification = userVerification;
  }
}
