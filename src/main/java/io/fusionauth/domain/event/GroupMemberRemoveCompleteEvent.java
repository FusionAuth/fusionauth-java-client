/*
 * Copyright (c) 2022-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.Group;
import io.fusionauth.domain.GroupMember;

/**
 * Models the Group Member Remove Complete Event.
 *
 * @author Daniel DeGroff
 */
public class GroupMemberRemoveCompleteEvent extends BaseGroupEvent implements Buildable<GroupMemberRemoveCompleteEvent>, NonTransactionalEvent {
  public List<GroupMember> members = new ArrayList<>();

  @JacksonConstructor
  public GroupMemberRemoveCompleteEvent() {
  }

  public GroupMemberRemoveCompleteEvent(EventInfo info, Group group, List<GroupMember> members) {
    super(info, group);
    if (members != null) {
      this.members.addAll(members);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    GroupMemberRemoveCompleteEvent that = (GroupMemberRemoveCompleteEvent) o;
    return Objects.equals(members, that.members);
  }

  @Override
  public EventType getType() {
    return EventType.GroupMemberRemoveComplete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), members);
  }
}
