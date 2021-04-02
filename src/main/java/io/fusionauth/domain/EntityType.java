/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * Models an entity type that has a specific set of permissions. These are global objects and can be used across tenants.
 *
 * @author Brian Pontarelli
 */
public class EntityType implements Buildable<EntityType>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  @InternalJSONColumn
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  public ZonedDateTime lastUpdateInstant;

  public String name;

  public List<EntityTypePermission> permissions = new ArrayList<>();

  public EntityTypePermission getPermission(String name) {
    for (EntityTypePermission permission : permissions) {
      if (permission.name.equals(name)) {
        return permission;
      }
    }

    return null;
  }

  @JacksonConstructor
  public EntityType() {
  }

  public EntityType(String name) {
    this.name = name;
  }

  public EntityType(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EntityType)) {
      return false;
    }
    EntityType group = (EntityType) o;
    return Objects.equals(data, group.data) &&
           Objects.equals(id, group.id) &&
           Objects.equals(insertInstant, group.insertInstant) &&
           Objects.equals(lastUpdateInstant, group.lastUpdateInstant) &&
           Objects.equals(name, group.name) &&
           Objects.equals(permissions, group.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, id, insertInstant, lastUpdateInstant, name, permissions);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  /**
   * JWT Configuration for entities.
   */
  public static class JWTConfiguration extends Enableable implements Buildable<JWTConfiguration> {
    /**
     * The signing key used to sign the access token
     */
    @ExcludeFromDatabaseDataColumn
    public UUID accessTokenKeyId;

    /**
     * The length of time in seconds this JWT is valid from the time it was issued. This should be a non-zero value.
     */
    public int timeToLiveInSeconds;
  }
}
