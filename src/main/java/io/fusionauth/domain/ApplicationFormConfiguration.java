/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ApplicationFormConfiguration implements Buildable<ApplicationFormConfiguration> {
  public UUID adminRegistrationFormId;

  public SelfServiceFormConfiguration selfServiceFormConfiguration = new SelfServiceFormConfiguration();

  public UUID selfServiceFormId;

  @JacksonConstructor
  public ApplicationFormConfiguration() {
  }

  public ApplicationFormConfiguration(ApplicationFormConfiguration other) {
    this.adminRegistrationFormId = other.adminRegistrationFormId;
    this.selfServiceFormConfiguration = new SelfServiceFormConfiguration(other.selfServiceFormConfiguration);
    this.selfServiceFormId = other.selfServiceFormId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationFormConfiguration that = (ApplicationFormConfiguration) o;
    return Objects.equals(adminRegistrationFormId, that.adminRegistrationFormId)
           && Objects.equals(selfServiceFormId, that.selfServiceFormId)
           && Objects.equals(selfServiceFormConfiguration, that.selfServiceFormConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminRegistrationFormId, selfServiceFormId, selfServiceFormConfiguration);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
