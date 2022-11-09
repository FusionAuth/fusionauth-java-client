/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

/**
 * Used to express whether the Relying Party requires <a href="https://www.w3.org/TR/webauthn-2/#user-verification">user verification</a> for the
 * current operation.
 *
 * @author Spencer Witt
 */
public enum UserVerificationRequirement {
  /**
   * The Relying Party requires user verification for the operation and will fail without it
   */
  required,

  /**
   * The Relying Party prefers user verification for the operation but will not fail without it
   */
  preferred,

  /**
   * The Relying Party does not want user verification for the operation
   */
  discouraged
}
