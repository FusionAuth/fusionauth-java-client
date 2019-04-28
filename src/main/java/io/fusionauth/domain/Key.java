/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package io.fusionauth.domain;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;

/**
 * Domain for a public key, key pair or an HMAC secret. This is used by KeyMaster to manage keys for JWTs, SAML, etc.
 *
 * @author Brian Pontarelli
 */
public class Key implements Buildable<Key> {
  public KeyAlgorithm algorithm;

  public String certificate;

  // Response only
  public CertificateInformation certificateInformation;

  public ZonedDateTime expirationInstant;

  public UUID id;

  public ZonedDateTime insertInstant;

  public String issuer;

  public String kid;

  // Response only
  public Integer length;

  public String name;

  public boolean pair;

  public String privateKey;

  public String publicKey;

  public String secret;

  public KeyType type;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Key)) {
      return false;
    }
    Key key = (Key) o;
    return Objects.equals(algorithm, key.algorithm) &&
        Objects.equals(certificateInformation, key.certificateInformation) &&
        Objects.equals(expirationInstant, key.expirationInstant) &&
        Objects.equals(id, key.id) &&
        Objects.equals(insertInstant, key.insertInstant) &&
        Objects.equals(issuer, key.issuer) &&
        Objects.equals(kid, key.kid) &&
        Objects.equals(length, key.length) &&
        Objects.equals(name, key.name) &&
        Objects.equals(pair, key.pair) &&
        Objects.equals(privateKey, key.privateKey) &&
        Objects.equals(publicKey, key.publicKey) &&
        Objects.equals(secret, key.secret) &&
        Objects.equals(type, key.type);
  }

  @JsonIgnore
  public String getDisplayName() {
    if (algorithm == null) {
      return name + " (" + type.name() + ")";
    }

    return name + " (" + algorithm.name() + ")";
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, certificateInformation, expirationInstant, id, insertInstant, issuer, kid, length, name, pair, privateKey, publicKey, secret, type);
  }

  @JsonIgnore
  public boolean isExpired() {
    if (expirationInstant == null) {
      return false;
    }

    return ZonedDateTime.now(ZoneOffset.UTC).isAfter(expirationInstant);
  }

  /**
   * This method only works if the private key is known. It won't work on API responses since the private key is never returned.
   *
   * @return True if the key has a public and private key.
   */
  @JsonIgnore
  public boolean isPair() {
    return pair;
  }

  public void normalize() {
    // Normalize Line returns in the public / private keys
    if (certificate != null) {
      certificate = certificate.replace("\r\n", "\n").replace("\r", "\n");
    }
    if (publicKey != null) {
      publicKey = publicKey.replace("\r\n", "\n").replace("\r", "\n");
    }
    if (privateKey != null) {
      privateKey = privateKey.replace("\r\n", "\n").replace("\r", "\n");
    }
  }

  public Key secure() {
    privateKey = null;
    secret = null;
    return this;
  }

  public String toString() {
    return ToString.toString(this);
  }

  public enum KeyAlgorithm {
    ES256("SHA256withECDSA"),
    ES384("SHA384withECDSA"),
    ES512("SHA512withECDSA"),
    HS256("HmacSHA256"),
    HS384("HmacSHA384"),
    HS512("HmacSHA512"),
    RS256("SHA256withRSA"),
    RS384("SHA384withRSA"),
    RS512("SHA512withRSA");

    public String algorithm;

    KeyAlgorithm(String algorithm) {
      this.algorithm = algorithm;
    }

    public String getName() {
      return algorithm;
    }
  }

  public enum KeyType {
    EC,
    RSA,
    HMAC
  }

  public static class CertificateInformation {
    public String issuer;

    public String md5Fingerprint;

    public String serialNumber;

    public String sha1Fingerprint;

    public String sha1Thumbprint;

    public String sha256Fingerprint;

    public String sha256Thumbprint;

    public String subject;

    public ZonedDateTime validFrom;

    public ZonedDateTime validTo;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CertificateInformation)) {
        return false;
      }
      CertificateInformation that = (CertificateInformation) o;
      return Objects.equals(issuer, that.issuer) &&
          Objects.equals(md5Fingerprint, that.md5Fingerprint) &&
          Objects.equals(serialNumber, that.serialNumber) &&
          Objects.equals(sha1Fingerprint, that.sha1Fingerprint) &&
          Objects.equals(sha1Thumbprint, that.sha1Thumbprint) &&
          Objects.equals(sha256Fingerprint, that.sha256Fingerprint) &&
          Objects.equals(sha256Thumbprint, that.sha256Thumbprint) &&
          Objects.equals(subject, that.subject) &&
          Objects.equals(validFrom, that.validFrom) &&
          Objects.equals(validTo, that.validTo);
    }

    @Override
    public int hashCode() {
      return Objects.hash(issuer, md5Fingerprint, serialNumber, sha1Fingerprint, sha1Thumbprint, sha256Fingerprint, sha256Thumbprint, subject, validFrom, validTo);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
