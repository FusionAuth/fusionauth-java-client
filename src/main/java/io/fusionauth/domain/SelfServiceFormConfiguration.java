/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author andrewpai
 */
public class SelfServiceFormConfiguration implements Buildable<SelfServiceFormConfiguration> {
  public boolean requireCurrentPasswordOnPasswordChange;

  @JacksonConstructor
  public SelfServiceFormConfiguration() {
  }

  public SelfServiceFormConfiguration(SelfServiceFormConfiguration other) {
    this.requireCurrentPasswordOnPasswordChange = other.requireCurrentPasswordOnPasswordChange;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SelfServiceFormConfiguration that = (SelfServiceFormConfiguration) o;
    return requireCurrentPasswordOnPasswordChange == that.requireCurrentPasswordOnPasswordChange;
  }

  @Override
  public int hashCode() {
    return Objects.hash(requireCurrentPasswordOnPasswordChange);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
