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

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * domain POJO to represent AuthenticationKey
 *
 * @author sanjay
 */
public class APIKey implements Buildable<APIKey> {
  public UUID id;

  public ZonedDateTime insertInstant;

  public String key;

  public boolean keyManager;

  public ZonedDateTime lastUpdateInstant;

  public APIKeyMetaData metaData;

  public APIKeyPermissions permissions;

  public UUID tenantId;

  public APIKey(String key) {
    this.key = key;
  }

  @JacksonConstructor
  public APIKey() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    APIKey apiKey = (APIKey) o;
    return keyManager == apiKey.keyManager && Objects.equals(id, apiKey.id) && Objects.equals(insertInstant, apiKey.insertInstant) && Objects.equals(key, apiKey.key) && Objects.equals(lastUpdateInstant, apiKey.lastUpdateInstant) && Objects.equals(metaData, apiKey.metaData) && Objects.equals(permissions, apiKey.permissions) && Objects.equals(tenantId, apiKey.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, insertInstant, key, keyManager, lastUpdateInstant, metaData, permissions, tenantId);
  }

  public void normalize() {
    if (permissions != null) {
      permissions.endpoints.remove("/api/api-key");
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class APIKeyMetaData {
    public final Map<String, String> attributes = new HashMap<>();

    public APIKeyMetaData(Map<String, String> attributes) {
      this.attributes.putAll(attributes);
    }

    public APIKeyMetaData() {
    }

    public APIKeyMetaData(String key, String value) {
      attributes.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      APIKeyMetaData that = (APIKeyMetaData) o;
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

  public static class APIKeyPermissions {
    public final Map<String, Set<String>> endpoints = new HashMap<>();

    public APIKeyPermissions() {
    }

    public APIKeyPermissions(Map<String, Set<String>> endpoints) {
      endpoints.entrySet().stream().forEach(e -> this.endpoints.put(e.getKey(), new HashSet<>(e.getValue())));
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      APIKeyPermissions that = (APIKeyPermissions) o;
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
