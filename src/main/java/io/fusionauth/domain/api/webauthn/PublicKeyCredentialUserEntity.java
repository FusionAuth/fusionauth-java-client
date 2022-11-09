/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import io.fusionauth.domain.Buildable;

/**
 * Supply additional information about the user account when creating a new credential
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialUserEntity extends PublicKeyCredentialEntity implements Buildable<PublicKeyCredentialUserEntity> {
  /**
   * A human-readable name for the user account intended only for display. This value <i>should</i> be chosen by the user.
   */
  public String displayName;

  /**
   * A <a href="https://www.w3.org/TR/webauthn-2/#user-handle">user handle</a> specified by the Relying Party that can be used to uniquely identify a
   * user account with the Relying Party
   */
  public String id;
}
