/*
 * Copyright (c) 2022-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.Enableable;

/**
 * @author Daniel DeGroff
 */
public class LoginHintConfiguration extends Enableable implements Buildable<LoginHintConfiguration> {
  public String parameterName = "login_hint";

  @JacksonConstructor
  public LoginHintConfiguration() {
  }

  public LoginHintConfiguration(boolean enabled) {
    this.enabled = enabled;
  }

  public LoginHintConfiguration(LoginHintConfiguration other) {
    this.enabled = other.enabled;
    this.parameterName = other.parameterName;
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
    LoginHintConfiguration that = (LoginHintConfiguration) o;
    return Objects.equals(parameterName, that.parameterName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), parameterName);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
