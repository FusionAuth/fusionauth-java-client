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

  public String encryptionScheme;

  public Integer factor;

  public UUID id;

  public String password;

  public ChangePasswordReason passwordChangeReason;

  public boolean passwordChangeRequired;

  public ZonedDateTime passwordLastUpdateInstant;

  public String salt;

  public boolean verified;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SecureIdentity)) {
      return false;
    }
    SecureIdentity that = (SecureIdentity) o;
    return passwordChangeRequired == that.passwordChangeRequired &&
        passwordChangeReason == that.passwordChangeReason &&
        verified == that.verified &&
        Objects.equals(breachedPasswordLastCheckedInstant, that.breachedPasswordLastCheckedInstant) &&
        breachedPasswordStatus == that.breachedPasswordStatus &&
        Objects.equals(encryptionScheme, that.encryptionScheme) &&
        Objects.equals(factor, that.factor) &&
        Objects.equals(password, that.password) &&
        Objects.equals(passwordLastUpdateInstant, that.passwordLastUpdateInstant) &&
        Objects.equals(salt, that.salt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(breachedPasswordLastCheckedInstant, breachedPasswordStatus, encryptionScheme, factor, password, passwordChangeReason, passwordChangeRequired, passwordLastUpdateInstant, salt, verified);
  }
}
