/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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
import java.util.UUID;

import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * Base class for all {@link User}-related events.
 *
 * @author Spencer Witt
 */
public abstract class BaseUserEvent extends BaseEvent implements ObjectIdentifiable {
  public User user;

  public BaseUserEvent() {
  }

  public BaseUserEvent(EventInfo info, User user) {
    super(info);
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    BaseUserEvent that = (BaseUserEvent) o;
    return Objects.equals(user, that.user);
  }

  @Override
  public UUID getLinkedObjectId() {
    return user.id;
  }

  @Override
  public void setLinkedObjectId(UUID linkedObjectId) {
    // needs a setter for the deserializer.
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), user);
  }
}
