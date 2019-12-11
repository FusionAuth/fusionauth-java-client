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
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Brian Pontarelli
 */
public class EmailConfiguration implements Buildable<EmailConfiguration> {
  @ExcludeFromDatabaseDataColumn
  public UUID forgotPasswordEmailTemplateId;

  public String host;

  public String password;

  @ExcludeFromDatabaseDataColumn
  public UUID passwordlessEmailTemplateId;

  public Integer port;

  public String properties;

  public EmailSecurityType security;

  @ExcludeFromDatabaseDataColumn
  public UUID setPasswordEmailTemplateId;

  public String username;

  @ExcludeFromDatabaseDataColumn
  public UUID verificationEmailTemplateId;

  public boolean verifyEmail;

  public boolean verifyEmailWhenChanged;

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
        Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
        Objects.equals(host, that.host) &&
        Objects.equals(password, that.password) &&
        Objects.equals(passwordlessEmailTemplateId, that.passwordlessEmailTemplateId) &&
        Objects.equals(port, that.port) &&
        Objects.equals(properties, that.properties) &&
        security == that.security &&
        Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId) &&
        Objects.equals(username, that.username) &&
        Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(forgotPasswordEmailTemplateId, host, password, passwordlessEmailTemplateId, port, properties, security, setPasswordEmailTemplateId, username, verificationEmailTemplateId, verifyEmail, verifyEmailWhenChanged);
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