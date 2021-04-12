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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import io.fusionauth.domain.internal._InternalJSONColumn;

/**
 * A grant for an entity to a user or another entity.
 *
 * @author Brian Pontarelli
 */
public class EntityGrant implements Buildable<EntityGrant>, _InternalJSONColumn {
  public Map<String, Object> data = new LinkedHashMap<>();

  public Entity entity;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public Set<String> permissions = new TreeSet<>();

  public UUID recipientEntityId;

  public UUID userId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EntityGrant)) {
      return false;
    }
    EntityGrant that = (EntityGrant) o;
    return Objects.equals(data, that.data) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(permissions, that.permissions) &&
           Objects.equals(recipientEntityId, that.recipientEntityId) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, id, insertInstant, lastUpdateInstant, permissions, recipientEntityId, userId);
  }
}
