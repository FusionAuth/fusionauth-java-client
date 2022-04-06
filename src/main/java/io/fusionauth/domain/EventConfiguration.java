/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.event.EventType;

/**
 * @author Brian Pontarelli
 */
public class EventConfiguration implements Buildable<EventConfiguration> {
  public Map<EventType, EventConfigurationData> events = new HashMap<>();

  @JacksonConstructor
  public EventConfiguration() {
  }

  public EventConfiguration(EventConfiguration other) {
    for (Map.Entry<EventType, EventConfigurationData> entry : other.events.entrySet()) {
      events.put(entry.getKey(), new EventConfigurationData(entry.getValue()));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventConfiguration that = (EventConfiguration) o;
    return Objects.equals(events, that.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(events);
  }

  public EventConfiguration normalize() {
    events.forEach((key, value) -> value.transactionType = key.isTransactionalEvent() ? value.transactionType : TransactionType.None);
    events.keySet().removeIf(EventType::isInstanceEvent);
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class EventConfigurationData extends Enableable {
    public TransactionType transactionType = TransactionType.None;

    @JacksonConstructor
    public EventConfigurationData() {
    }

    public EventConfigurationData(boolean enabled, TransactionType transactionType) {
      this.enabled = enabled;
      this.transactionType = transactionType;
    }

    public EventConfigurationData(EventConfigurationData other) {
      this.enabled = other.enabled;
      this.transactionType = other.transactionType;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      EventConfigurationData that = (EventConfigurationData) o;
      return super.equals(o) &&
             Objects.equals(transactionType, that.transactionType);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), transactionType);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}