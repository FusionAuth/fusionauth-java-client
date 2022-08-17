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
 * Models the Group Create Event (and can be converted to JSON).
 *
 * @author Daniel DeGroff
 */
public class GroupUpdateEvent extends BaseEvent implements Buildable<GroupUpdateEvent> {
  public Group group;

  public Group original;

  @JacksonConstructor
  public GroupUpdateEvent() {
  }

  public GroupUpdateEvent(EventInfo info, Group original, Group group) {
    super(info);
    this.group = group;
    this.original = original;
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
    GroupUpdateEvent that = (GroupUpdateEvent) o;
    return Objects.equals(group, that.group) && Objects.equals(original, that.original);
  }

  @Override
  public EventType getType() {
    return EventType.GroupUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), group, original);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
