/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.User;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

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
    this.users = users.stream().map(u -> new User(u).secure().sort())
                      .collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserBulkCreateEvent that = (UserBulkCreateEvent) o;
    return Objects.equals(users, that.users);
  }

  @Override
  public EventType getType() {
    return EventType.UserBulkCreate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), users);
  }
}
