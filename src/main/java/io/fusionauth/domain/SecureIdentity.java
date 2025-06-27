/*
 * Copyright (c) 2018-2025, FusionAuth, All Rights Reserved
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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.fusionauth.domain.connector.BaseConnectorConfiguration;

/**
 * @author Daniel DeGroff
 */
public class SecureIdentity {
  
  //                            fixes UAT.post_noPassword_has_email_and_phone.
  public final List<UserIdentity> identities = new ArrayList<>();

  public ZonedDateTime breachedPasswordLastCheckedInstant;

  public BreachedPasswordStatus breachedPasswordStatus;

  // Legacy field, this will represent the connector from the first identity, most likely email.
  public UUID connectorId = BaseConnectorConfiguration.FUSIONAUTH_CONNECTOR_ID;

  public String encryptionScheme;

  public Integer factor;

  public UUID id;

  public ZonedDateTime lastLoginInstant;

  public String password;

  public ChangePasswordReason passwordChangeReason;

  public boolean passwordChangeRequired;

  public ZonedDateTime passwordLastUpdateInstant;

  public String salt;

  // When unique usernames are enabled, this will represent the unique username. This value may be used to login.
  public String uniqueUsername;

  // When unique usernames are enabled, this value may be different than 'uniqueUsername' and in that case will represent the base username the user selected.
  public String username;

  public ContentStatus usernameStatus = ContentStatus.ACTIVE;

  /**
   * @deprecated This value is still here for compatibility reasons but starting in FusionAuth 1.56.0, use the verified
   * boolean value on the identities collection entry of type `email`. See {@link UserIdentity#verified}
   */
  @Deprecated // JDK 8 compatible/client library (since = "1.56.0")
  public boolean verified;

  /**
   * The instant when one of a user's identities was first verified. Once this value is set, it will not change.
   */
  public ZonedDateTime verifiedInstant;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SecureIdentity that = (SecureIdentity) o;
    return passwordChangeRequired == that.passwordChangeRequired &&
           verified == that.verified &&
           Objects.equals(breachedPasswordLastCheckedInstant, that.breachedPasswordLastCheckedInstant) &&
           breachedPasswordStatus == that.breachedPasswordStatus &&
           Objects.equals(connectorId, that.connectorId) &&
           Objects.equals(encryptionScheme, that.encryptionScheme) &&
           Objects.equals(factor, that.factor) &&
           Objects.equals(id, that.id) &&
           Objects.equals(identities, that.identities) &&
           Objects.equals(lastLoginInstant, that.lastLoginInstant) &&
           Objects.equals(password, that.password) &&
           passwordChangeReason == that.passwordChangeReason &&
           Objects.equals(passwordLastUpdateInstant, that.passwordLastUpdateInstant) &&
           Objects.equals(salt, that.salt) &&
           Objects.equals(uniqueUsername, that.uniqueUsername) &&
           Objects.equals(username, that.username) &&
           usernameStatus == that.usernameStatus &&
           Objects.equals(verifiedInstant, that.verifiedInstant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(breachedPasswordLastCheckedInstant,
                        breachedPasswordStatus,
                        connectorId,
                        encryptionScheme,
                        factor,
                        id,
                        identities,
                        lastLoginInstant,
                        password,
                        passwordChangeReason,
                        passwordChangeRequired,
                        passwordLastUpdateInstant,
                        salt,
                        uniqueUsername,
                        username,
                        usernameStatus,
                        verified,
                        verifiedInstant);
  }
}
