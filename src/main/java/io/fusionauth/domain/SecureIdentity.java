/*
 * Copyright (c) 2018-2020, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Daniel DeGroff
 */
public class SecureIdentity {
  public ZonedDateTime breachedPasswordLastCheckedInstant;

  public BreachedPasswordStatus breachedPasswordStatus;

  public UUID connectorId;

  public String encryptionScheme;

  public Integer factor;

  public UUID id;

  public ZonedDateTime lastLoginInstant;

  public String password;

  public ChangePasswordReason passwordChangeReason;

  public boolean passwordChangeRequired;

  public ZonedDateTime passwordLastUpdateInstant;

  public String salt;

  public TwoFactorDelivery twoFactorDelivery;

  public boolean twoFactorEnabled;

  public String twoFactorSecret;

  public String username;

  public ContentStatus usernameStatus;

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
           twoFactorEnabled == that.twoFactorEnabled &&
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
           twoFactorDelivery == that.twoFactorDelivery &&
           Objects.equals(twoFactorSecret, that.twoFactorSecret) &&
           Objects.equals(username, that.username) &&
           usernameStatus == that.usernameStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(breachedPasswordLastCheckedInstant, breachedPasswordStatus, connectorId, encryptionScheme, factor, id, lastLoginInstant,
                        password, passwordChangeReason, passwordChangeRequired, passwordLastUpdateInstant, salt, twoFactorDelivery,
                        twoFactorEnabled, twoFactorSecret, username, usernameStatus, verified);
  }
}
