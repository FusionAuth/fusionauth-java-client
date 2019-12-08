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

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.lineReturns;
import static io.fusionauth.domain.util.Normalizer.trimToNull;
import static java.util.Arrays.asList;

/**
 * @author Trevor Smith
 */
public class Theme implements Buildable<Theme>, _InternalJSONColumn {
  public static final UUID FUSIONAUTH_THEME_ID = UUID.fromString("75a068fd-e94b-451a-9aeb-3ddb9a3b5987");

  public Map<String, Object> data = new HashMap<>();

  @InternalJSONColumn
  public String defaultMessages;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  @InternalJSONColumn
  public LocalizedStrings localizedMessages = new LocalizedStrings();

  public String name;

  @InternalJSONColumn
  public String stylesheet;

  @InternalJSONColumn
  public Templates templates;

  public Theme() {
  }

  public Theme(Theme theme) {
    this.data.putAll(theme.data);
    this.defaultMessages = theme.defaultMessages;
    this.id = theme.id;
    this.insertInstant = theme.insertInstant;
    this.lastUpdateInstant = theme.lastUpdateInstant;
    this.localizedMessages.putAll(theme.localizedMessages);
    this.name = theme.name;
    this.stylesheet = theme.stylesheet;
    if (theme.templates != null) {
      this.templates = new Templates(theme.templates);
    }
  }

  /**
   * @return This implementation always returns an empty set, but this is overridden in FusionAuth-App to provide the correct set of
   * additional locales.
   */
  public Set<Locale> additionalLocales() {
    return Collections.emptySet();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Theme)) {
      return false;
    }
    Theme that = (Theme) o;
    return Objects.equals(data, that.data) &&
        Objects.equals(defaultMessages, that.defaultMessages) &&
        Objects.equals(insertInstant, that.insertInstant) &&
        Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
        Objects.equals(localizedMessages, that.localizedMessages) &&
        Objects.equals(name, that.name) &&
        Objects.equals(stylesheet, that.stylesheet) &&
        Objects.equals(templates, that.templates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, defaultMessages, insertInstant, lastUpdateInstant, localizedMessages, name, stylesheet, templates);
  }

  /**
   * Looks up a message. This is defined here to assist with code completion in FreeMarker templates. The FusionAuth-App package overrides
   * this with the actual lookup method.
   *
   * @param key       The key of the message to lookup.
   * @param arguments Optional arguments used to format the message (printf style)/
   * @return This version always returns an empty String.
   */
  public String message(String key, Object... arguments) {
    return "";
  }

  @JsonIgnore
  public boolean missingTemplate() {
    if (templates == null) {
      return true;
    }

    return Stream.of(templates.emailComplete,
                     templates.emailSend,
                     templates.emailVerify,
                     templates.helpers,
                     templates.oauth2Authorize,
                     templates.oauth2ChildRegistrationNotAllowed,
                     templates.oauth2ChildRegistrationNotAllowedComplete,
                     templates.oauth2CompleteRegistration,
                     templates.oauth2Device,
                     templates.oauth2DeviceComplete,
                     templates.oauth2Error,
                     templates.oauth2Logout,
                     templates.oauth2Passwordless,
                     templates.oauth2Register,
                     templates.oauth2TwoFactor,
                     templates.oauth2Wait,
                     templates.passwordChange,
                     templates.passwordComplete,
                     templates.passwordForgot,
                     templates.passwordSent,
                     templates.registrationComplete,
                     templates.registrationSend,
                     templates.registrationVerify)
                 .anyMatch(Objects::isNull);
  }

  public void normalize() {
    if (defaultMessages != null) {
      defaultMessages = lineReturns(defaultMessages);
    }

    if (templates != null) {
      templates.normalize();
    }

    if (localizedMessages != null) {
      localizedMessages.normalize();
    }

    stylesheet = lineReturns(stylesheet);
  }

  /**
   * @return Safely return the stylesheet or an empty string.
   */
  public String stylesheet() {
    if (stylesheet == null) {
      return "";
    }

    return Normalizer.lineReturns(stylesheet);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class Templates implements Buildable<Templates> {
    public static final Set<String> suppliers = Collections.unmodifiableSet(new HashSet<>(asList(
        "emailComplete", "emailSend", "emailVerify", "helpers", "oauth2Authorize", "oauth2ChildRegistrationNotAllowed",
        "oauth2ChildRegistrationNotAllowedComplete", "oauth2CompleteRegistration", "oauth2Device", "oauth2DeviceComplete", "oauth2Error", "oauth2Logout",
        "oauth2Passwordless", "oauth2Register", "oauth2TwoFactor", "passwordChange", "passwordComplete", "passwordForgot", "passwordSent",
        "registrationComplete", "registrationSend", "registrationVerify"
    )));

    public String emailComplete;

    public String emailSend;

    public String emailVerify;

    public String helpers;

    public String oauth2Authorize;

    public String oauth2ChildRegistrationNotAllowed;

    public String oauth2ChildRegistrationNotAllowedComplete;

    public String oauth2CompleteRegistration;

    public String oauth2Device;

    public String oauth2DeviceComplete;

    public String oauth2Error;

    public String oauth2Logout;

    public String oauth2Passwordless;

    public String oauth2Register;

    public String oauth2TwoFactor;

    public String oauth2Wait;

    public String passwordChange;

    public String passwordComplete;

    public String passwordForgot;

    public String passwordSent;

    public String registrationComplete;

    public String registrationSend;

    public String registrationVerify;

    public Templates() {
    }

    public Templates(Templates other) {
      this.emailComplete = other.emailComplete;
      this.emailSend = other.emailSend;
      this.emailVerify = other.emailVerify;
      this.helpers = other.helpers;
      this.oauth2Authorize = other.oauth2Authorize;
      this.oauth2ChildRegistrationNotAllowed = other.oauth2ChildRegistrationNotAllowed;
      this.oauth2ChildRegistrationNotAllowedComplete = other.oauth2ChildRegistrationNotAllowedComplete;
      this.oauth2CompleteRegistration = other.oauth2CompleteRegistration;
      this.oauth2Device = other.oauth2Device;
      this.oauth2DeviceComplete = other.oauth2DeviceComplete;
      this.oauth2Error = other.oauth2Error;
      this.oauth2Logout = other.oauth2Logout;
      this.oauth2Passwordless = other.oauth2Passwordless;
      this.oauth2Register = other.oauth2Register;
      this.oauth2TwoFactor = other.oauth2TwoFactor;
      this.oauth2Wait = other.oauth2Wait;
      this.passwordChange = other.passwordChange;
      this.passwordComplete = other.passwordComplete;
      this.passwordForgot = other.passwordForgot;
      this.passwordSent = other.passwordSent;
      this.registrationComplete = other.registrationComplete;
      this.registrationSend = other.registrationSend;
      this.registrationVerify = other.registrationVerify;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Templates that = (Templates) o;
      return Objects.equals(emailComplete, that.emailComplete) &&
          Objects.equals(emailSend, that.emailSend) &&
          Objects.equals(emailVerify, that.emailVerify) &&
          Objects.equals(helpers, that.helpers) &&
          Objects.equals(oauth2Authorize, that.oauth2Authorize) &&
          Objects.equals(oauth2ChildRegistrationNotAllowed, that.oauth2ChildRegistrationNotAllowed) &&
          Objects.equals(oauth2ChildRegistrationNotAllowedComplete, that.oauth2ChildRegistrationNotAllowedComplete) &&
          Objects.equals(oauth2CompleteRegistration, that.oauth2CompleteRegistration) &&
          Objects.equals(oauth2Device, that.oauth2Device) &&
          Objects.equals(oauth2DeviceComplete, that.oauth2DeviceComplete) &&
          Objects.equals(oauth2Error, that.oauth2Error) &&
          Objects.equals(oauth2Logout, that.oauth2Logout) &&
          Objects.equals(oauth2Passwordless, that.oauth2Passwordless) &&
          Objects.equals(oauth2Register, that.oauth2Register) &&
          Objects.equals(oauth2TwoFactor, that.oauth2TwoFactor) &&
          Objects.equals(oauth2Wait, that.oauth2Wait) &&
          Objects.equals(passwordChange, that.passwordChange) &&
          Objects.equals(passwordComplete, that.passwordComplete) &&
          Objects.equals(passwordForgot, that.passwordForgot) &&
          Objects.equals(passwordSent, that.passwordSent) &&
          Objects.equals(registrationComplete, that.registrationComplete) &&
          Objects.equals(registrationSend, that.registrationSend) &&
          Objects.equals(registrationVerify, that.registrationVerify);
    }

    @Override
    public int hashCode() {
      return Objects.hash(emailComplete,
                          emailSend,
                          emailVerify,
                          helpers,
                          oauth2Authorize,
                          oauth2ChildRegistrationNotAllowed,
                          oauth2ChildRegistrationNotAllowedComplete,
                          oauth2CompleteRegistration,
                          oauth2Device,
                          oauth2DeviceComplete,
                          oauth2Error,
                          oauth2Logout,
                          oauth2Passwordless,
                          oauth2Register,
                          oauth2TwoFactor,
                          oauth2Wait,
                          passwordChange,
                          passwordComplete,
                          passwordForgot,
                          passwordSent,
                          registrationComplete,
                          registrationSend,
                          registrationVerify);
    }

    @SuppressWarnings("DuplicatedCode")
    public void normalize() {
      emailComplete = lineReturns(trimToNull(emailComplete));
      emailSend = lineReturns(trimToNull(emailSend));
      emailVerify = lineReturns(trimToNull(emailVerify));
      helpers = lineReturns(trimToNull(helpers));
      oauth2Authorize = lineReturns(trimToNull(oauth2Authorize));
      oauth2ChildRegistrationNotAllowed = lineReturns(trimToNull(oauth2ChildRegistrationNotAllowed));
      oauth2ChildRegistrationNotAllowedComplete = lineReturns(trimToNull(oauth2ChildRegistrationNotAllowedComplete));
      oauth2CompleteRegistration = lineReturns(trimToNull(oauth2CompleteRegistration));
      oauth2Device = lineReturns(trimToNull(oauth2Device));
      oauth2DeviceComplete = lineReturns(trimToNull(oauth2DeviceComplete));
      oauth2Error = lineReturns(trimToNull(oauth2Error));
      oauth2Logout = lineReturns(trimToNull(oauth2Logout));
      oauth2Passwordless = lineReturns(trimToNull(oauth2Passwordless));
      oauth2Register = lineReturns(trimToNull(oauth2Register));
      oauth2TwoFactor = lineReturns(trimToNull(oauth2TwoFactor));
      oauth2Wait = lineReturns(trimToNull(oauth2Wait));
      passwordChange = lineReturns(trimToNull(passwordChange));
      passwordComplete = lineReturns(trimToNull(passwordComplete));
      passwordForgot = lineReturns(trimToNull(passwordForgot));
      passwordSent = lineReturns(trimToNull(passwordSent));
      registrationComplete = lineReturns(trimToNull(registrationComplete));
      registrationSend = lineReturns(trimToNull(registrationSend));
      registrationVerify = lineReturns(trimToNull(registrationVerify));
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
