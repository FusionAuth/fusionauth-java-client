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
public class RefreshTokenRevocationPolicy {
  public boolean onLoginPrevented;

  public boolean onMultiFactorEnable;

  public boolean onPasswordChanged;

  @JacksonConstructor
  public RefreshTokenRevocationPolicy() {
  }

  public RefreshTokenRevocationPolicy(boolean onLoginPrevented, boolean onPasswordChanged) {
    this.onLoginPrevented = onLoginPrevented;
    this.onPasswordChanged = onPasswordChanged;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefreshTokenRevocationPolicy that = (RefreshTokenRevocationPolicy) o;
    return onLoginPrevented == that.onLoginPrevented && onMultiFactorEnable == that.onMultiFactorEnable && onPasswordChanged == that.onPasswordChanged;
  }

  @Override
  public int hashCode() {
    return Objects.hash(onLoginPrevented, onMultiFactorEnable, onPasswordChanged);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}