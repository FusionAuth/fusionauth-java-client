/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.jwt.domain.Algorithm;

/**
 * JWT Configuration. A JWT Configuration for an Application may not be active if it is using the global configuration, the configuration
 * may be <code>enabled = false</code>.
 *
 * @author Daniel DeGroff
 */
public class JWTConfiguration extends Enableable implements Buildable<JWTConfiguration> {
  /**
   * The configured algorithm used for signing and verification of JWTs.
   */
  public Algorithm algorithm;

  /**
   * The Issuer of the JWT.
   */
  public String issuer;

  /**
   * RSA Private Key used for RSA algorithms.
   */
  public String privateKey;

  /**
   * RSA Public Key used for RSA algorithms.
   */
  public String publicKey;

  /**
   * The length of time in minutes a Refresh Token is valid from the time it was issued. This should be a non-zero value.
   */
  public int refreshTokenTimeToLiveInMinutes;

  /**
   * HMAC Secret used for HMAC algorithms.
   */
  public String secret;

  /**
   * The length of time in seconds this JWT is valid from the time it was issued. This should be a non-zero value.
   */
  public int timeToLiveInSeconds;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JWTConfiguration)) {
      return false;
    }
    JWTConfiguration that = (JWTConfiguration) o;
    return super.equals(o) &&
        Objects.equals(algorithm, that.algorithm) &&
        Objects.equals(issuer, that.issuer) &&
        Objects.equals(privateKey, that.privateKey) &&
        Objects.equals(publicKey, that.publicKey) &&
        Objects.equals(refreshTokenTimeToLiveInMinutes, that.refreshTokenTimeToLiveInMinutes) &&
        Objects.equals(secret, that.secret) &&
        Objects.equals(timeToLiveInSeconds, that.timeToLiveInSeconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), algorithm, issuer, privateKey, publicKey, refreshTokenTimeToLiveInMinutes, secret, timeToLiveInSeconds);
  }

  public void normalize() {
    // Normalize Line returns in the public / private keys
    if (publicKey != null) {
      publicKey = publicKey.replace("\r\n", "\n").replace("\r", "\n");
    }
    if (privateKey != null) {
      privateKey = privateKey.replace("\r\n", "\n").replace("\r", "\n");
    }
  }

  public JWTConfiguration secure() {
    privateKey = null;
    secret = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
