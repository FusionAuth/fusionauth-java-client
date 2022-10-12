/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.Group;

/**
 * Models the Group Delete Event.
 *
 * @author Daniel DeGroff
 */
public class GroupDeleteEvent extends BaseEvent implements Buildable<GroupDeleteEvent> {
  public Group group;

  @JacksonConstructor
  public GroupDeleteEvent() {
  }

  public GroupDeleteEvent(EventInfo info, Group group) {
    super(info);
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupDeleteEvent that = (GroupDeleteEvent) o;
    return super.equals(o) &&
           Objects.equals(group, that.group);
  }

  @Override
  public EventType getType() {
    return EventType.GroupDelete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), group);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
