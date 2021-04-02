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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.ToString;

/**
 * domain POJO to represent AuthenticationKey
 *
 * @author sanjay
 */
public class AuthenticationAPIKey implements Buildable<AuthenticationAPIKey> {
  public String id;

  public AuthenticationMetaData metaData;

  public AuthenticationPermissions permissions;

  public UUID tenantId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AuthenticationAPIKey)) {
      return false;
    }
    AuthenticationAPIKey that = (AuthenticationAPIKey) o;
    return Objects.equals(id, that.id) &&
           Objects.equals(metaData, that.metaData) &&
           Objects.equals(permissions, that.permissions) &&
           Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, metaData, permissions, tenantId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class AuthenticationMetaData {
    public boolean copyAllowed;

    public final Map<String, String> attributes = new HashMap<>();

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      AuthenticationAPIKey.AuthenticationMetaData that = (AuthenticationAPIKey.AuthenticationMetaData) o;
      return Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
      return attributes.hashCode();
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class AuthenticationPermissions {
    public final Map<String, Set<String>> endpoints = new HashMap<>();

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      AuthenticationAPIKey.AuthenticationPermissions that = (AuthenticationAPIKey.AuthenticationPermissions) o;
      return Objects.equals(endpoints, that.endpoints);
    }

    @Override
    public int hashCode() {
      return endpoints.hashCode();
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
