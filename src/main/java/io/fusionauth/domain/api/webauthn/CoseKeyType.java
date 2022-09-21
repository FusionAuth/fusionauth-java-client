/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.fusionauth.client.json.CoseKeyTypeDeserializer;
import io.fusionauth.client.json.CoseKeyTypeSerializer;

/**
 * COSE key type
 *
 * @author Spencer Witt
 */
@JsonDeserialize(using = CoseKeyTypeDeserializer.class)
@JsonSerialize(using = CoseKeyTypeSerializer.class)
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

  private static final Map<Integer, CoseKeyType> BY_KTY = new HashMap<>();

  public final int kty;

  CoseKeyType(int kty) {
    this.kty = kty;
  }

  public static CoseKeyType valueOfKty(int kty) {
    return BY_KTY.get(kty);
  }

  static {
    for (CoseKeyType k : values()) {
      BY_KTY.put(k.kty, k);
    }
  }
}
