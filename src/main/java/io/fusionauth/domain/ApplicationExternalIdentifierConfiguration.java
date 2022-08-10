/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ApplicationExternalIdentifierConfiguration implements Buildable<ApplicationExternalIdentifierConfiguration> {
  public Integer twoFactorTrustIdTimeToLiveInSeconds;

  @JacksonConstructor
  public ApplicationExternalIdentifierConfiguration() {
  }

  public ApplicationExternalIdentifierConfiguration(ApplicationExternalIdentifierConfiguration other) {
    this.twoFactorTrustIdTimeToLiveInSeconds = other.twoFactorTrustIdTimeToLiveInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationExternalIdentifierConfiguration that = (ApplicationExternalIdentifierConfiguration) o;
    return Objects.equals(twoFactorTrustIdTimeToLiveInSeconds, that.twoFactorTrustIdTimeToLiveInSeconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(twoFactorTrustIdTimeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}