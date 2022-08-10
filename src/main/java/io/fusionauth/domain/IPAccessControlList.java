/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.ToString;



/**
 * @author Brett Guy
 */
public class IPAccessControlList implements Buildable<IPAccessControlList> {
  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public final Map<String, Object> data = new LinkedHashMap<>();

  
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