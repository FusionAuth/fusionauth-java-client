/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;

/**
 * Describes a user account or WebAuthn Relying Party associated with a public key credential
 */
public abstract class PublicKeyCredentialEntity {
  /**
   * A human-readable name for the entity
   */
  public String name;

  @JacksonConstructor
  public PublicKeyCredentialEntity() {
  }

  public PublicKeyCredentialEntity(String name) {
    this.name = name;
  }
}
