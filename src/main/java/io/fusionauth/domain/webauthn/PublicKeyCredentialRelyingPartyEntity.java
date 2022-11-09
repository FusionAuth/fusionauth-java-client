/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import io.fusionauth.domain.Buildable;

/**
 * Supply additional information about the Relying Party when creating a new credential
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialRelyingPartyEntity extends PublicKeyCredentialEntity implements Buildable<PublicKeyCredentialRelyingPartyEntity> {
  /**
   * A unique identifier for the Relying Party. Defaults to the calling host's <a
   * href="https://html.spec.whatwg.org/multipage/origin.html#concept-origin-effective-domain">effective domain</a>
   */
  public String id;
}
