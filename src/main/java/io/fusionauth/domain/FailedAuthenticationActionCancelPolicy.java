/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class FailedAuthenticationActionCancelPolicy {

  /**
   * True if the action should be canceled after a successful password reset. This would allow the user to utilize a self-service option to unlock
   * their account prior to the action timeout.
   */
  public boolean onPasswordReset;

  @JacksonConstructor
  public FailedAuthenticationActionCancelPolicy() {
  }

  public FailedAuthenticationActionCancelPolicy(FailedAuthenticationActionCancelPolicy other) {
    this.onPasswordReset = other.onPasswordReset;
  }

  public FailedAuthenticationActionCancelPolicy(boolean onPasswordReset) {
    this.onPasswordReset = onPasswordReset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FailedAuthenticationActionCancelPolicy that = (FailedAuthenticationActionCancelPolicy) o;
    return onPasswordReset == that.onPasswordReset;
  }

  @Override
  public int hashCode() {
    return Objects.hash(onPasswordReset);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}