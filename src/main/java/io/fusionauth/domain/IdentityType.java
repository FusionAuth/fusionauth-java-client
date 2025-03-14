/*
 * Copyright (c) 2024-2025, FusionAuth, All Rights Reserved
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Brady Wied
 */
public class IdentityType implements Comparable<IdentityType> {
  public static Map<String, IdentityType> Provided = new HashMap<>(3);

  public static IdentityType email = new IdentityType("email");

  public static IdentityType phoneNumber = new IdentityType("phoneNumber");

  public static IdentityType username = new IdentityType("username");

  public String name;

  public IdentityType(IdentityType other) {
    this.name = other != null ? other.name : null;
  }

  private IdentityType(String name) {
    this.name = name;
  }

  public static IdentityType of(String name) {
    if (name == null) {
      return null;
    }

    // returns null if not a Provided type
    return Provided.get(name);
  }

  @Override
  public int compareTo(IdentityType o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentityType that = (IdentityType) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public boolean is(IdentityType type) {
    return this == type || equals(type);
  }

  public boolean is(String name) {
    return this.name.equals(name);
  }

  public boolean isNot(String name) {
    return !is(name);
  }

  public boolean isNot(IdentityType type) {
    return !is(type);
  }

  @Override
  public String toString() {
    return name;
  }

  static {
    Provided.put(email.name, email);
    Provided.put(username.name, username);
    Provided.put(phoneNumber.name, phoneNumber);
  }
}
