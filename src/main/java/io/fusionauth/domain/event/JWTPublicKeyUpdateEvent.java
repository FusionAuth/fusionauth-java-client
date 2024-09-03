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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * Models the JWT public key Refresh Token Revoke Event. This event might be for a single
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
    if (!super.equals(o)) {
      return false;
    }
    JWTPublicKeyUpdateEvent that = (JWTPublicKeyUpdateEvent) o;
    return Objects.equals(applicationIds, that.applicationIds);
  }

  @Override
  public EventType getType() {
    return EventType.JWTPublicKeyUpdate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationIds);
  }
}
