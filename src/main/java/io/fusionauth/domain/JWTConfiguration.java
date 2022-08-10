/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
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

  /**
   * The length of time in minutes a Refresh Token is valid from the time it was issued. This should be a non-zero value.
   */
  public int refreshTokenTimeToLiveInMinutes = 43200;

  public RefreshTokenUsagePolicy refreshTokenUsagePolicy = RefreshTokenUsagePolicy.Reusable;

  /**
   * The length of time in seconds this JWT is valid from the time it was issued. This should be a non-zero value.
   */
  public int timeToLiveInSeconds = 3600;

  @JacksonConstructor
  public JWTConfiguration() {
  }

  public JWTConfiguration(JWTConfiguration other) {
    this.accessTokenKeyId = other.accessTokenKeyId;
    this.enabled = other.enabled;
    this.idTokenKeyId = other.idTokenKeyId;
    this.refreshTokenExpirationPolicy = other.refreshTokenExpirationPolicy;
    this.refreshTokenRevocationPolicy = other.refreshTokenRevocationPolicy;
    this.refreshTokenTimeToLiveInMinutes = other.refreshTokenTimeToLiveInMinutes;
    this.refreshTokenUsagePolicy = other.refreshTokenUsagePolicy;
    this.timeToLiveInSeconds = other.timeToLiveInSeconds;
  }

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
           Objects.equals(accessTokenKeyId, that.accessTokenKeyId) &&
           Objects.equals(idTokenKeyId, that.idTokenKeyId) &&
           Objects.equals(refreshTokenExpirationPolicy, that.refreshTokenExpirationPolicy) &&
           Objects.equals(refreshTokenTimeToLiveInMinutes, that.refreshTokenTimeToLiveInMinutes) &&
           Objects.equals(refreshTokenUsagePolicy, that.refreshTokenUsagePolicy) &&
           Objects.equals(timeToLiveInSeconds, that.timeToLiveInSeconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), accessTokenKeyId, idTokenKeyId, refreshTokenExpirationPolicy, refreshTokenTimeToLiveInMinutes, refreshTokenUsagePolicy, timeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
