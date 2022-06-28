/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.fusionauth.json.CoseEllipticCurveDeserializer;
import io.fusionauth.json.CoseEllipticCurveSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * COSE Elliptic Curve identifier to determine which elliptic curve to use with a given key
 *
 * @author Spencer Witt
 */
@JsonDeserialize(using = CoseEllipticCurveDeserializer.class)
@JsonSerialize(using = CoseEllipticCurveSerializer.class)
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

  private static final Map<Integer, CoseEllipticCurve> BY_CRV = new HashMap<>();

  public final int crv;

  public final String spec;

  CoseEllipticCurve(int crv, String spec) {
    this.crv = crv;
    this.spec = spec;
  }

  public static CoseEllipticCurve valueOfCrv(int crv) {
    return BY_CRV.get(crv);
  }

  static {
    for (CoseEllipticCurve c : values()) {
      BY_CRV.put(c.crv, c);
    }
  }
}
