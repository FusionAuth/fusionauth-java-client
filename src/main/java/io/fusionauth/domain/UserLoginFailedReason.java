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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.error.Errors;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * The reason for the login failure.
 *
 * @author Daniel DeGroff
 */
public class UserLoginFailedReason implements Buildable<UserLoginFailedReason> {
  public String code;

  public UUID lambdaId;

  public Errors lambdaResult;

  @JacksonConstructor
  public UserLoginFailedReason() {
  }

  public UserLoginFailedReason(String code) {
    this.code = code;
  }

  public UserLoginFailedReason(String code, UUID lambdaId, Errors lambdaResult) {
    this.code = code;
    this.lambdaId = lambdaId;
    this.lambdaResult = lambdaResult;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserLoginFailedReason that = (UserLoginFailedReason) o;
    return Objects.equals(code, that.code) && Objects.equals(lambdaId, that.lambdaId) && Objects.equals(lambdaResult, that.lambdaResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, lambdaId, lambdaResult);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
