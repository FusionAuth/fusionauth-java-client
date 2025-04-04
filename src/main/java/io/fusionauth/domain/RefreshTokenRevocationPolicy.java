/*
 * Copyright (c) 2020-2024, FusionAuth, All Rights Reserved
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
public class RefreshTokenRevocationPolicy implements Buildable<RefreshTokenRevocationPolicy> {
  public boolean onLoginPrevented;

  public boolean onMultiFactorEnable;

  public boolean onOneTimeTokenReuse;

  public boolean onPasswordChanged;

  @JacksonConstructor
  public RefreshTokenRevocationPolicy() {
  }

  public RefreshTokenRevocationPolicy(RefreshTokenRevocationPolicy other) {
    this.onLoginPrevented = other.onLoginPrevented;
    this.onMultiFactorEnable = other.onMultiFactorEnable;
    this.onPasswordChanged = other.onPasswordChanged;
    this.onOneTimeTokenReuse = other.onOneTimeTokenReuse;
  }

  public RefreshTokenRevocationPolicy(boolean onLoginPrevented, boolean onPasswordChanged) {
    this.onLoginPrevented = onLoginPrevented;
    this.onPasswordChanged = onPasswordChanged;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefreshTokenRevocationPolicy that = (RefreshTokenRevocationPolicy) o;
    return onLoginPrevented == that.onLoginPrevented && onMultiFactorEnable == that.onMultiFactorEnable && onPasswordChanged == that.onPasswordChanged && onOneTimeTokenReuse == that.onOneTimeTokenReuse;
  }

  @Override
  public int hashCode() {
    return Objects.hash(onLoginPrevented, onMultiFactorEnable, onPasswordChanged, onOneTimeTokenReuse);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
