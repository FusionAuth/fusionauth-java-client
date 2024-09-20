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

import java.util.Arrays;import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

// This can't stay an ENUM but it avoid hard coding strings that need to be found/pulled out later in DEV
public class IdentityTypes {
  public static final IdentityTypes email = new IdentityTypes("email");

  public static final IdentityTypes phoneNumber = new IdentityTypes("phoneNumber");

  public static final IdentityTypes username = new IdentityTypes("username");

  private static final HashSet<IdentityTypes> values = new HashSet<>(
      Arrays.asList(
          email,
          phoneNumber,
          username
      )
  );

  public final String value;

  private IdentityTypes(String value) {
    this.value = value;
  }

  @JacksonConstructor
  private IdentityTypes() {
    this.value = null;
  }

  public static Collection<IdentityTypes> values() {
    return values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdentityTypes)) {
      return false;
    }
    IdentityTypes that = (IdentityTypes) o;
    return Objects.equals(value, that.value);
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
