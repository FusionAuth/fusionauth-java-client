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
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * Event event to indicate kickstart has been successfully completed.
 *
 * @author Daniel DeGroff
 */
public class KickstartSuccessEvent extends BaseEvent implements Buildable<KickstartSuccessEvent>, InstanceEvent, NonTransactionalEvent {
  public UUID instanceId;

  @JacksonConstructor
  public KickstartSuccessEvent() {
  }

  public KickstartSuccessEvent(UUID instanceId) {
    this.instanceId = instanceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    KickstartSuccessEvent that = (KickstartSuccessEvent) o;
    return Objects.equals(instanceId, that.instanceId);
  }

  @Override
  public EventType getType() {
    return EventType.KickstartSuccess;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), instanceId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
