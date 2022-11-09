/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines valid credential types. This is an extension point in the WebAuthn spec. The only defined value at this time is "public-key"
 *
 * @author Spencer Witt
 */
public enum PublicKeyCredentialType {
  /**
   * The associated credential represents an asymmetric key pair
   */
  publicKey("public-key");

  private static final Map<String, PublicKeyCredentialType> valueMap = new HashMap<>();

  @JsonValue
  public final String value;

  PublicKeyCredentialType(String value) {
    this.value = value;
  }

  @JsonCreator
  public static PublicKeyCredentialType forValue(String value) {
    return valueMap.get(value);
  }

  static {
    for (PublicKeyCredentialType k : values()) {
      valueMap.put(k.value, k);
    }
  }
}
