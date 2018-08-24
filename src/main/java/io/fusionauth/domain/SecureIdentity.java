/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Daniel DeGroff
 */
public class SecureIdentity {
  public String encryptionScheme;

  public Integer factor;

  public UUID id;

  public String password;

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
    SecureIdentity identity = (SecureIdentity) o;
    return Objects.equals(encryptionScheme, identity.encryptionScheme) &&
        Objects.equals(factor, identity.factor) &&
        Objects.equals(password, identity.password) &&
        Objects.equals(passwordChangeRequired, identity.passwordChangeRequired) &&
        Objects.equals(passwordLastUpdateInstant, identity.passwordLastUpdateInstant) &&
        Objects.equals(salt, identity.salt) &&
        Objects.equals(verified, identity.verified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(encryptionScheme, factor, password, passwordChangeRequired, passwordLastUpdateInstant, salt, verified);
  }
}
