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
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;

/**
 * Configuration for the behavior of failed login attempts. This helps us protect against brute force password attacks.
 *
 * @author Daniel DeGroff
 */
public class FailedAuthenticationConfiguration implements Buildable<FailedAuthenticationConfiguration> {
  /**
   * The duration of the action.
   */
  public long actionDuration = 3;

  /**
   * The Unit of time of the Action Duration.
   */
  public ExpiryUnit actionDurationUnit = ExpiryUnit.MINUTES;

  /**
   * The length of time in seconds the failed login attempt is kept in the cache. This essentially causes the failed
   * login count to fail after this period of time.
   */
  public int resetCountInSeconds = 60;

  /**
   * Number of failed login attempts considered to be too many.
   */
  public int tooManyAttempts = 5;

  /**
   * Id of the User Action used when a user reaches the threshold defined by <code>tooManyAttempts</code>.
   */
  @ExcludeFromDatabaseDataColumn
  public UUID userActionId;

  public FailedAuthenticationConfiguration() {
  }

  public FailedAuthenticationConfiguration(FailedAuthenticationConfiguration other) {
    this.actionDuration = other.actionDuration;
    this.actionDurationUnit = other.actionDurationUnit;
    this.resetCountInSeconds = other.resetCountInSeconds;
    this.tooManyAttempts = other.tooManyAttempts;
    this.userActionId = other.userActionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FailedAuthenticationConfiguration that = (FailedAuthenticationConfiguration) o;
    return actionDuration == that.actionDuration &&
        actionDurationUnit == that.actionDurationUnit &&
        tooManyAttempts == that.tooManyAttempts &&
        resetCountInSeconds == that.resetCountInSeconds &&
        Objects.equals(userActionId, that.userActionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionDuration, actionDurationUnit, userActionId, tooManyAttempts, resetCountInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
