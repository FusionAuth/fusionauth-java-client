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
import io.fusionauth.domain.TwoFactorMethod;

/**
 * Model a user event when a two-factor method has been added.
 *
 * @author Daniel DeGroff
 */
public class UserTwoFactorMethodRemoveEvent extends BaseUserEvent implements Buildable<UserTwoFactorMethodRemoveEvent>, NonTransactionalEvent {
  public TwoFactorMethod method;

  @JacksonConstructor
  public UserTwoFactorMethodRemoveEvent() {
  }

  public UserTwoFactorMethodRemoveEvent(EventInfo info, TwoFactorMethod method, User user) {
    super(info, user);
    this.method = method;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserTwoFactorMethodRemoveEvent that = (UserTwoFactorMethodRemoveEvent) o;
    return Objects.equals(method, that.method);
  }

  @Override
  public EventType getType() {
    return EventType.UserTwoFactorMethodRemove;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), method);
  }
}
