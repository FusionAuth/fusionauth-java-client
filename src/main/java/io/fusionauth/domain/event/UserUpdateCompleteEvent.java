/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Models the User Update Event once it is completed. This cannot be transactional.
 *
 * @author Daniel DeGroff
 */
public class UserUpdateCompleteEvent extends BaseEvent implements Buildable<UserUpdateCompleteEvent>, NonTransactionalEvent {
  public User original;

  public User user;

  @JacksonConstructor
  public UserUpdateCompleteEvent() {
  }

  public UserUpdateCompleteEvent(EventInfo info, User original, User user) {
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
    UserUpdateCompleteEvent that = (UserUpdateCompleteEvent) o;
    return super.equals(o) &&
           Objects.equals(user, that.user) && Objects.equals(original, that.original);
  }

  @Override
  public EventType getType() {
    return EventType.UserUpdateComplete;
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
