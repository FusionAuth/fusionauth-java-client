/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A number identifying a cryptographic algorithm. Values should be registered with the <a
 * href="https://www.iana.org/assignments/cose/cose.xhtml#algorithms">IANA COSE Algorithms registry</a>
 *
 * @author Spencer Witt
 */
public enum CoseAlgorithmIdentifier {
  /**
   * ECDSA using P-256 and SHA-256 OID: 1.2.840.10045.3.1.7 - prime256v1 / secp256r1
   */
  ES256(-7, "SHA256withECDSA", CoseKeyType.EC2),

  /**
   * ECDSA using P-384 and SHA-384 OID: 1.3.132.0.34 - secp384r1 / secp384r1
   */
  ES384(-35, "SHA384withECDSA", CoseKeyType.EC2),

  /**
   * ECDSA using P-521 and SHA-512 OID: 1.3.132.0.35 - prime521v1 / secp521r1
   */
  ES512(-36, "SHA512withECDSA", CoseKeyType.EC2),

  /**
   * RSASSA-PKCS1-v1_5 using SHA-256
   */
  RS256(-257, "SHA256withRSA", CoseKeyType.RSA),

  /**
   * RSASSA-PKCS1-v1_5 using SHA-384
   */
  RS384(-258, "SHA384withRSA", CoseKeyType.RSA),

  /**
   * RSASSA-PKCS1-v1_5 using SHA-512
   */
  RS512(-259, "SHA512withRSA", CoseKeyType.RSA),

  /**
   * RSASSA-PSS using SHA-256 and MGF1 with SHA-256 - SHA256withRSAandMGF1
   */
  PS256(-37, "SHA-256", CoseKeyType.RSA),

  /**
   * RSASSA-PSS using SHA-384 and MGF1 with SHA-384 - SHA384withRSAandMGF1
   */
  PS384(-38, "SHA-384", CoseKeyType.RSA),

  /**
   * RSASSA-PSS using SHA-512 and MGF1 with SHA-512 - SHA512withRSAandMGF1
   */
  PS512(-39, "SHA-512", CoseKeyType.RSA);

  private static final Map<Long, CoseAlgorithmIdentifier> valueMap = new HashMap<>();

  public final String description;

  public final CoseKeyType keyType;

  @JsonValue
  // The IANA registry Id
  public final long registryId;

  CoseAlgorithmIdentifier(long registryId, String description, CoseKeyType keyType) {
    this.registryId = registryId;
    this.description = description;
    this.keyType = keyType;
  }

  @JsonCreator
  public static CoseAlgorithmIdentifier forValue(long registryId) {
    return valueMap.get(registryId);
  }

  public CoseEllipticCurve getCurve() {
    switch (this) {
      case ES256:
        return CoseEllipticCurve.P256;
      case ES384:
        return CoseEllipticCurve.P384;
      case ES512:
        return CoseEllipticCurve.P521;
      default:
        throw new IllegalStateException("An incompatible algorithm was provided, this method is only used for ECDSA algorithms.");
    }
  }

  public int getSaltLength() {
    switch (this) {
      case PS256:
        return 32;
      case PS384:
        return 48;
      case PS512:
        return 64;
      default:
        throw new IllegalStateException("An incompatible algorithm was provided, this method is only used for RSASSA-PSS algorithms.");
    }
  }

  @Override
  public String toString() {
    return String.format("%s (%d)", description, registryId);
  }

  static {
    for (CoseAlgorithmIdentifier a : values()) {
      valueMap.put(a.registryId, a);
    }
  }
}
