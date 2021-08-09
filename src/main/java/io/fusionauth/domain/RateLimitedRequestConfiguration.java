/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
 * @author Daniel DeGroff
 */
public class RateLimitedRequestConfiguration extends Enableable implements Buildable<RateLimitedRequestConfiguration> {
  public int limit;

  public int timePeriodInSeconds;

  @JacksonConstructor
  public RateLimitedRequestConfiguration() {
  }

  public RateLimitedRequestConfiguration(int limit, int timePeriodInSeconds) {
    this.limit = limit;
    this.timePeriodInSeconds = timePeriodInSeconds;
  }

  public RateLimitedRequestConfiguration(RateLimitedRequestConfiguration other) {
    this.enabled = other.enabled;
    this.limit = other.limit;
    this.timePeriodInSeconds = other.timePeriodInSeconds;
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
    RateLimitedRequestConfiguration that = (RateLimitedRequestConfiguration) o;
    return limit == that.limit && timePeriodInSeconds == that.timePeriodInSeconds;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), limit, timePeriodInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
