/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;

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
  @ExcludeFromDatabaseDataColumn
  public UUID accessTokenKeyId;

  /**
   * The signing key used to sign the Id token
   */
  @ExcludeFromDatabaseDataColumn
  public UUID idTokenKeyId;

  /**
   * The length of time in minutes a Refresh Token is valid from the time it was issued. This should be a non-zero value.
   */
  public int refreshTokenTimeToLiveInMinutes;

  /**
   * The length of time in seconds this JWT is valid from the time it was issued. This should be a non-zero value.
   */
  public int timeToLiveInSeconds;

  public JWTConfiguration() {
  }

  public JWTConfiguration(JWTConfiguration other) {
    this.accessTokenKeyId = other.accessTokenKeyId;
    this.enabled = other.enabled;
    this.idTokenKeyId = other.idTokenKeyId;
    this.refreshTokenTimeToLiveInMinutes = other.refreshTokenTimeToLiveInMinutes;
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
        Objects.equals(refreshTokenTimeToLiveInMinutes, that.refreshTokenTimeToLiveInMinutes) &&
        Objects.equals(timeToLiveInSeconds, that.timeToLiveInSeconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), accessTokenKeyId, idTokenKeyId, refreshTokenTimeToLiveInMinutes, timeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
