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
 * Models the User Update Registration Event.
 *
 * @author Daniel DeGroff
 */
public class UserRegistrationUpdateEvent extends BaseEvent implements Buildable<UserRegistrationUpdateEvent> {
  public UUID applicationId;

  public UserRegistration original;

  public UserRegistration registration;

  public User user;

  @JacksonConstructor
  public UserRegistrationUpdateEvent() {
  }

  public UserRegistrationUpdateEvent(EventInfo info, UUID applicationId, UserRegistration original, UserRegistration registration, User user) {
    super(info);
    this.applicationId = applicationId;
    this.original = original;
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
    UserRegistrationUpdateEvent that = (UserRegistrationUpdateEvent) o;
    return super.equals(o) &&
           Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(original, that.original) &&
           Objects.equals(registration, that.registration) &&
           Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserRegistrationUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, original, registration, user);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
