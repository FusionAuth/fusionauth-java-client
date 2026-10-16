/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

/**
 * How much confidence we have in the validation of the subject token that was presented. These values are specified by the ARC and are
 * stored by name, so they may not be renamed without a migration.
 */
public enum SubjectTokenValidationStatus {
  /**
   * The token was fully validated against a trusted key or introspection endpoint.
   */
  AUTHORITATIVE,

  /**
   * The token was parsed and partially validated, but we could not fully establish authority for it.
   */
  BEST_EFFORT,

  /**
   * Validation of the token failed.
   */
  FAILED
}
