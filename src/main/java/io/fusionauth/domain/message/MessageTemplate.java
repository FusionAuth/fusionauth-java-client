/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.message;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inversoft.json.ToString;


/**
 * Stores an message template used to distribute messages;
 *
 * @author Michael Sleevi
 */
@JsonIgnoreProperties(value = "type", allowGetters = true, allowSetters = false)
public abstract class MessageTemplate  {
  public Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageTemplate that = (MessageTemplate) o;
    return id.equals(that.id) &&
           insertInstant.equals(that.insertInstant) &&
           lastUpdateInstant.equals(that.lastUpdateInstant) &&
           name.equals(that.name) &&
           data.equals(that.data);
  }

  public abstract MessageType getType();

  @Override
  public int hashCode() {
    return Objects.hash(id, insertInstant, lastUpdateInstant, name, data);
  }

  public abstract void normalize();

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
