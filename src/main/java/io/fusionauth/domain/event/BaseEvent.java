/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.inversoft.json.ToString;
import io.fusionauth.domain.EventInfo;

/**
 * Base-class for all FusionAuth events.
 *
 * @author Brian Pontarelli
 */
public abstract class BaseEvent {
  public List<UUID> applicationIds = new ArrayList<>();

  public ZonedDateTime createInstant;

  public UUID id;

  public EventInfo info;

  public UUID tenantId;

  public BaseEvent() {
  }

  public BaseEvent(EventInfo info) {
    this.info = info;
  }

  public BaseEvent(EventInfo info, Set<UUID> applicationIds) {
    this.info = info;
    this.applicationIds = applicationIds.stream().collect(Collectors.toList());
  }

  public Collection<UUID> applicationIds() {
    return applicationIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseEvent baseEvent = (BaseEvent) o;
    return Objects.equals(createInstant, baseEvent.createInstant) &&
           Objects.equals(id, baseEvent.id) &&
           Objects.equals(info, baseEvent.info) &&
           Objects.equals(tenantId, baseEvent.tenantId);
  }

  /**
   * @return The type of this event.
   */
  public abstract EventType getType();

  @Override
  public int hashCode() {
    return Objects.hash(createInstant, id, info, tenantId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
