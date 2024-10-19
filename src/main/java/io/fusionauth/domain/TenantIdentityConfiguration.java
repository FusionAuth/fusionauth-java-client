/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class TenantIdentityConfiguration implements Buildable<TenantIdentityConfiguration> {
  // TODO : ENG-1826 : Likely going to remove this prior to ship.
  //        ENG-1825 : Maybe add this in full later once we have a concrete use.
  public Set<String> enabledTypes = new TreeSet<>(Arrays.asList("email", "username"));

  // TODO : ENG-1 : Daniel : Could we fix this by adding an API version concept to know when to use the new strategy?
  public TenantIdentityConfigurationMode mode = TenantIdentityConfigurationMode.Compatible;

  @JacksonConstructor
  public TenantIdentityConfiguration() {
  }

  public TenantIdentityConfiguration(TenantIdentityConfiguration other) {
    this.enabledTypes = new TreeSet<>(other.enabledTypes);
    this.mode = other.mode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TenantIdentityConfiguration that = (TenantIdentityConfiguration) o;
    return Objects.equals(enabledTypes, that.enabledTypes) && mode == that.mode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(enabledTypes, mode);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
