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

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Derek Klatt
 */
public class PasswordValidationRules implements Buildable<PasswordValidationRules> {
  public PasswordBreachDetection breachDetection = new PasswordBreachDetection();

  public int maxLength = 256;

  public int minLength;

  public RememberPreviousPasswords rememberPreviousPasswords = new RememberPreviousPasswords();

  // Require mixed case
  public boolean requireMixedCase;

  // Require non-alphanumeric
  public boolean requireNonAlpha;

  // Require a number
  public boolean requireNumber;

  public boolean validateOnLogin;

  @JacksonConstructor
  public PasswordValidationRules() {
    this.minLength = FIPS.minimumPasswordLength();
  }

  public PasswordValidationRules(PasswordValidationRules other) {
    this.breachDetection = new PasswordBreachDetection(other.breachDetection);
    this.maxLength = other.maxLength;
    this.minLength = other.minLength;
    this.rememberPreviousPasswords = new RememberPreviousPasswords(other.rememberPreviousPasswords);
    this.requireMixedCase = other.requireMixedCase;
    this.requireNonAlpha = other.requireNonAlpha;
    this.requireNumber = other.requireNumber;
    this.validateOnLogin = other.validateOnLogin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PasswordValidationRules)) {
      return false;
    }
    PasswordValidationRules that = (PasswordValidationRules) o;
    return maxLength == that.maxLength &&
           minLength == that.minLength &&
           requireMixedCase == that.requireMixedCase &&
           requireNonAlpha == that.requireNonAlpha &&
           requireNumber == that.requireNumber &&
           Objects.equals(breachDetection, that.breachDetection) &&
           Objects.equals(rememberPreviousPasswords, that.rememberPreviousPasswords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(breachDetection, maxLength, minLength, rememberPreviousPasswords, requireMixedCase, requireNonAlpha, requireNumber, validateOnLogin);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
