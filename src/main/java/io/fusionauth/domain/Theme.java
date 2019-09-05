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
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;
import static java.util.Arrays.asList;

/**
 * @author Trevor Smith
 */
public class Theme implements Buildable<Theme>, _InternalJSONColumn {
  public static final UUID FUSIONAUTH_THEME_ID = UUID.fromString("75a068fd-e94b-451a-9aeb-3ddb9a3b5987");

  public Map<String, Object> data = new HashMap<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  public boolean isDefault;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  @InternalJSONColumn
  public String stylesheet;

  @InternalJSONColumn
  public Templates templates;

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
        Objects.equals(insertInstant, that.insertInstant) &&
        Objects.equals(isDefault, that.isDefault) &&
        Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
        Objects.equals(templates, that.templates) &&
        Objects.equals(name, that.name) &&
        Objects.equals(stylesheet, that.stylesheet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, insertInstant, isDefault, lastUpdateInstant, templates, name, stylesheet);
  }

  public void normalize() {
    if (templates != null) {
      templates.normalize();
    }

    stylesheet = Normalizer.trimToNull(stylesheet);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class Templates implements Buildable<Templates> {
    public static final Set<String> suppliers = Collections.unmodifiableSet(new HashSet<>(asList(
        "emailComplete", "emailSend", "emailVerify", "helpers", "oauth2Authorize", "oauth2ChildRegistrationNotAllowed",
        "oauth2ChildRegistrationNotAllowedComplete", "oauth2CompleteRegistration", "oauth2Error", "oauth2Passwordless", "oauth2Register",
        "oauth2TwoFactor", "passwordChange", "passwordComplete", "passwordForgot", "passwordSent", "registrationComplete",
        "registrationSend", "registrationVerify"
    )));

    public String emailComplete;

    public String emailSend;

    public String emailVerify;

    public String helpers;

    public String oauth2Authorize;

    public String oauth2ChildRegistrationNotAllowed;

    public String oauth2ChildRegistrationNotAllowedComplete;

    public String oauth2CompleteRegistration;

    public String oauth2Error;

    public String oauth2Passwordless;

    public String oauth2Register;

    public String oauth2TwoFactor;

    public String passwordChange;

    public String passwordComplete;

    public String passwordForgot;

    public String passwordSent;

    public String registrationComplete;

    public String registrationSend;

    public String registrationVerify;

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
          Objects.equals(oauth2Error, that.oauth2Error) &&
          Objects.equals(oauth2Passwordless, that.oauth2Passwordless) &&
          Objects.equals(oauth2Register, that.oauth2Register) &&
          Objects.equals(oauth2TwoFactor, that.oauth2TwoFactor) &&
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
      return Objects.hash(emailComplete, emailSend, emailVerify, helpers, oauth2Authorize, oauth2ChildRegistrationNotAllowed,
                          oauth2ChildRegistrationNotAllowedComplete, oauth2CompleteRegistration, oauth2Error, oauth2Passwordless,
                          oauth2Register, oauth2TwoFactor, passwordChange, passwordComplete, passwordForgot, passwordSent, registrationComplete,
                          registrationSend, registrationVerify);
    }

    public void normalize() {
      emailComplete = Normalizer.trimToNull(emailComplete);
      emailSend = Normalizer.trimToNull(emailSend);
      emailVerify = Normalizer.trimToNull(emailVerify);
      helpers = Normalizer.trimToNull(helpers);
      oauth2Authorize = Normalizer.trimToNull(oauth2Authorize);
      oauth2ChildRegistrationNotAllowed = Normalizer.trimToNull(oauth2ChildRegistrationNotAllowed);
      oauth2ChildRegistrationNotAllowedComplete = Normalizer.trimToNull(oauth2ChildRegistrationNotAllowedComplete);
      oauth2CompleteRegistration = Normalizer.trimToNull(oauth2CompleteRegistration);
      oauth2Error = Normalizer.trimToNull(oauth2Error);
      oauth2Passwordless = Normalizer.trimToNull(oauth2Passwordless);
      oauth2Register = Normalizer.trimToNull(oauth2Register);
      oauth2TwoFactor = Normalizer.trimToNull(oauth2TwoFactor);
      passwordChange = Normalizer.trimToNull(passwordChange);
      passwordComplete = Normalizer.trimToNull(passwordComplete);
      passwordForgot = Normalizer.trimToNull(passwordForgot);
      passwordSent = Normalizer.trimToNull(passwordSent);
      registrationComplete = Normalizer.trimToNull(registrationComplete);
      registrationSend = Normalizer.trimToNull(registrationSend);
      registrationVerify = Normalizer.trimToNull(registrationVerify);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
