/*
 * Copyright (c) 2021-2024, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.User;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * Models an event where a user's email is updated outside of a forgot / change password workflow.
 *
 * @author Daniel DeGroff
 */
public class UserEmailUpdateEvent extends BaseUserEvent implements Buildable<UserEmailUpdateEvent>, NonTransactionalEvent {
  public String previousEmail;

  public UserEmailUpdateEvent(EventInfo info, String previousEmail, User user) {
    super(info, user);
    this.previousEmail = previousEmail;
  }

  @JacksonConstructor
  public UserEmailUpdateEvent() {
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserEmailUpdateEvent that = (UserEmailUpdateEvent) o;
    return Objects.equals(previousEmail, that.previousEmail);
  }

  @Override
  public EventType getType() {
    return EventType.UserEmailUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), previousEmail);
  }
}
