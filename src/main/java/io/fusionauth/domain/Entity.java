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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;


/**
 * Models an entity that a user can be granted permissions to. Or an entity that can be granted permissions to another entity.
 *
 * @author Brian Pontarelli
 */
public class Entity implements Buildable<Entity>, Tenantable {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public String clientId;

  public String clientSecret;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  public UUID parentId;

  public UUID tenantId;

  public EntityType type;

  @JacksonConstructor
  public Entity() {
  }

  public Entity(String name) {
    this.name = name;
  }

  public Entity(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Entity)) {
      return false;
    }
    Entity group = (Entity) o;
    return Objects.equals(clientSecret, group.clientSecret) &&
           Objects.equals(data, group.data) &&
           Objects.equals(id, group.id) &&
           Objects.equals(name, group.name) &&
           Objects.equals(parentId, group.parentId) &&
           Objects.equals(tenantId, group.tenantId) &&
           Objects.equals(type, group.type) &&
           Objects.equals(insertInstant, group.insertInstant) &&
           Objects.equals(lastUpdateInstant, group.lastUpdateInstant);
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientSecret, data, id, name, parentId, tenantId, type, insertInstant, lastUpdateInstant);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
