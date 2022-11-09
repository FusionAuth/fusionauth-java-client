/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * COSE Elliptic Curve identifier to determine which elliptic curve to use with a given key
 *
 * @author Spencer Witt
 */
public enum CoseEllipticCurve {
  /**
   * Reserved by specification
   */
  Reserved(0, "N/A"),

  /**
   * NIST P-256 also known as secp256r1. For use with {@link CoseKeyType#EC2}
   */
  P256(1, "secp256r1"),

  /**
   * NIST P-384 also known as secp384r1. For use with {@link CoseKeyType#EC2}
   */
  P384(2, "secp384r1"),

  /**
   * NIST P-521 also known as secp521r1. For use with {@link CoseKeyType#EC2}
   */
  P521(3, "secp521r1"),

  /**
   * X25519 for use w/ ECDH only. For use with {@link CoseKeyType#OKP}
   */
  X25519(4, "X25519"),

  /**
   * X448 for use w/ ECDH only. For use with {@link CoseKeyType#OKP}
   */
  X448(5, "X448"),

  /**
   * Ed25519 for use w/ EdDSA only. For use with {@link CoseKeyType#OKP}
   */
  Ed25519(6, "Ed25519"),

  /**
   * Ed448 for use w/ EdDSA only. For use with {@link CoseKeyType#OKP}
   */
  Ed448(7, "Ed448"),

  /**
   * SECG secp256k1 curve. For use with {@link CoseKeyType#EC2}
   */
  Secp256k1(8, "secp256k1");

  private static final Map<Integer, CoseEllipticCurve> valueMap = new HashMap<>();

  public final String curve;

  @JsonValue
  // The IANA registry Id
  public final int registryId;

  CoseEllipticCurve(int registryId, String curve) {
    this.registryId = registryId;
    this.curve = curve;
  }

  @JsonCreator
  public static CoseEllipticCurve forValue(int registryId) {
    return valueMap.get(registryId);
  }

  static {
    for (CoseEllipticCurve c : values()) {
      valueMap.put(c.registryId, c);
    }
  }
}
