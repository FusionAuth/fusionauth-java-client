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

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.EventInfo;

/**
 * Base class for all FusionAuth events.
 *
 * @author Brian Pontarelli
 */
public abstract class BaseEvent {
  public ZonedDateTime createInstant;

  public UUID id;

  public EventInfo info;

  public UUID tenantId;

  public BaseEvent() {
  }

  public BaseEvent(EventInfo info) {
    this.info = info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseEvent baseEvent = (BaseEvent) o;
    return Objects.equals(createInstant, baseEvent.createInstant) &&
           Objects.equals(id, baseEvent.id) &&
           Objects.equals(info, baseEvent.info) &&
           Objects.equals(tenantId, baseEvent.tenantId);
  }

  /**
   * @return The type of this event.
   */
  public abstract EventType getType();

  @Override
  public int hashCode() {
    return Objects.hash(createInstant, id, info, tenantId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
