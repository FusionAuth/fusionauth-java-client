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

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
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
    if (!super.equals(o)) {
      return false;
    }
    TestEvent that = (TestEvent) o;
    return Objects.equals(message, that.message);
  }

  @Override
  public EventType getType() {
    return EventType.Test;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), message);
  }
}
