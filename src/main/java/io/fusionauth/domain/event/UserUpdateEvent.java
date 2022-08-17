/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models the User Update Event.
 *
 * @author Brian Pontarelli
 */
public class UserUpdateEvent extends BaseEvent implements Buildable<UserUpdateEvent> {
  public User original;

  public User user;

  @JacksonConstructor
  public UserUpdateEvent() {
  }

  public UserUpdateEvent(EventInfo info, User original, User user) {
    super(info);
    this.original = original;
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserUpdateEvent that = (UserUpdateEvent) o;
    return super.equals(o) &&
           Objects.equals(user, that.user) && Objects.equals(original, that.original);
  }

  @Override
  public EventType getType() {
    return EventType.UserUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), user, original);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
