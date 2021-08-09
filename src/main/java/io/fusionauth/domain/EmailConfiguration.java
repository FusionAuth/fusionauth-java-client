/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Brian Pontarelli
 */
public class EmailConfiguration implements Buildable<EmailConfiguration> {
  @JsonIgnore
  @SuppressWarnings("unused")
  public static List<String> EmailTemplateIdFieldNames = Arrays.stream(EmailConfiguration.class.getDeclaredFields())
                                                               .map(Field::getName)
                                                               .filter(name -> name.endsWith("EmailTemplateId"))
                                                               .sorted()
                                                               .collect(Collectors.toList());

  @JsonIgnore
  @SuppressWarnings("unused")
  public static List<Field> EmailTemplateIdFields = Arrays.stream(EmailConfiguration.class.getDeclaredFields())
                                                          .filter(f -> f.getName().endsWith("EmailTemplateId"))
                                                          .sorted(Comparator.comparing(Field::getName))
                                                          .collect(Collectors.toList());

  public String defaultFromEmail = "change-me@example.com";

  public String defaultFromName;

  @ExcludeFromDatabaseDataColumn
  public UUID emailUpdateEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID emailVerifiedEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID forgotPasswordEmailTemplateId;

  public String host = "localhost";

  @ExcludeFromDatabaseDataColumn
  public UUID loginIdInUseOnCreateEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID loginIdInUseOnUpdateEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID loginNewDeviceEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID loginSuspiciousEmailTemplateId;

  public String password;

  @ExcludeFromDatabaseDataColumn
  public UUID passwordResetSuccessEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID passwordUpdateEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID passwordlessEmailTemplateId;

  public Integer port = 25;

  public String properties;

  public EmailSecurityType security;

  @ExcludeFromDatabaseDataColumn
  public UUID setPasswordEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID twoFactorMethodAddEmailTemplateId;

  @ExcludeFromDatabaseDataColumn
  public UUID twoFactorMethodRemoveEmailTemplateId;

  public EmailUnverifiedOptions unverified = new EmailUnverifiedOptions();

  public String username;

  @ExcludeFromDatabaseDataColumn
  public UUID verificationEmailTemplateId;

  public VerificationStrategy verificationStrategy;

  public boolean verifyEmail;

  public boolean verifyEmailWhenChanged;

  @JacksonConstructor
  public EmailConfiguration() {
  }

  public EmailConfiguration(EmailConfiguration other) {
    this.defaultFromEmail = other.defaultFromEmail;
    this.defaultFromName = other.defaultFromName;
    this.emailUpdateEmailTemplateId = other.emailUpdateEmailTemplateId;
    this.emailVerifiedEmailTemplateId = other.emailVerifiedEmailTemplateId;
    this.forgotPasswordEmailTemplateId = other.forgotPasswordEmailTemplateId;
    this.host = other.host;
    this.loginNewDeviceEmailTemplateId = other.loginNewDeviceEmailTemplateId;
    this.loginSuspiciousEmailTemplateId = other.loginSuspiciousEmailTemplateId;
    this.password = other.password;
    this.passwordlessEmailTemplateId = other.passwordlessEmailTemplateId;
    this.passwordResetSuccessEmailTemplateId = other.passwordResetSuccessEmailTemplateId;
    this.passwordUpdateEmailTemplateId = other.passwordUpdateEmailTemplateId;
    this.port = other.port;
    this.properties = other.properties;
    this.security = other.security;
    this.setPasswordEmailTemplateId = other.setPasswordEmailTemplateId;
    this.twoFactorMethodAddEmailTemplateId = other.twoFactorMethodAddEmailTemplateId;
    this.twoFactorMethodRemoveEmailTemplateId = other.twoFactorMethodRemoveEmailTemplateId;
    this.unverified = new EmailUnverifiedOptions(other.unverified);
    this.username = other.username;
    this.loginIdInUseOnCreateEmailTemplateId = other.loginIdInUseOnCreateEmailTemplateId;
    this.loginIdInUseOnUpdateEmailTemplateId = other.loginIdInUseOnUpdateEmailTemplateId;
    this.verificationEmailTemplateId = other.verificationEmailTemplateId;
    this.verificationStrategy = other.verificationStrategy;
    this.verifyEmail = other.verifyEmail;
    this.verifyEmailWhenChanged = other.verifyEmailWhenChanged;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailConfiguration that = (EmailConfiguration) o;
    return verifyEmail == that.verifyEmail &&
           verifyEmailWhenChanged == that.verifyEmailWhenChanged &&
           Objects.equals(defaultFromEmail, that.defaultFromEmail) &&
           Objects.equals(defaultFromName, that.defaultFromName) &&
           Objects.equals(emailUpdateEmailTemplateId, that.emailUpdateEmailTemplateId) &&
           Objects.equals(emailVerifiedEmailTemplateId, that.emailVerifiedEmailTemplateId) &&
           Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
           Objects.equals(host, that.host) &&
           Objects.equals(loginIdInUseOnCreateEmailTemplateId, that.loginIdInUseOnCreateEmailTemplateId) &&
           Objects.equals(loginIdInUseOnUpdateEmailTemplateId, that.loginIdInUseOnUpdateEmailTemplateId) &&
           Objects.equals(loginNewDeviceEmailTemplateId, that.loginNewDeviceEmailTemplateId) &&
           Objects.equals(loginSuspiciousEmailTemplateId, that.loginSuspiciousEmailTemplateId) &&
           Objects.equals(password, that.password) &&
           Objects.equals(passwordResetSuccessEmailTemplateId, that.passwordResetSuccessEmailTemplateId) &&
           Objects.equals(passwordUpdateEmailTemplateId, that.passwordUpdateEmailTemplateId) &&
           Objects.equals(passwordlessEmailTemplateId, that.passwordlessEmailTemplateId) &&
           Objects.equals(port, that.port) &&
           Objects.equals(properties, that.properties) &&
           security == that.security &&
           Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId) &&
           Objects.equals(twoFactorMethodAddEmailTemplateId, that.twoFactorMethodAddEmailTemplateId) &&
           Objects.equals(twoFactorMethodRemoveEmailTemplateId, that.twoFactorMethodRemoveEmailTemplateId) &&
           Objects.equals(unverified, that.unverified) &&
           Objects.equals(username, that.username) &&
           Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId) &&
           verificationStrategy == that.verificationStrategy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultFromEmail, defaultFromName, emailUpdateEmailTemplateId, emailVerifiedEmailTemplateId, forgotPasswordEmailTemplateId, host, loginIdInUseOnCreateEmailTemplateId, loginIdInUseOnUpdateEmailTemplateId, loginNewDeviceEmailTemplateId, loginSuspiciousEmailTemplateId, password, passwordResetSuccessEmailTemplateId, passwordUpdateEmailTemplateId, passwordlessEmailTemplateId, port, properties, security, setPasswordEmailTemplateId, twoFactorMethodAddEmailTemplateId, twoFactorMethodRemoveEmailTemplateId, unverified, username, verificationEmailTemplateId, verificationStrategy, verifyEmail, verifyEmailWhenChanged);
  }

  public void normalize() {
    host = trim(host);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum EmailSecurityType {
    NONE,
    SSL,
    TLS
  }
}