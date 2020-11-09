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
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * A role given to a user for a specific application.
 *
 * @author Seth Musselman
 */
public class ApplicationRole implements Comparable<ApplicationRole>, Buildable<ApplicationRole> {
  @JsonIgnore
  public UUID applicationId;

  public String description;

  public UUID id;

  public ZonedDateTime insertInstant;

  public boolean isDefault;

  public boolean isSuperRole;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  @JacksonConstructor
  public ApplicationRole() {
  }

  public ApplicationRole(ApplicationRole other) {
    this.applicationId = other.applicationId;
    this.description = other.description;
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.isDefault = other.isDefault;
    this.isSuperRole = other.isSuperRole;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.name = other.name;
  }

  public ApplicationRole(String roleName) {
    this(null, null, roleName, false, false, null);
  }

  public ApplicationRole(UUID id, UUID applicationId, String roleName, boolean isDefault, boolean isSuperRole,
                         String description) {
    this.id = id;
    this.applicationId = applicationId;
    this.name = roleName;
    this.isDefault = isDefault;
    this.isSuperRole = isSuperRole;
    this.description = description;
  }

  @Override
  public int compareTo(ApplicationRole o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ApplicationRole)) {
      return false;
    }
    ApplicationRole that = (ApplicationRole) o;
    return isDefault == that.isDefault &&
           isSuperRole == that.isSuperRole &&
           Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(id, that.id) &&
           Objects.equals(description, that.description) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(name, that.name);
  }

  @JsonIgnore
  public String getDisplay() {
    return description != null ? description + " (" + name + ")" : name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, description, id, insertInstant, isDefault, isSuperRole, lastUpdateInstant, name);
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
