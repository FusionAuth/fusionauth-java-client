/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
 * Models the User Event (and can be converted to JSON) that is used for all user modifications (create, update,
 * delete).
 *
 * @author Brian Pontarelli
 */
public class UserDeleteEvent extends BaseEvent implements Buildable<UserDeleteEvent> {
  public User user;

  @JacksonConstructor
  public UserDeleteEvent() {
  }

  public UserDeleteEvent(EventInfo info, User user) {
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
    UserDeleteEvent that = (UserDeleteEvent) o;
    return super.equals(o) &&
           Objects.equals(user, that.user);
  }

  @Override
  public EventType getType() {
    return EventType.UserDelete;
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
