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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * Models a specific entity type permission. This permission can be granted to users or other entities.
 *
 * @author Brian Pontarelli
 */
public class EntityTypePermission implements Buildable<EntityTypePermission> {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public String description;

  @JsonIgnore
  public UUID entityTypeId;

  public UUID id;

  public ZonedDateTime insertInstant;

  public boolean isDefault;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  @JacksonConstructor
  public EntityTypePermission() {
  }

  public EntityTypePermission(String name) {
    this.name = name;
  }

  public EntityTypePermission(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EntityTypePermission)) {
      return false;
    }
    EntityTypePermission group = (EntityTypePermission) o;
    return Objects.equals(data, group.data) &&
           Objects.equals(description, group.description) &&
           Objects.equals(entityTypeId, group.entityTypeId) &&
           Objects.equals(id, group.id) &&
           Objects.equals(isDefault, group.isDefault) &&
           Objects.equals(name, group.name) &&
           Objects.equals(insertInstant, group.insertInstant) &&
           Objects.equals(lastUpdateInstant, group.lastUpdateInstant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, description, entityTypeId, id, isDefault, name, insertInstant, lastUpdateInstant);
  }

  public void normalize() {
    description = trim(description);
    name = trim(name);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
