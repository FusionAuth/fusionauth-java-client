/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.List;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.Group;
import io.fusionauth.domain.GroupMember;

/**
 * Models the Group Create Event (and can be converted to JSON).
 *
 * @author Daniel DeGroff
 */
public class GroupMemberRemoveEvent extends BaseEvent implements Buildable<GroupMemberRemoveEvent> {
  public Group group;

  public List<GroupMember> members;

  @JacksonConstructor
  public GroupMemberRemoveEvent() {
  }

  public GroupMemberRemoveEvent(EventInfo info, Group group, List<GroupMember> members) {
    super(info);
    this.group = group;
    this.members = members;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GroupMemberRemoveEvent that = (GroupMemberRemoveEvent) o;
    return Objects.equals(group, that.group) && Objects.equals(members, that.members);
  }

  @Override
  public EventType getType() {
    return EventType.GroupMemberRemove;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), group, members);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
