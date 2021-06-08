/*
 * Copyright (c) 2018-2020, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import io.fusionauth.domain.connector.BaseConnectorConfiguration;

/**
 * @author Daniel DeGroff
 */
public class SecureIdentity {
  public ZonedDateTime breachedPasswordLastCheckedInstant;

  public BreachedPasswordStatus breachedPasswordStatus;

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

  public boolean verified;

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
           Objects.equals(lastLoginInstant, that.lastLoginInstant) &&
           Objects.equals(password, that.password) &&
           passwordChangeReason == that.passwordChangeReason &&
           Objects.equals(passwordLastUpdateInstant, that.passwordLastUpdateInstant) &&
           Objects.equals(salt, that.salt) &&
           Objects.equals(uniqueUsername, that.uniqueUsername) &&
           Objects.equals(username, that.username) &&
           usernameStatus == that.usernameStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(breachedPasswordLastCheckedInstant, breachedPasswordStatus, connectorId, encryptionScheme, factor, id, lastLoginInstant,
                        password, passwordChangeReason, passwordChangeRequired, passwordLastUpdateInstant, salt, uniqueUsername, username, usernameStatus, verified);
  }
}
