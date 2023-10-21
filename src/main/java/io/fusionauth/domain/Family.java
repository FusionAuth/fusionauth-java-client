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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * Models a family grouping of users.
 *
 * @author Brian Pontarelli
 */
public class Family implements Buildable<Family> {
  public final List<FamilyMember> members = new ArrayList<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  @JacksonConstructor
  public Family() {
  }

  public Family(UUID id) {
    this.id = id;
  }

  public Family(UUID id, List<FamilyMember> members) {
    this.id = id;
    this.members.addAll(members);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Family)) {
      return false;
    }
    Family family = (Family) o;
    return Objects.equals(id, family.id) &&
           Objects.equals(insertInstant, family.insertInstant) &&
           Objects.equals(lastUpdateInstant, family.lastUpdateInstant) &&
           Objects.equals(members, family.members);
  }

  public FamilyMember getMember(UUID userId) {
    return members.stream()
                  .filter(fm -> fm.userId.equals(userId))
                  .findFirst()
                  .orElse(null);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, insertInstant, lastUpdateInstant, members);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
