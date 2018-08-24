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
import java.util.UUID;

import com.inversoft.json.ToString;

/**
 * Counts for an period.
 *
 * @author Brian Pontarelli
 */
public class IntervalCount {
  public UUID applicationId;

  public int count;

  public int decrementedCount;

  public int period;

  public IntervalCount() {
  }

  public IntervalCount(UUID applicationId, int count, int decrementedCount, int period) {
    this.applicationId = applicationId;
    this.count = count;
    this.decrementedCount = decrementedCount;
    this.period = period;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IntervalCount)) {
      return false;
    }
    IntervalCount rawLogin = (IntervalCount) o;
    return Objects.equals(applicationId, rawLogin.applicationId) &&
        Objects.equals(count, rawLogin.count) &&
        Objects.equals(decrementedCount, rawLogin.decrementedCount) &&
        Objects.equals(period, rawLogin.period);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, count, decrementedCount, period);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
