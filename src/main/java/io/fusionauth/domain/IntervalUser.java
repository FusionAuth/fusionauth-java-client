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
 * A user over an period (for daily and monthly active user calculations).
 *
 * @author Brian Pontarelli
 */
public class IntervalUser {
  public UUID applicationId;

  public int period;

  public UUID userId;

  public IntervalUser() {
  }

  public IntervalUser(UUID applicationId, int period, UUID userId) {
    this.applicationId = applicationId;
    this.period = period;
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IntervalUser)) {
      return false;
    }
    IntervalUser other = (IntervalUser) o;
    return Objects.equals(applicationId, other.applicationId) &&
        Objects.equals(period, other.period) &&
        Objects.equals(userId, other.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, period, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
