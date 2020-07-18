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
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;

/**
 * An audit log.
 *
 * @author Brian Pontarelli
 */
public class AuditLog implements Buildable<AuditLog>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public Long id;

  public ZonedDateTime insertInstant;

  public String insertUser;

  public String message;

  @InternalJSONColumn
  public Object newValue;

  @InternalJSONColumn
  public Object oldValue;

  @InternalJSONColumn
  public String reason;

  public AuditLog() {
  }

  public AuditLog(String insertUser, String message) {
    this.insertUser = insertUser;
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AuditLog)) {
      return false;
    }
    AuditLog auditLog = (AuditLog) o;
    return Objects.equals(data, auditLog.data) &&
           Objects.equals(id, auditLog.id) &&
           Objects.equals(insertInstant, auditLog.insertInstant) &&
           Objects.equals(insertUser, auditLog.insertUser) &&
           Objects.equals(message, auditLog.message) &&
           Objects.equals(newValue, auditLog.newValue) &&
           Objects.equals(oldValue, auditLog.oldValue) &&
           Objects.equals(reason, auditLog.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, id, insertInstant, insertUser, message, newValue, oldValue, reason);
  }

  public void normalize() {
    insertUser = Normalizer.trim(insertUser);
    message = Normalizer.trim(message);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
