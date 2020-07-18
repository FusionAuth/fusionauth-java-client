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
 * A User's membership into a Group
 *
 * @author Daniel DeGroff
 */
public class GroupMember implements Buildable<GroupMember> {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public UUID groupId;

  public UUID id;

  public ZonedDateTime insertInstant;

  public UUID userId;

  @JacksonConstructor
  public GroupMember() {
  }

  public GroupMember(GroupMember member) {
    this.data.putAll(member.data);
    this.groupId = member.groupId;
    this.id = member.id;
    this.insertInstant = member.insertInstant;
    this.userId = member.userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMember that = (GroupMember) o;
    return Objects.equals(data, that.data) &&
           Objects.equals(groupId, that.groupId) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, groupId, id, insertInstant, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
