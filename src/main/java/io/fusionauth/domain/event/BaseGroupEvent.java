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
import io.fusionauth.domain.Group;
import io.fusionauth.domain.GroupMember;

/**
 * Base class for all {@link Group} and {@link GroupMember} events.
 *
 * @author Spencer Witt
 */
public abstract class BaseGroupEvent extends BaseEvent implements ObjectIdentifiable {
  public Group group;

  public BaseGroupEvent() {
  }

  public BaseGroupEvent(EventInfo info, Group group) {
    super(info);
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    BaseGroupEvent that = (BaseGroupEvent) o;
    return Objects.equals(group, that.group);
  }

  @Override
  public UUID getLinkedObjectId() {
    return group.id;
  }

  @Override
  public void setLinkedObjectId(UUID linkedObjectId) {
    // needs a setter for the deserializer.
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), group);
  }
}
