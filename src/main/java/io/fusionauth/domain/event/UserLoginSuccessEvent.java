/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.User;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import static io.fusionauth.domain.connector.BaseConnectorConfiguration.FUSIONAUTH_CONNECTOR_ID;

/**
 * Models the User Login Success Event.
 *
 * @author Daniel DeGroff
 */
public class UserLoginSuccessEvent extends BaseUserEvent implements Buildable<UserLoginSuccessEvent> {
  public UUID applicationId;

  public String authenticationType;

  // Generally this will be equal to user.connectorId, except during a login where the action is set to migrate.
  public UUID connectorId;

  public UUID identityProviderId;

  public String identityProviderName;

  @Deprecated
  public String ipAddress;

  @JacksonConstructor
  public UserLoginSuccessEvent() {
  }

  public UserLoginSuccessEvent(EventInfo info, UUID applicationId, String authenticationType, BaseIdentityProvider<?> identityProvider,
                               User user) {
    super(info, user);
    this.applicationId = applicationId;
    this.authenticationType = authenticationType;
    // Identity provider login always takes the FusionAuth connector Id
    this.connectorId = FUSIONAUTH_CONNECTOR_ID;
    this.identityProviderId = identityProvider.id;
    this.identityProviderName = identityProvider.name;

    // Maintain the old JSON format
    if (info != null && info.ipAddress != null) {
      ipAddress = info.ipAddress;
    }
  }

  public UserLoginSuccessEvent(EventInfo info, UUID applicationId, UUID connectorId, String authenticationType, User user) {
    super(info, user);
    this.applicationId = applicationId;
    this.authenticationType = authenticationType;
    this.connectorId = connectorId;

    // Maintain the old JSON format
    if (info != null && info.ipAddress != null) {
      ipAddress = info.ipAddress;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    UserLoginSuccessEvent that = (UserLoginSuccessEvent) o;
    return Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(authenticationType, that.authenticationType) &&
           Objects.equals(connectorId, that.connectorId) &&
           Objects.equals(identityProviderId, that.identityProviderId) &&
           Objects.equals(identityProviderName, that.identityProviderName) &&
           Objects.equals(ipAddress, that.ipAddress);
  }

  @Override
  public EventType getType() {
    return EventType.UserLoginSuccess;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationId, authenticationType, connectorId, identityProviderId, identityProviderName, ipAddress);
  }
}
