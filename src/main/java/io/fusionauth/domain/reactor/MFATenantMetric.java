/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.reactor;

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * Reactor metric with counts of MFA challenges, successes, and failures for a tenant.
 */
public class MFATenantMetric implements Buildable<MFATenantMetric> {
  public long challengeCount;

  public long failedAttemptCount;

  public long successCount;

  public MFATenantMetric() {
  }

  public MFATenantMetric(MFATenantMetric other) {
    this.challengeCount = other.challengeCount;
    this.failedAttemptCount = other.failedAttemptCount;
    this.successCount = other.successCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MFATenantMetric)) {
      return false;
    }
    MFATenantMetric that = (MFATenantMetric) o;
    return challengeCount == that.challengeCount &&
           failedAttemptCount == that.failedAttemptCount &&
           successCount == that.successCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(challengeCount, failedAttemptCount, successCount);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
