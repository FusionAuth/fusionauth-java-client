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
 * Models the User Reactivate Event.
 *
 * @author Brian Pontarelli
 */
public class UserReactivateEvent extends BaseEvent implements Buildable<UserReactivateEvent> {
  public User user;

  @JacksonConstructor
  public UserReactivateEvent() {
  }

  public UserReactivateEvent(EventInfo info, User user) {
    super(info);
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
    UserReactivateEvent that = (UserReactivateEvent) o;
    return super.equals(o) &&
           Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserReactivate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), user);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
