/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class TenantUnverifiedConfiguration implements Buildable<TenantUnverifiedConfiguration> {
  public UnverifiedBehavior email;

  public RegistrationUnverifiedOptions whenGated = new RegistrationUnverifiedOptions();

  public TenantUnverifiedConfiguration(TenantUnverifiedConfiguration other) {
    this.email = other.email;
    this.whenGated = new RegistrationUnverifiedOptions(other.whenGated);
  }

  @JacksonConstructor
  public TenantUnverifiedConfiguration() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TenantUnverifiedConfiguration that = (TenantUnverifiedConfiguration) o;
    return email == that.email &&
           Objects.equals(whenGated, that.whenGated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, whenGated);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
