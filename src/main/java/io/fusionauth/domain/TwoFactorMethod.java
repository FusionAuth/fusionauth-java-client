/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
 * @author Daniel DeGroff
 */
public class TwoFactorMethod implements Buildable<TwoFactorMethod> {
  public static final String Authenticator = "authenticator";

  public static final String Email = "email";

  public static final String SMS = "sms";

  public AuthenticatorConfiguration authenticator;

  public String email;

  // email, mobilePhone & id
  // - email, sms and authenticator
  public String id;

  public Boolean lastUsed;

  public String method;

  public String mobilePhone;

  public String secret;

  @JacksonConstructor
  public TwoFactorMethod() {
  }

  public TwoFactorMethod(String method) {
    this.method = method;
  }

  public TwoFactorMethod(TwoFactorMethod other) {
    if (other.authenticator != null) {
      this.authenticator = new AuthenticatorConfiguration(other.authenticator);
    }
    this.email = other.email;
    this.id = other.id;
    this.lastUsed = other.lastUsed;
    this.method = other.method;
    this.mobilePhone = other.mobilePhone;
    this.secret = other.secret;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TwoFactorMethod that = (TwoFactorMethod) o;
    return Objects.equals(authenticator, that.authenticator) && Objects.equals(email, that.email) && Objects.equals(id, that.id) && Objects.equals(lastUsed, that.lastUsed) && Objects.equals(method, that.method) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(secret, that.secret);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authenticator, email, id, lastUsed, method, mobilePhone, secret);
  }

  public void normalize() {
    if (method != null) {
      switch (method) {
        case TwoFactorMethod.Authenticator:
          email = null;
          mobilePhone = null;
          break;
        case TwoFactorMethod.Email:
          mobilePhone = null;
          secret = null;
          break;
        case TwoFactorMethod.SMS:
          email = null;
          secret = null;
          break;
      }
    }
  }

  public TwoFactorMethod secure() {
    secret = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
