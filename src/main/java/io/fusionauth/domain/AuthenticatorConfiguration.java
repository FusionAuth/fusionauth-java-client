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
public class AuthenticatorConfiguration implements Buildable<AuthenticatorConfiguration> {
  public TOTPAlgorithm algorithm;

  public int codeLength;

  public int timeStep;

  @JacksonConstructor
  public AuthenticatorConfiguration() {
  }

  public AuthenticatorConfiguration(AuthenticatorConfiguration other) {
    this.algorithm = other.algorithm;
    this.codeLength = other.codeLength;
    this.timeStep = other.timeStep;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthenticatorConfiguration that = (AuthenticatorConfiguration) o;
    return codeLength == that.codeLength && timeStep == that.timeStep && algorithm == that.algorithm;
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, codeLength, timeStep);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum TOTPAlgorithm {
    HmacSHA1,
    HmacSHA256,
    HmacSHA512
  }
}
