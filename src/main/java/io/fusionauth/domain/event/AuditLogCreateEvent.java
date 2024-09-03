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
import io.fusionauth.domain.AuditLog;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * Event to indicate an audit log was created.
 *
 * @author Daniel DeGroff
 */
public class AuditLogCreateEvent extends BaseEvent implements Buildable<AuditLogCreateEvent>, InstanceEvent {
  public AuditLog auditLog;

  @JacksonConstructor
  public AuditLogCreateEvent() {
  }

  public AuditLogCreateEvent(EventInfo info, AuditLog auditLog) {
    super(info);
    this.auditLog = auditLog;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    AuditLogCreateEvent that = (AuditLogCreateEvent) o;
    return Objects.equals(auditLog, that.auditLog);
  }

  @Override
  public EventType getType() {
    return EventType.AuditLogCreate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), auditLog);
  }
}
