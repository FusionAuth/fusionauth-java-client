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
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Brett Guy
 */
public class IPAccessControlList implements Buildable<IPAccessControlList>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public List<IPAccessControlEntry> entries = new ArrayList<>();

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
    IPAccessControlList that = (IPAccessControlList) o;
    return Objects.equals(data, that.data) &&
           Objects.equals(entries, that.entries) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, entries, id, insertInstant, lastUpdateInstant, name);
  }

  public IPAccessControlList normalize() {
    // The "base" entry of "*" does not have an end value
    boolean hasDefault = false;
    Iterator<IPAccessControlEntry> iterator = entries.iterator();
    while (iterator.hasNext()) {
      IPAccessControlEntry entry = iterator.next();
      if ("*".equals(entry.startIPAddress)) {
        if (hasDefault) {
          iterator.remove();
          continue;
        }

        entry.endIPAddress = null;
        hasDefault = true;
      }
    }

    // Sort the entries for easier readability, and keep the default entry at the top.
    entries.sort(Comparator.<IPAccessControlEntry, String>comparing(e -> e.startIPAddress, Comparator.nullsLast(Comparator.naturalOrder()))
                     .thenComparing(e -> e.endIPAddress, Comparator.nullsLast(Comparator.naturalOrder())));
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}