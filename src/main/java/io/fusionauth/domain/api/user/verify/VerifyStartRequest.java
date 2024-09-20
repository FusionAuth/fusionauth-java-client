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
package io.fusionauth.domain.api.user.verify;

import java.util.Arrays;import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.IdentityTypes;

public class VerifyStartRequest {
  public final String loginId;

  public final IdentityTypes loginType;

  public UUID applicationId;

  public CodeTypes codeType;

  public VerifyStartRequest(String loginId, IdentityTypes loginType) {
    this.loginId = loginId;
    this.loginType = loginType;
  }

  @JacksonConstructor
  private VerifyStartRequest() {
    loginId = null;
    loginType = null;
  }

  public static class CodeTypes {
    public static final CodeTypes clickable = new CodeTypes("clickable");

    public static final CodeTypes shortCode = new CodeTypes("shortCode");

    private static final HashSet<CodeTypes> values = new HashSet<>(
        Arrays.asList(
            CodeTypes.clickable,
            CodeTypes.shortCode
        )
    );

    private final String value;

    private CodeTypes(String value) {
      this.value = value;
    }

    @JacksonConstructor
    private CodeTypes() {
      this.value = null;
    }

    public static Collection<CodeTypes> values() {
      return values;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CodeTypes)) {
        return false;
      }
      CodeTypes codeTypes = (CodeTypes) o;
      return Objects.equals(value, codeTypes.value);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(value);
    }

    public boolean isValid() {
      return values.contains(this);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
