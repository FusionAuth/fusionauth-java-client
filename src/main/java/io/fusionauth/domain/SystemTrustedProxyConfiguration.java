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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.util.Normalizer;

/**
 * @author Daniel DeGroff
 */
public class SystemTrustedProxyConfiguration {
  public SystemTrustedProxyConfigurationPolicy trustPolicy = SystemTrustedProxyConfigurationPolicy.All;

  public List<String> trusted = new ArrayList<>();

  @JacksonConstructor
  public SystemTrustedProxyConfiguration() {
  }

  public SystemTrustedProxyConfiguration(SystemTrustedProxyConfiguration other) {
    this.trustPolicy = other.trustPolicy;
    this.trusted.addAll(other.trusted);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemTrustedProxyConfiguration that = (SystemTrustedProxyConfiguration) o;
    return trustPolicy == that.trustPolicy && Objects.equals(trusted, that.trusted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trustPolicy, trusted);
  }

  public void normalize() {
    Normalizer.removeEmpty(trusted);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
