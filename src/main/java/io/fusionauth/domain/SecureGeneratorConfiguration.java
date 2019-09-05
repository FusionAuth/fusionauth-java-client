/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
public class SecureGeneratorConfiguration {
  public int length;

  public SecureGeneratorType type;

  @JacksonConstructor
  public SecureGeneratorConfiguration() {
  }

  public SecureGeneratorConfiguration(int length, SecureGeneratorType type) {
    this.length = length;
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SecureGeneratorConfiguration)) {
      return false;
    }
    SecureGeneratorConfiguration that = (SecureGeneratorConfiguration) o;
    return length == that.length &&
        type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(length, type);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}