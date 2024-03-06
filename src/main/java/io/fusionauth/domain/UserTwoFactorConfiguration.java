/*
 * Copyright (c) 2021-2023, FusionAuth, All Rights Reserved
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
@JsonIgnoreProperties(value = "enabled", allowGetters = true, allowSetters = false)
public class UserTwoFactorConfiguration implements Buildable<UserTwoFactorConfiguration> {
  public final List<TwoFactorMethod> methods = new ArrayList<>();

  public final List<String> recoveryCodes = new ArrayList<>();

  @JacksonConstructor
  public UserTwoFactorConfiguration() {
  }

  public UserTwoFactorConfiguration(UserTwoFactorConfiguration other) {
    other.methods.forEach(m -> methods.add(new TwoFactorMethod(m)));
    this.recoveryCodes.addAll(other.recoveryCodes);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserTwoFactorConfiguration that = (UserTwoFactorConfiguration) o;
    return Objects.equals(methods, that.methods) && Objects.equals(recoveryCodes, that.recoveryCodes);
  }

  @JsonIgnore
  public TwoFactorMethod getLastUsedMethod() {
    if (methods.size() == 1) {
      return methods.get(0);
    }

    return methods.stream().filter(m -> m.lastUsed != null && m.lastUsed).findFirst().orElse(null);
  }

  @JsonIgnore
  public TwoFactorMethod getMethodById(String id) {
    return methods.stream()
                  .filter(m -> m.id != null)
                  .filter(m -> m.id.equals(id))
                  .findFirst()
                  .orElse(null);
  }

  @Override
  public int hashCode() {
    return Objects.hash(methods, recoveryCodes);
  }

  public UserTwoFactorConfiguration secure() {
    recoveryCodes.clear();
    methods.forEach(TwoFactorMethod::secure);
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
