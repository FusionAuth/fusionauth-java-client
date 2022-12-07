/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;


/**
 * Configuration for the behavior of failed login attempts. This helps us protect against brute force password attacks.
 *
 * @author Daniel DeGroff
 */
public class FailedAuthenticationConfiguration implements Buildable<FailedAuthenticationConfiguration> {
  public FailedAuthenticationActionCancelPolicy actionCancelPolicy = new FailedAuthenticationActionCancelPolicy(false);

  /**
   * The duration of the action.
   */
  public long actionDuration = 3;

  /**
   * The Unit of time of the Action Duration.
   */
  public ExpiryUnit actionDurationUnit = ExpiryUnit.MINUTES;

  /**
   * True if an email should be sent to the user when the failed action is applied to the user. This configuration only applies when the configured
   * action is also configured to email the user.
   */
  public boolean emailUser;

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
  
  public UUID userActionId;

  @JacksonConstructor
  public FailedAuthenticationConfiguration() {
  }

  public FailedAuthenticationConfiguration(FailedAuthenticationConfiguration other) {
    this.actionCancelPolicy = new FailedAuthenticationActionCancelPolicy(other.actionCancelPolicy);
    this.actionDuration = other.actionDuration;
    this.actionDurationUnit = other.actionDurationUnit;
    this.emailUser = other.emailUser;
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
    return actionDuration == that.actionDuration && emailUser == that.emailUser && resetCountInSeconds == that.resetCountInSeconds && tooManyAttempts == that.tooManyAttempts && Objects.equals(actionCancelPolicy, that.actionCancelPolicy) && actionDurationUnit == that.actionDurationUnit && Objects.equals(userActionId, that.userActionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionCancelPolicy, actionDuration, actionDurationUnit, emailUser, resetCountInSeconds, tooManyAttempts, userActionId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
