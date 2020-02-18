/*
 * Copyright (c) 2018-2020, FusionAuth, All Rights Reserved
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Daniel DeGroff
 */
public class Tenant implements Buildable<Tenant>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public boolean configured;

  @InternalJSONColumn
  public EmailConfiguration emailConfiguration = new EmailConfiguration();

  @InternalJSONColumn
  public EventConfiguration eventConfiguration = new EventConfiguration();

  @InternalJSONColumn
  public ExternalIdentifierConfiguration externalIdentifierConfiguration = new ExternalIdentifierConfiguration();

  @InternalJSONColumn
  public FailedAuthenticationConfiguration failedAuthenticationConfiguration = new FailedAuthenticationConfiguration();

  @InternalJSONColumn
  public FamilyConfiguration familyConfiguration = new FamilyConfiguration();

  /**
   * Time in seconds until an inactive session will be invalidated. Used when creating a new session in the FusionAuth
   * OAuth frontend.
   * <p>
   * Default is 60 minutes.
   */
  @InternalJSONColumn
  public int httpSessionMaxInactiveInterval = 3600;

  public UUID id;

  @InternalJSONColumn
  public String issuer;

  @InternalJSONColumn
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  /**
   * Logout redirect URL when calling the <code>/oauth2/logout</code> endpoint. If this the
   * <code>Application.oauthConfiguration.logoutURL</code> is defined it will be used instead.
   */
  @InternalJSONColumn
  public URI logoutURL;

  @InternalJSONColumn
  public MaximumPasswordAge maximumPasswordAge = new MaximumPasswordAge();

  @InternalJSONColumn
  public MinimumPasswordAge minimumPasswordAge = new MinimumPasswordAge();

  public String name;

  @InternalJSONColumn
  public PasswordEncryptionConfiguration passwordEncryptionConfiguration = new PasswordEncryptionConfiguration();

  @InternalJSONColumn
  public PasswordValidationRules passwordValidationRules = new PasswordValidationRules();

  public UUID themeId;

  @InternalJSONColumn
  public TenantUserDeletePolicy userDeletePolicy = new TenantUserDeletePolicy();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tenant tenant = (Tenant) o;
    return configured == tenant.configured &&
        httpSessionMaxInactiveInterval == tenant.httpSessionMaxInactiveInterval &&
        Objects.equals(data, tenant.data) &&
        Objects.equals(emailConfiguration, tenant.emailConfiguration) &&
        Objects.equals(eventConfiguration, tenant.eventConfiguration) &&
        Objects.equals(externalIdentifierConfiguration, tenant.externalIdentifierConfiguration) &&
        Objects.equals(failedAuthenticationConfiguration, tenant.failedAuthenticationConfiguration) &&
        Objects.equals(familyConfiguration, tenant.familyConfiguration) &&
        Objects.equals(issuer, tenant.issuer) &&
        Objects.equals(jwtConfiguration, tenant.jwtConfiguration) &&
        Objects.equals(logoutURL, tenant.logoutURL) &&
        Objects.equals(maximumPasswordAge, tenant.maximumPasswordAge) &&
        Objects.equals(minimumPasswordAge, tenant.minimumPasswordAge) &&
        Objects.equals(name, tenant.name) &&
        Objects.equals(passwordEncryptionConfiguration, tenant.passwordEncryptionConfiguration) &&
        Objects.equals(passwordValidationRules, tenant.passwordValidationRules) &&
        Objects.equals(themeId, tenant.themeId) &&
        Objects.equals(userDeletePolicy, tenant.userDeletePolicy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, configured, emailConfiguration, eventConfiguration, externalIdentifierConfiguration,
                        failedAuthenticationConfiguration, familyConfiguration, httpSessionMaxInactiveInterval, issuer,
                        jwtConfiguration, logoutURL, maximumPasswordAge, minimumPasswordAge, name, passwordEncryptionConfiguration, passwordValidationRules, themeId, userDeletePolicy);
  }

  @JsonIgnore
  public JWTConfiguration lookupJWTConfiguration(Application application) {
    if (application != null && application.jwtConfiguration != null && application.jwtConfiguration.enabled) {
      return application.jwtConfiguration;
    }

    return jwtConfiguration;
  }

  public void normalize() {
    // Clear verification settings if they are disabled
    if (!emailConfiguration.verifyEmail) {
      emailConfiguration.verifyEmailWhenChanged = false;
      emailConfiguration.verificationEmailTemplateId = null;
    }

    Normalizer.removeEmpty(data);
    name = trim(name);
    emailConfiguration.normalize();
  }

  public Tenant secure() {
    this.emailConfiguration.password = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
