/*
 * Copyright (c) 2025, FusionAuth, All Rights Reserved
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

/**
 * @author Lyle Schemmerling
 */
public class UniversalApplicationConfiguration { // TODO move to sep file/class and change name to UniversalApplicationConfiguration

  // This is a flag to indicate that all tenants can use this universal application
  public boolean global;

  // This is a flag to indicate that this application is universal and can be used by the configured application tenants
  public boolean universal;

  @JacksonConstructor
  public UniversalApplicationConfiguration() {
  }

  public UniversalApplicationConfiguration(UniversalApplicationConfiguration other) {
    this.global = other.global;
    this.universal = other.universal;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniversalApplicationConfiguration that = (UniversalApplicationConfiguration) o;
    return global == that.global && universal == that.universal;
  }

  @Override
  public int hashCode() {
    return Objects.hash(global, universal);
  }
}
