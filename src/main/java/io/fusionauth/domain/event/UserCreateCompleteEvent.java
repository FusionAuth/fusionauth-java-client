/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models the User Created Event.
 * <p>
 * This is different than the user.create event in that it will be sent after the user has been created. This event cannot be made transactional.
 *
 * @author Daniel DeGroff
 */
public class UserCreateCompleteEvent extends BaseEvent implements Buildable<UserCreateCompleteEvent>, NonTransactionalEvent {
  public User user;

  @JacksonConstructor
  public UserCreateCompleteEvent() {
  }

  public UserCreateCompleteEvent(EventInfo info, User user) {
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
    UserCreateCompleteEvent that = (UserCreateCompleteEvent) o;
    return super.equals(o) &&
           Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserCreateComplete;
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
