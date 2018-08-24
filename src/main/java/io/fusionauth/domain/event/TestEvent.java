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
package io.fusionauth.domain.event;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class TestEvent extends BaseEvent implements Buildable<TestEvent> {
  public String message;

  @JacksonConstructor
  public TestEvent() {
    message = "Example FusionAuth Event.";
  }

  public TestEvent(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestEvent that = (TestEvent) o;
    return super.equals(o) &&
        Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), message);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  @Override
  public EventType type() {
    return EventType.Test;
  }
}
