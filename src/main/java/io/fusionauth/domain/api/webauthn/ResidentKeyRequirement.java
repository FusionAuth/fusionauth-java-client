/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

/**
 * Describes the Relying Party's requirements for <a href="https://www.w3.org/TR/webauthn-2/#client-side-discoverable-credential">client-side
 * discoverable credentials</a> (formerly known as "resident keys")
 *
 * @author Spencer Witt
 */
public enum ResidentKeyRequirement {
  /**
   * The Relying Party prefers creating a server-side credential but will accept a client-side discoverable credential
   */
  discouraged,

  /**
   * The Relying Party strongly prefers creating a client-side discoverable credential but will accept a server-side credential
   */
  preferred,

  /**
   * The Relying Party requires a client-side discoverable credential and is prepared to receive an error if one cannot be created
   */
  required
}
