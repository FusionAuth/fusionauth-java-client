/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * Models the JWT public key Refresh Token Revoke Event (and can be converted to JSON). This event might be for a single
 * token, a user or an entire application.
 *
 * @author Brian Pontarelli
 */
public class JWTPublicKeyUpdateEvent extends BaseEvent implements Buildable<JWTPublicKeyUpdateEvent> {
  public final Set<UUID> applicationIds;

  @JacksonConstructor
  public JWTPublicKeyUpdateEvent() {
    applicationIds = new HashSet<>(0);
  }

  public JWTPublicKeyUpdateEvent(EventInfo info, UUID applicationId) {
    super(info);
    this.applicationIds = new HashSet<>();
    this.applicationIds.add(applicationId);
  }

  public JWTPublicKeyUpdateEvent(EventInfo info, Set<UUID> applicationIds) {
    super(info);
    this.applicationIds = new HashSet<>(applicationIds);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JWTPublicKeyUpdateEvent that = (JWTPublicKeyUpdateEvent) o;
    return super.equals(o) &&
           Objects.equals(applicationIds, that.applicationIds);
  }

  @Override
  public EventType getType() {
    return EventType.JWTPublicKeyUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationIds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
