/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;

/**
 * Models a single family member.
 *
 * @author Brian Pontarelli
 */
public class FamilyMember implements Buildable<FamilyMember>, _InternalJSONColumn {
  public Map<String, Object> data = new HashMap<>();

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public boolean owner;

  public FamilyRole role;

  public UUID userId;

  @JacksonConstructor
  public FamilyMember() {
  }

  public FamilyMember(FamilyMember familyMember) {
    this.data.putAll(familyMember.data);
    this.insertInstant = familyMember.insertInstant;
    this.lastUpdateInstant = familyMember.lastUpdateInstant;
    this.owner = familyMember.owner;
    this.role = familyMember.role;
    this.userId = familyMember.userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FamilyMember)) {
      return false;
    }
    FamilyMember that = (FamilyMember) o;
    return Objects.equals(data, that.data) &&
        Objects.equals(insertInstant, that.insertInstant) &&
        Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
        owner == that.owner &&
        role == that.role &&
        Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, insertInstant, lastUpdateInstant, owner, role, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum FamilyRole {
    Child,

    Teen,

    Adult
  }
}
