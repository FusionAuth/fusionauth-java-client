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
public class RegistrationUnverifiedOptions implements Buildable<RegistrationUnverifiedOptions> {
  public UnverifiedBehavior behavior = UnverifiedBehavior.Allow;

  @JacksonConstructor
  public RegistrationUnverifiedOptions() {
  }

  public RegistrationUnverifiedOptions(RegistrationUnverifiedOptions other) {
    this.behavior = other.behavior;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationUnverifiedOptions that = (RegistrationUnverifiedOptions) o;
    return behavior == that.behavior;
  }

  @Override
  public int hashCode() {
    return Objects.hash(behavior);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}