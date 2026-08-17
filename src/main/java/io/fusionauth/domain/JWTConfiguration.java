/*
 * Copyright (c) 2019-2026, FusionAuth, All Rights Reserved
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

import java.util.ArrayList;
import java.util.List;
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
   * Alternate verification key IDs for access tokens. The signing key ({@link #accessTokenKeyId}) is implicitly
   * available for verification and does not need to be listed here.
   */
  public List<UUID> accessTokenVerificationKeyIds = new ArrayList<>();

  /**
   * The signing key used to sign the Id token
   */
  public UUID idTokenKeyId;

  /**
   * Alternate verification key IDs for id tokens. The signing key ({@link #idTokenKeyId}) is implicitly
   * available for verification and does not need to be listed here.
   */
  public List<UUID> idTokenVerificationKeyIds = new ArrayList<>();

  public RefreshTokenExpirationPolicy refreshTokenExpirationPolicy = RefreshTokenExpirationPolicy.Fixed;

  public RefreshTokenOneTimeUseConfiguration refreshTokenOneTimeUseConfiguration = new RefreshTokenOneTimeUseConfiguration();

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
    this.accessTokenVerificationKeyIds.addAll(other.accessTokenVerificationKeyIds);
    this.enabled = other.enabled;
    this.idTokenKeyId = other.idTokenKeyId;
    this.idTokenVerificationKeyIds.addAll(other.idTokenVerificationKeyIds);
    this.refreshTokenExpirationPolicy = other.refreshTokenExpirationPolicy;
    this.refreshTokenRevocationPolicy = new RefreshTokenRevocationPolicy(other.refreshTokenRevocationPolicy);
    this.refreshTokenOneTimeUseConfiguration = new RefreshTokenOneTimeUseConfiguration(other.refreshTokenOneTimeUseConfiguration);
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
           Objects.equals(accessTokenVerificationKeyIds, that.accessTokenVerificationKeyIds) &&
           Objects.equals(idTokenKeyId, that.idTokenKeyId) &&
           Objects.equals(idTokenVerificationKeyIds, that.idTokenVerificationKeyIds) &&
           refreshTokenExpirationPolicy == that.refreshTokenExpirationPolicy &&
           Objects.equals(refreshTokenRevocationPolicy, that.refreshTokenRevocationPolicy) &&
           Objects.equals(refreshTokenOneTimeUseConfiguration, that.refreshTokenOneTimeUseConfiguration) &&
           Objects.equals(refreshTokenSlidingWindowConfiguration, that.refreshTokenSlidingWindowConfiguration) &&
           refreshTokenTimeToLiveInMinutes == that.refreshTokenTimeToLiveInMinutes &&
           refreshTokenUsagePolicy == that.refreshTokenUsagePolicy &&
           timeToLiveInSeconds == that.timeToLiveInSeconds;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(),
                        accessTokenKeyId,
                        accessTokenVerificationKeyIds,
                        idTokenKeyId,
                        idTokenVerificationKeyIds,
                        refreshTokenExpirationPolicy,
                        refreshTokenRevocationPolicy,
                        refreshTokenOneTimeUseConfiguration,
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
