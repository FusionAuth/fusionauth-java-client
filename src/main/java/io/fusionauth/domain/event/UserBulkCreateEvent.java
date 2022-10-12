/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.List;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models the User Bulk Create Event.
 *
 * @author Brian Pontarelli
 */
public class UserBulkCreateEvent extends BaseEvent implements Buildable<UserBulkCreateEvent> {
  public List<User> users;

  @JacksonConstructor
  public UserBulkCreateEvent() {
  }

  public UserBulkCreateEvent(EventInfo info, List<User> users) {
    super(info);
    this.users = users;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBulkCreateEvent that = (UserBulkCreateEvent) o;
    return super.equals(o) &&
           Objects.equals(users, that.users);
  }

  @Override
  public EventType getType() {
    return EventType.UserBulkCreate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), users);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
