/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
import com.inversoft.json.JacksonConstructor;
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

  // Using a non-primitive so that we can omit this from the serialized response for HMAC keys
  public Boolean hasPrivateKey;

  public UUID id;

  public ZonedDateTime insertInstant;

  public String issuer;

  public String kid;

  public ZonedDateTime lastUpdateInstant;

  // Response only
  public Integer length;

  public String name;

  public String privateKey;

  public String publicKey;

  public String secret;

  public KeyType type;

  @JacksonConstructor
  public Key() {
  }

  public Key(Key key) {
    this.algorithm = key.algorithm;
    this.certificate = key.certificate;
    this.certificateInformation = key.certificateInformation;
    this.expirationInstant = key.expirationInstant;
    this.hasPrivateKey = key.hasPrivateKey;
    this.id = key.id;
    this.insertInstant = key.insertInstant;
    this.issuer = key.issuer;
    this.kid = key.kid;
    this.lastUpdateInstant = key.lastUpdateInstant;
    this.length = key.length;
    this.name = key.name;
    this.privateKey = key.privateKey;
    this.publicKey = key.publicKey;
    this.secret = key.secret;
    this.type = key.type;
  }

  @JsonIgnore
  public boolean canSign() {
    return use() != KeyUse.VerifyOnly;
  }

  @JsonIgnore
  public boolean canVerify() {
    return use() != KeyUse.SignOnly;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Key)) {
      return false;
    }
    Key key = (Key) o;
    return algorithm == key.algorithm &&
           Objects.equals(certificate, key.certificate) &&
           Objects.equals(certificateInformation, key.certificateInformation) &&
           Objects.equals(expirationInstant, key.expirationInstant) &&
           Objects.equals(hasPrivateKey, key.hasPrivateKey) &&
           Objects.equals(id, key.id) &&
           Objects.equals(insertInstant, key.insertInstant) &&
           Objects.equals(issuer, key.issuer) &&
           Objects.equals(kid, key.kid) &&
           Objects.equals(lastUpdateInstant, key.lastUpdateInstant) &&
           Objects.equals(length, key.length) &&
           Objects.equals(name, key.name) &&
           Objects.equals(privateKey, key.privateKey) &&
           Objects.equals(publicKey, key.publicKey) &&
           Objects.equals(secret, key.secret) &&
           type == key.type;
  }

  @JsonIgnore
  public String getDisplayName() {
    if (algorithm == null) {
      return name + " (" + type.name() + ")";
    }

    return name + " (" + algorithm.name() + ")";
  }

  @JsonIgnore
  public boolean hasPrivateKey() {
    return privateKey != null || (hasPrivateKey != null && hasPrivateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, certificate, certificateInformation, expirationInstant, hasPrivateKey, id, insertInstant, issuer, kid, lastUpdateInstant, length, name, privateKey, publicKey, secret, type);
  }

  @JsonIgnore
  public boolean isExpired() {
    if (expirationInstant == null) {
      return false;
    }

    return ZonedDateTime.now(ZoneOffset.UTC).isAfter(expirationInstant);
  }

  /**
   * @return True if the key has a public and private key.
   */
  @JsonIgnore
  public boolean isPair() {
    return (publicKey != null || certificate != null) && hasPrivateKey();
  }

  public void normalize() {
    // Normalize the line-returns in the public / private keys
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

  @JsonIgnore
  public boolean privateKeyOnly() {
    return hasPrivateKey() && publicKey == null && certificate == null;
  }

  public Key secure() {
    privateKey = null;
    secret = null;
    return this;
  }

  public String toString() {
    return ToString.toString(this);
  }

  @JsonIgnore
  public boolean use(KeyUse use) {
    return use().equals(use);
  }

  public KeyUse use() {
    // If we have an algorithm and it is either HMAC or a key pair, then we can sign and verify.
    if (algorithm != null && (isPair() || type == KeyType.HMAC)) {
      return KeyUse.SignAndVerify;
    } else if (hasPrivateKey()) {
      return KeyUse.SignOnly;
    } else {
      return KeyUse.VerifyOnly;
    }
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
