/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines valid credential types. This is an extension point in the WebAuthn spec. The only defined value at this time is "public-key"
 *
 * @author Spencer Witt
 */
public enum PublicKeyCredentialType {
  /**
   * The associated credential represents an asymmetric key pair
   */
  @JsonProperty("public-key")
  publicKey
}
