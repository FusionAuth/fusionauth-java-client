/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
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
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * JWT Configuration. A JWT Configuration for an Application may not be active if it is using the global configuration, the configuration
 * may be <code>enabled = false</code>.
 *
 * @author Daniel DeGroff
 */
public class JWTConfiguration extends Enableable implements Buildable<JWTConfiguration> {
  /**
   * The signing key used to sign the access token
   */
  public UUID accessTokenKeyId;

  /**
   * The signing key used to sign the Id token
   */
  public UUID idTokenKeyId;

  public RefreshTokenExpirationPolicy refreshTokenExpirationPolicy = RefreshTokenExpirationPolicy.Fixed;

  /**
   * This can only be set at the tenant level.
   */
  public RefreshTokenRevocationPolicy refreshTokenRevocationPolicy = new RefreshTokenRevocationPolicy(true, true);

  public RefreshTokenSlidingWindowConfiguration refreshTokenSlidingWindowConfiguration = new RefreshTokenSlidingWindowConfiguration();

  /**
   * The length of time in minutes a Refresh Token is valid from the time it was issued. This should be a non-zero value.
   * <p>
   * Default is set at 30 days.
   */
  public int refreshTokenTimeToLiveInMinutes = 30 * 24 * 60;

  public RefreshTokenUsagePolicy refreshTokenUsagePolicy = RefreshTokenUsagePolicy.Reusable;

  /**
   * The length of time in seconds this JWT is valid from the time it was issued. This should be a non-zero value.
   */
  public int timeToLiveInSeconds = 60 * 60;

  @JacksonConstructor
  public JWTConfiguration() {
  }

  public JWTConfiguration(JWTConfiguration other) {
    this.accessTokenKeyId = other.accessTokenKeyId;
    this.enabled = other.enabled;
    this.idTokenKeyId = other.idTokenKeyId;
    this.refreshTokenExpirationPolicy = other.refreshTokenExpirationPolicy;
    this.refreshTokenRevocationPolicy = new RefreshTokenRevocationPolicy(other.refreshTokenRevocationPolicy);
    this.refreshTokenSlidingWindowConfiguration = new RefreshTokenSlidingWindowConfiguration(other.refreshTokenSlidingWindowConfiguration);
    this.refreshTokenTimeToLiveInMinutes = other.refreshTokenTimeToLiveInMinutes;
    this.refreshTokenUsagePolicy = other.refreshTokenUsagePolicy;
    this.timeToLiveInSeconds = other.timeToLiveInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    JWTConfiguration that = (JWTConfiguration) o;
    return Objects.equals(accessTokenKeyId, that.accessTokenKeyId) &&
           Objects.equals(idTokenKeyId, that.idTokenKeyId) &&
           refreshTokenExpirationPolicy == that.refreshTokenExpirationPolicy &&
           Objects.equals(refreshTokenRevocationPolicy, that.refreshTokenRevocationPolicy) &&
           Objects.equals(refreshTokenSlidingWindowConfiguration, that.refreshTokenSlidingWindowConfiguration) &&
           refreshTokenTimeToLiveInMinutes == that.refreshTokenTimeToLiveInMinutes &&
           refreshTokenUsagePolicy == that.refreshTokenUsagePolicy &&
           timeToLiveInSeconds == that.timeToLiveInSeconds;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(),
                        accessTokenKeyId,
                        idTokenKeyId,
                        refreshTokenExpirationPolicy,
                        refreshTokenRevocationPolicy,
                        refreshTokenSlidingWindowConfiguration,
                        refreshTokenTimeToLiveInMinutes,
                        refreshTokenUsagePolicy,
                        timeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
