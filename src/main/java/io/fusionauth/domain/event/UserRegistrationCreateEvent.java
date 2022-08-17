/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;

/**
 * Models the User Create Registration Event.
 *
 * @author Daniel DeGroff
 */
public class UserRegistrationCreateEvent extends BaseEvent implements Buildable<UserRegistrationCreateEvent> {
  public UUID applicationId;

  public UserRegistration registration;

  public User user;

  @JacksonConstructor
  public UserRegistrationCreateEvent() {
  }

  public UserRegistrationCreateEvent(EventInfo info, UUID applicationId, UserRegistration registration, User user) {
    super(info);
    this.applicationId = applicationId;
    this.registration = registration;
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
    UserRegistrationCreateEvent that = (UserRegistrationCreateEvent) o;
    return super.equals(o) &&
           Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(registration, that.registration) &&
           Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserRegistrationCreate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, registration, user);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
