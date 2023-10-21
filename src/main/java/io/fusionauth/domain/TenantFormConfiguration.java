/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class TenantFormConfiguration implements Buildable<TenantFormConfiguration> {
  public UUID adminUserFormId;

  @JacksonConstructor
  public TenantFormConfiguration() {
  }

  public TenantFormConfiguration(TenantFormConfiguration other) {
    this.adminUserFormId = other.adminUserFormId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TenantFormConfiguration that = (TenantFormConfiguration) o;
    return Objects.equals(adminUserFormId, that.adminUserFormId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminUserFormId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
