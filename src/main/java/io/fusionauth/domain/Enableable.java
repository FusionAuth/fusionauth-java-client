/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

/**
 * Something that can be enabled, and thus also disabled.
 *
 * @author Daniel DeGroff
 */
public abstract class Enableable {
  public boolean enabled;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enableable)) {
      return false;
    }
    Enableable that = (Enableable) o;
    return enabled == that.enabled;
  }

  @Override
  public int hashCode() {
    return Objects.hash(enabled);
  }
}
