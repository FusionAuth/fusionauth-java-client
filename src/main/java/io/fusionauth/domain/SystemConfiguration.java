/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.event.EventType;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import static java.util.Arrays.asList;

/**
 * @author Brian Pontarelli
 */
public class SystemConfiguration implements Buildable<SystemConfiguration>, _InternalJSONColumn {
  /**
   * Base64 encoded Initialization Vector for prime-mvc. This is currently only used to encrypt and de-crypt saved
   * request cookies.
   */
  @InternalJSONColumn
  public String cookieEncryptionIV;

  /**
   * Base64 encoded Encryption Key for prime-mvc. This is currently only used to encrypt and de-crypt saved request
   * cookies.
   */
  @InternalJSONColumn
  public String cookieEncryptionKey;

  public Map<String, Object> data = new HashMap<>();

  @InternalJSONColumn
  public EmailConfiguration emailConfiguration = new EmailConfiguration();

  @InternalJSONColumn
  public EventConfiguration eventConfiguration = new EventConfiguration();

  @InternalJSONColumn
  public ExternalIdentifierConfiguration externalIdentifierConfiguration = new ExternalIdentifierConfiguration();

  /**
   * Failed Authentication Cache.
   */
  @InternalJSONColumn
  public FailedAuthenticationConfiguration failedAuthenticationConfiguration = new FailedAuthenticationConfiguration();

  /**
   * Time in seconds until an inactive session will be invalidated. Used when creating a new session in the FusionAuth
   * OAuth frontend.
   * <p>
   * Default is 60 minutes.
   */
  public int httpSessionMaxInactiveInterval = 3600;

  /**
   * Global System JWT Configuration used to sign and drop a JWT cookie on a successful login.
   */
  @InternalJSONColumn
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  /**
   * Logout redirect URL when calling the <code>/oauth2/logout</code> endpoint. If this the
   * <code>Application.oauthConfiguration.logoutURL</code> is defined it will be used instead.
   */
  public URI logoutURL;

  @InternalJSONColumn
  public MaximumPasswordAge maximumPasswordAge = new MaximumPasswordAge();

  @InternalJSONColumn
  public MinimumPasswordAge minimumPasswordAge = new MinimumPasswordAge();

  @InternalJSONColumn
  public PasswordEncryptionConfiguration passwordEncryptionConfiguration = new PasswordEncryptionConfiguration();

  @InternalJSONColumn
  public PasswordValidationRules passwordValidationRules = new PasswordValidationRules();

  public ZoneId reportTimezone;

  @InternalJSONColumn
  public UIConfiguration uiConfiguration = new UIConfiguration();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SystemConfiguration)) {
      return false;
    }
    SystemConfiguration that = (SystemConfiguration) o;
    return httpSessionMaxInactiveInterval == that.httpSessionMaxInactiveInterval &&
        Objects.equals(cookieEncryptionIV, that.cookieEncryptionIV) &&
        Objects.equals(cookieEncryptionKey, that.cookieEncryptionKey) &&
        Objects.equals(data, that.data) &&
        Objects.equals(emailConfiguration, that.emailConfiguration) &&
        Objects.equals(eventConfiguration, that.eventConfiguration) &&
        Objects.equals(externalIdentifierConfiguration, that.externalIdentifierConfiguration) &&
        Objects.equals(failedAuthenticationConfiguration, that.failedAuthenticationConfiguration) &&
        Objects.equals(jwtConfiguration, that.jwtConfiguration) &&
        Objects.equals(logoutURL, that.logoutURL) &&
        Objects.equals(maximumPasswordAge, that.maximumPasswordAge) &&
        Objects.equals(minimumPasswordAge, that.minimumPasswordAge) &&
        Objects.equals(passwordEncryptionConfiguration, that.passwordEncryptionConfiguration) &&
        Objects.equals(passwordValidationRules, that.passwordValidationRules) &&
        Objects.equals(reportTimezone, that.reportTimezone) &&
        Objects.equals(uiConfiguration, that.uiConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cookieEncryptionIV, cookieEncryptionKey, data, emailConfiguration, eventConfiguration, externalIdentifierConfiguration,
                        failedAuthenticationConfiguration, httpSessionMaxInactiveInterval, jwtConfiguration, logoutURL, maximumPasswordAge,
                        minimumPasswordAge, passwordEncryptionConfiguration, passwordValidationRules, reportTimezone, uiConfiguration);
  }

  public void normalize() {
    // Normalize Line returns in the public / private keys
    if (jwtConfiguration != null) {
      jwtConfiguration.normalize();
    }

    if (uiConfiguration != null) {
      uiConfiguration.normalize();
    }
  }

  public SystemConfiguration secure() {
    if (jwtConfiguration != null) {
      jwtConfiguration.secure();
    }

    cookieEncryptionIV = null;
    cookieEncryptionKey = null;

    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class EmailConfiguration extends Enableable implements Buildable<EmailConfiguration> {
    public UUID forgotPasswordEmailTemplateId;

    public String host;

    public String password;

    public Integer port;

    public EmailSecurityType security;

    public UUID setPasswordEmailTemplateId;

    public String username;

    public UUID verificationEmailTemplateId;

    public boolean verifyEmail;

    public boolean verifyEmailWhenChanged;

    public EmailConfiguration() {
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EmailConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      EmailConfiguration that = (EmailConfiguration) o;
      return verifyEmail == that.verifyEmail &&
          verifyEmailWhenChanged == that.verifyEmailWhenChanged &&
          Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
          Objects.equals(host, that.host) &&
          Objects.equals(password, that.password) &&
          Objects.equals(port, that.port) &&
          security == that.security &&
          Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId) &&
          Objects.equals(username, that.username) &&
          Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId);
    }

    @Override
    public int hashCode() {

      return Objects.hash(super.hashCode(), forgotPasswordEmailTemplateId, host, password, port, security, setPasswordEmailTemplateId, username, verificationEmailTemplateId, verifyEmail, verifyEmailWhenChanged);
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

  public static class EventConfiguration implements Buildable<EventConfiguration> {
    public Map<EventType, EventConfigurationData> events = new HashMap<>();

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      EventConfiguration that = (EventConfiguration) o;
      return Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
      return Objects.hash(events);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }

    public static class EventConfigurationData extends Enableable {
      public TransactionType transactionType;

      @JacksonConstructor
      public EventConfigurationData() {
      }

      public EventConfigurationData(boolean enabled, TransactionType transactionType) {
        this.enabled = enabled;
        this.transactionType = transactionType;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        EventConfigurationData that = (EventConfigurationData) o;
        return super.equals(o) &&
            Objects.equals(transactionType, that.transactionType);
      }

      @Override
      public int hashCode() {
        return Objects.hash(super.hashCode(), transactionType);
      }

      @Override
      public String toString() {
        return ToString.toString(this);
      }
    }
  }

  public static class ExternalIdentifierConfiguration implements Buildable<ExternalIdentifierConfiguration> {
    public int authorizationGrantIdTimeToLiveInSeconds;

    public int changePasswordIdTimeToLiveInSeconds;

    public int emailVerificationIdTimeToLiveInSeconds;

    public int registrationVerificationIdTimeToLiveInSeconds;

    public int setupPasswordIdTimeToLiveInSeconds;

    public int twoFactorIdTimeToLiveInSeconds;

    public int twoFactorTrustIdTimeToLiveInSeconds;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ExternalIdentifierConfiguration)) {
        return false;
      }
      ExternalIdentifierConfiguration that = (ExternalIdentifierConfiguration) o;
      return authorizationGrantIdTimeToLiveInSeconds == that.authorizationGrantIdTimeToLiveInSeconds &&
          changePasswordIdTimeToLiveInSeconds == that.changePasswordIdTimeToLiveInSeconds &&
          emailVerificationIdTimeToLiveInSeconds == that.emailVerificationIdTimeToLiveInSeconds &&
          registrationVerificationIdTimeToLiveInSeconds == that.registrationVerificationIdTimeToLiveInSeconds &&
          setupPasswordIdTimeToLiveInSeconds == that.setupPasswordIdTimeToLiveInSeconds &&
          twoFactorIdTimeToLiveInSeconds == that.twoFactorIdTimeToLiveInSeconds &&
          twoFactorTrustIdTimeToLiveInSeconds == that.twoFactorTrustIdTimeToLiveInSeconds;
    }

    @Override
    public int hashCode() {
      return Objects.hash(authorizationGrantIdTimeToLiveInSeconds, changePasswordIdTimeToLiveInSeconds, emailVerificationIdTimeToLiveInSeconds,
                          registrationVerificationIdTimeToLiveInSeconds, setupPasswordIdTimeToLiveInSeconds, twoFactorIdTimeToLiveInSeconds,
                          twoFactorTrustIdTimeToLiveInSeconds);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class UIConfiguration {
    public String headerColor;

    public LoginTheme loginTheme;

    public String logoURL;

    public String menuFontColor;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof UIConfiguration)) {
        return false;
      }
      UIConfiguration that = (UIConfiguration) o;
      return Objects.equals(logoURL, that.logoURL) &&
          Objects.equals(headerColor, that.headerColor) &&
          Objects.equals(loginTheme, that.loginTheme) &&
          Objects.equals(menuFontColor, that.menuFontColor);
    }

    @Override
    public int hashCode() {
      return Objects.hash(logoURL, headerColor, loginTheme, menuFontColor);
    }

    public void normalize() {
      if (loginTheme != null) {
        loginTheme.normalize();
      }
    }

    public static class LoginTheme extends Enableable implements Buildable<LoginTheme> {
      public static final Set<String> suppliers = Collections.unmodifiableSet(new HashSet<>(asList(
          "emailComplete", "emailSend", "emailVerify", "helpers", "oauth2Authorize", "oauth2Error", "oauth2TwoFactor", "passwordChange",
          "passwordComplete", "passwordForgot", "passwordSent", "registrationComplete", "registrationSend", "registrationVerify"
      )));

      public String emailComplete;

      public String emailSend;

      public String emailVerify;

      public String helpers;

      public ZonedDateTime lastModified;

      public String oauth2Authorize;

      public String oauth2Error;

      public String oauth2TwoFactor;

      public String passwordChange;

      public String passwordComplete;

      public String passwordForgot;

      public String passwordSent;

      public String registrationComplete;

      public String registrationSend;

      public String registrationVerify;

      public String stylesheet;

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (!(o instanceof LoginTheme)) {
          return false;
        }
        if (!super.equals(o)) {
          return false;
        }
        LoginTheme that = (LoginTheme) o;
        return Objects.equals(emailComplete, that.emailComplete) &&
            Objects.equals(emailSend, that.emailSend) &&
            Objects.equals(emailVerify, that.emailVerify) &&
            Objects.equals(helpers, that.helpers) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(oauth2Authorize, that.oauth2Authorize) &&
            Objects.equals(oauth2Error, that.oauth2Error) &&
            Objects.equals(oauth2TwoFactor, that.oauth2TwoFactor) &&
            Objects.equals(passwordChange, that.passwordChange) &&
            Objects.equals(passwordComplete, that.passwordComplete) &&
            Objects.equals(passwordForgot, that.passwordForgot) &&
            Objects.equals(passwordSent, that.passwordSent) &&
            Objects.equals(registrationComplete, that.registrationComplete) &&
            Objects.equals(registrationSend, that.registrationSend) &&
            Objects.equals(registrationVerify, that.registrationVerify) &&
            Objects.equals(stylesheet, that.stylesheet);
      }

      @Override
      public int hashCode() {
        return Objects.hash(super.hashCode(), emailComplete, emailSend, emailVerify, helpers, lastModified, oauth2Authorize,
                            oauth2Error, oauth2TwoFactor, passwordChange, passwordComplete, passwordForgot, passwordSent, registrationComplete,
                            registrationSend, registrationVerify, stylesheet);
      }

      public void normalize() {
        emailComplete = normalize(emailComplete);
        emailSend = normalize(emailSend);
        emailVerify = normalize(emailVerify);
        helpers = normalize(helpers);
        oauth2Authorize = normalize(oauth2Authorize);
        oauth2Error = normalize(oauth2Error);
        oauth2TwoFactor = normalize(oauth2TwoFactor);
        passwordChange = normalize(passwordChange);
        passwordComplete = normalize(passwordComplete);
        passwordForgot = normalize(passwordForgot);
        passwordSent = normalize(passwordSent);
        registrationComplete = normalize(registrationComplete);
        registrationSend = normalize(registrationSend);
        registrationVerify = normalize(registrationVerify);
        stylesheet = normalize(stylesheet);
      }

      public boolean wasUpdated(LoginTheme other) {
        if (other == null) {
          return true;
        }

        return !Objects.equals(emailComplete, other.emailComplete) ||
            !Objects.equals(emailSend, other.emailSend) ||
            !Objects.equals(emailVerify, other.emailVerify) ||
            !Objects.equals(helpers, other.helpers) ||
            !Objects.equals(oauth2Authorize, other.oauth2Authorize) ||
            !Objects.equals(oauth2Error, other.oauth2Error) ||
            !Objects.equals(oauth2TwoFactor, other.oauth2TwoFactor) ||
            !Objects.equals(passwordChange, other.passwordChange) ||
            !Objects.equals(passwordComplete, other.passwordComplete) ||
            !Objects.equals(passwordForgot, other.passwordForgot) ||
            !Objects.equals(passwordSent, other.passwordSent) ||
            !Objects.equals(registrationComplete, other.registrationComplete) ||
            !Objects.equals(registrationSend, other.registrationSend) ||
            !Objects.equals(registrationVerify, other.registrationVerify) ||
            !Objects.equals(stylesheet, other.stylesheet);
      }

      private String normalize(String value) {
        return value == null || value.trim().length() == 0 ? null : value;
      }
    }
  }
}
