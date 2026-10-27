/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * Models the CIMD Registration Failed Event.
 * <p>
 * This event is fired when a CIMD client registration fails. This event cannot be made transactional.
 */
public class CimdRegistrationFailedEvent extends BaseEvent implements Buildable<CimdRegistrationFailedEvent>, NonTransactionalEvent {
  public UUID cimdDomainId;

  public String clientId;

  public CimdRegistrationFailedReason reason;

  public String resource;

  @JacksonConstructor
  public CimdRegistrationFailedEvent() {
  }

  public CimdRegistrationFailedEvent(EventInfo info, UUID cimdDomainId, String clientId, CimdRegistrationFailedReason reason,
                                     String resource) {
    super(info);
    this.cimdDomainId = cimdDomainId;
    this.clientId = clientId;
    this.reason = reason;
    this.resource = resource;
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
    CimdRegistrationFailedEvent that = (CimdRegistrationFailedEvent) o;
    return Objects.equals(cimdDomainId, that.cimdDomainId) &&
           Objects.equals(clientId, that.clientId) &&
           Objects.equals(resource, that.resource) &&
           Objects.equals(reason, that.reason);
  }

  @Override
  public EventType getType() {
    return EventType.CimdRegistrationFailed;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), cimdDomainId, clientId, resource, reason);
  }
}
