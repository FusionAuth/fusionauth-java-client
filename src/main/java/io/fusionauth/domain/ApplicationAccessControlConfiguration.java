/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;


/**
 * @author Daniel DeGroff
 */
public class ApplicationAccessControlConfiguration implements Buildable<ApplicationAccessControlConfiguration> {
  
  public UUID uiIPAccessControlListId;

  @JacksonConstructor
  public ApplicationAccessControlConfiguration() {
  }

  public ApplicationAccessControlConfiguration(ApplicationAccessControlConfiguration other) {
    this.uiIPAccessControlListId = other.uiIPAccessControlListId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationAccessControlConfiguration that = (ApplicationAccessControlConfiguration) o;
    return Objects.equals(uiIPAccessControlListId, that.uiIPAccessControlListId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uiIPAccessControlListId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
