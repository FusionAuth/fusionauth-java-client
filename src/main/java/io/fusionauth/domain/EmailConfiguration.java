/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

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

  public List<EmailHeader> additionalHeaders = new ArrayList<>();

  public boolean debug;

  public String defaultFromEmail = "change-me@example.com";

  public String defaultFromName;

  
  public UUID emailUpdateEmailTemplateId;

  
  public UUID emailVerifiedEmailTemplateId;

  
  public UUID forgotPasswordEmailTemplateId;

  public String host = "localhost";

  // Allows email to be verified as the result of completing a similar based email workflow such as change password.
  // - When set to false, the user must explicitly complete the email verification workflow even if the user has already
  //   completed a similar email workflow such as change password (when completed by email)
  public boolean implicitEmailVerificationAllowed = true;

  
  public UUID loginIdInUseOnCreateEmailTemplateId;

  
  public UUID loginIdInUseOnUpdateEmailTemplateId;

  
  public UUID loginNewDeviceEmailTemplateId;

  
  public UUID loginSuspiciousEmailTemplateId;

  public String password;

  
  public UUID passwordResetSuccessEmailTemplateId;

  
  public UUID passwordUpdateEmailTemplateId;

  
  public UUID passwordlessEmailTemplateId;

  public Integer port = 25;

  public String properties;

  public EmailSecurityType security;

  
  public UUID setPasswordEmailTemplateId;

  
  public UUID twoFactorMethodAddEmailTemplateId;

  
  public UUID twoFactorMethodRemoveEmailTemplateId;

  public EmailUnverifiedOptions unverified = new EmailUnverifiedOptions();

  public String username;

  
  public UUID verificationEmailTemplateId;

  public VerificationStrategy verificationStrategy;

  public boolean verifyEmail;

  public boolean verifyEmailWhenChanged;

  @JacksonConstructor
  public EmailConfiguration() {
  }

  public EmailConfiguration(EmailConfiguration other) {
    this.additionalHeaders.addAll(other.additionalHeaders);
    this.debug = other.debug;
    this.defaultFromEmail = other.defaultFromEmail;
    this.defaultFromName = other.defaultFromName;
    this.emailUpdateEmailTemplateId = other.emailUpdateEmailTemplateId;
    this.emailVerifiedEmailTemplateId = other.emailVerifiedEmailTemplateId;
    this.forgotPasswordEmailTemplateId = other.forgotPasswordEmailTemplateId;
    this.host = other.host;
    this.implicitEmailVerificationAllowed = other.implicitEmailVerificationAllowed;
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
    return implicitEmailVerificationAllowed == that.implicitEmailVerificationAllowed &&
           verifyEmail == that.verifyEmail &&
           verifyEmailWhenChanged == that.verifyEmailWhenChanged &&
           Objects.equals(additionalHeaders, that.additionalHeaders) &&
           Objects.equals(debug, that.debug) &&
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
    return Objects.hash(additionalHeaders, debug, defaultFromEmail, defaultFromName, emailUpdateEmailTemplateId, emailVerifiedEmailTemplateId, forgotPasswordEmailTemplateId, host, implicitEmailVerificationAllowed, loginIdInUseOnCreateEmailTemplateId, loginIdInUseOnUpdateEmailTemplateId, loginNewDeviceEmailTemplateId, loginSuspiciousEmailTemplateId, password, passwordResetSuccessEmailTemplateId, passwordUpdateEmailTemplateId, passwordlessEmailTemplateId, port, properties, security, setPasswordEmailTemplateId, twoFactorMethodAddEmailTemplateId, twoFactorMethodRemoveEmailTemplateId, unverified, username, verificationEmailTemplateId, verificationStrategy, verifyEmail, verifyEmailWhenChanged);
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