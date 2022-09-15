/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Tyler Scott
 */
public class Group implements Buildable<Group>, Tenantable {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  public Map<UUID, List<ApplicationRole>> roles = new HashMap<>();

  public UUID tenantId;

  @JacksonConstructor
  public Group() {
  }

  public Group(String name) {
    this.name = name;
  }

  public Group(Group other) {
    this.id = other.id;
    this.data.putAll(other.data);
    this.insertInstant = other.insertInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.name = other.name;
    other.roles.forEach((k, v) -> roles.put(k, other.roles.get(k).stream().map(ApplicationRole::new).collect(Collectors.toList())));
    this.tenantId = other.tenantId;
  }

  public Group(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Group)) {
      return false;
    }
    Group group = (Group) o;
    return Objects.equals(data, group.data) &&
           Objects.equals(id, group.id) &&
           Objects.equals(name, group.name) &&
           Objects.equals(roles, group.roles) &&
           Objects.equals(tenantId, group.tenantId) &&
           Objects.equals(insertInstant, group.insertInstant) &&
           Objects.equals(lastUpdateInstant, group.lastUpdateInstant);
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, id, name, roles, tenantId, insertInstant, lastUpdateInstant);
  }

  public Group secure() {
    return this;
  }

  public Group sort() {
    for (List<ApplicationRole> appRoles : roles.values()) {
      appRoles.sort(Comparator.comparing(r -> r.name));
    }
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
