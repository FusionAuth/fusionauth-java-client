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
package io.fusionauth.domain.event;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;
import io.fusionauth.domain.provider.BaseIdentityProvider;

/**
 * Models the User Login event that is suspicious.
 *
 * @author Daniel DeGroff
 */
public class UserLoginSuspiciousEvent extends UserLoginSuccessEvent {
  @JacksonConstructor
  public UserLoginSuspiciousEvent() {
  }

  public UserLoginSuspiciousEvent(EventInfo info, UUID applicationId, String authenticationType, BaseIdentityProvider<?> identityProvider,
                                  User user) {
    super(info, applicationId, authenticationType, identityProvider, user);
  }

  public UserLoginSuspiciousEvent(EventInfo info, UUID applicationId, UUID connectorId, String authenticationType, User user) {
    super(info, applicationId, connectorId, authenticationType, user);
  }

  @Override
  public EventType getType() {
    return EventType.UserLoginSuspicious;
  }
}
