/*
 * Copyright (c) 2021-2024, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.AuthenticationThreats;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;
import io.fusionauth.domain.provider.BaseIdentityProvider;

/**
 * Models the User Login event that is suspicious.
 *
 * @author Daniel DeGroff
 */
public class UserLoginSuspiciousEvent extends UserLoginSuccessEvent {
  public Set<AuthenticationThreats> threatsDetected = new HashSet<>();

  @JacksonConstructor
  public UserLoginSuspiciousEvent() {
  }

  public UserLoginSuspiciousEvent(EventInfo info, UUID applicationId, String authenticationType, BaseIdentityProvider<?> identityProvider,
                                  User user, Set<AuthenticationThreats> threatsDetected) {
    super(info, applicationId, authenticationType, identityProvider, user);
    this.threatsDetected = threatsDetected;

  }

  public UserLoginSuspiciousEvent(EventInfo info, UUID applicationId, UUID connectorId, String authenticationType, User user,
                                  Set<AuthenticationThreats> threatsDetected) {
    super(info, applicationId, connectorId, authenticationType, user);
    this.threatsDetected = threatsDetected;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserLoginSuspiciousEvent that = (UserLoginSuspiciousEvent) o;
    return Objects.equals(threatsDetected, that.threatsDetected);
  }

  @Override
  public EventType getType() {
    return EventType.UserLoginSuspicious;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), threatsDetected);
  }
}
