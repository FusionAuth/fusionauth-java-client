/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * COSE key type
 *
 * @author Spencer Witt
 */
public enum CoseKeyType {
  /**
   * Reserved by specification
   */
  Reserved(0),

  /**
   * Octet Key Pair. An elliptic curve key represented by a single point
   */
  OKP(1),

  /**
   * Elliptic curve key represented with two points
   */
  EC2(2),

  /**
   * RSA encryption key
   */
  RSA(3),

  /**
   * Symmetric encryption key
   */
  Symmetric(4);

  private static final Map<Integer, CoseKeyType> valueMap = new HashMap<>();

  @JsonValue
  // The IANA registry Id
  public final int registryId;

  CoseKeyType(int registryId) {
    this.registryId = registryId;
  }

  @JsonCreator
  public static CoseKeyType forValue(int value) {
    return valueMap.get(value);
  }

  static {
    for (CoseKeyType k : values()) {
      valueMap.put(k.registryId, k);
    }
  }
}
