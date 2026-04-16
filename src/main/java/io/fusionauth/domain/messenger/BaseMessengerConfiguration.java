/*
 * Copyright (c) 2020-2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.messenger;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.inversoft.json.ToString;
import io.fusionauth.domain.message.MessageType;

/**
 * @author Brett Guy
 */
// Do not require a setter for 'type', it is defined by the concrete class and is not mutable
@JsonIgnoreProperties(value = "type", allowGetters = true, allowSetters = false)
public abstract class BaseMessengerConfiguration  {
  public final Map<String, Object> data = new HashMap<>();

  public boolean debug;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  @JsonMerge(OptBoolean.FALSE)
  public Set<MessageType> messageTypes = new LinkedHashSet<>(Collections.singletonList(MessageType.SMS));

  public String name;

  /**
   * @deprecated since 1.65.0, see {@link BaseMessengerConfiguration#messageTypes}
   */
  @Deprecated
  public String transport = MessengerTransport.SMS;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseMessengerConfiguration)) {
      return false;
    }
    BaseMessengerConfiguration that = (BaseMessengerConfiguration) o;
    return debug == that.debug &&
           Objects.equals(data, that.data) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(messageTypes, that.messageTypes) &&
           Objects.equals(name, that.name) &&
           Objects.equals(transport, that.transport);
  }

  public abstract MessengerType getType();

  @Override
  public int hashCode() {
    return Objects.hash(data, debug, id, insertInstant, lastUpdateInstant, messageTypes, name, transport);
  }

  public void normalize() {
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
