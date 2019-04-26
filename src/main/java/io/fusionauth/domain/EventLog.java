/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.inversoft.json.ToString;

/**
 * Event log used internally by FusionAuth to help developers debug hooks, Webhooks, email templates, etc.
 *
 * @author Brian Pontarelli
 */
public class EventLog implements Buildable<EventLog> {
  public Long id;

  public ZonedDateTime insertInstant;

  public String message;

  public EventLogType type;

  public EventLog(String message) {
    this.message = message;
  }

  public EventLog(EventLogType type, String message) {
    this.message = message;
    this.type = type;
  }

  public EventLog(EventLogType type, String message, Throwable t) {
    this.message = message;
    this.type = type;

    // Add the exception to the message
    StringWriter writer = new StringWriter();
    PrintWriter pw = new PrintWriter(writer);
    t.printStackTrace(pw);
    this.message += "\nException:\n" + writer.toString();
  }

  public EventLog() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EventLog)) {
      return false;
    }
    EventLog eventLog = (EventLog) o;
    return Objects.equals(insertInstant, eventLog.insertInstant) &&
        Objects.equals(message, eventLog.message) &&
        Objects.equals(type, eventLog.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(insertInstant, message, type);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
