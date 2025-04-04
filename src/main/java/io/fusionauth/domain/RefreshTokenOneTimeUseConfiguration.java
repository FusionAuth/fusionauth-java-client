/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * Refresh token one-time use configuration. This configuration is utilized when the usage policy is
 * configured for one-time use.
 *
 * @author Daniel DeGroff
 */
public class RefreshTokenOneTimeUseConfiguration implements Buildable<RefreshTokenOneTimeUseConfiguration> {
  public int gracePeriodInSeconds;

  @JacksonConstructor
  public RefreshTokenOneTimeUseConfiguration() {
  }

  public RefreshTokenOneTimeUseConfiguration(RefreshTokenOneTimeUseConfiguration other) {
    this.gracePeriodInSeconds = other.gracePeriodInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefreshTokenOneTimeUseConfiguration that = (RefreshTokenOneTimeUseConfiguration) o;
    return gracePeriodInSeconds == that.gracePeriodInSeconds;
  }

  @Override
  public int hashCode() {
    return Objects.hash(gracePeriodInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
