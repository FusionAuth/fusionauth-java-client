/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;
import io.fusionauth.domain.connector.ConnectorPolicy;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Daniel DeGroff
 */
public class Tenant implements Buildable<Tenant> {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public TenantAccessControlConfiguration accessControlConfiguration = new TenantAccessControlConfiguration();

  public TenantCaptchaConfiguration captchaConfiguration = new TenantCaptchaConfiguration();

  public boolean configured;

  public List<ConnectorPolicy> connectorPolicies = new ArrayList<>();

  public EmailConfiguration emailConfiguration = new EmailConfiguration();

  public EventConfiguration eventConfiguration = new EventConfiguration();

  public ExternalIdentifierConfiguration externalIdentifierConfiguration = new ExternalIdentifierConfiguration();

  public FailedAuthenticationConfiguration failedAuthenticationConfiguration = new FailedAuthenticationConfiguration();

  public FamilyConfiguration familyConfiguration = new FamilyConfiguration();

  public TenantFormConfiguration formConfiguration = new TenantFormConfiguration();

  /**
   * Time in seconds until an inactive session will be invalidated. Used when creating a new session in the FusionAuth
   * OAuth frontend.
   * <p>
   * Default is 60 minutes.
   */
  public int httpSessionMaxInactiveInterval = 3600;

  public UUID id;

  public ZonedDateTime insertInstant;

  public String issuer;

  @JsonIgnoreProperties("enabled")
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  public TenantLambdaConfiguration lambdaConfiguration = new TenantLambdaConfiguration();

  public ZonedDateTime lastUpdateInstant;

  public TenantLoginConfiguration loginConfiguration = new TenantLoginConfiguration();

  /**
   * Logout redirect URL when calling the <code>/oauth2/logout</code> endpoint. If this the
   * <code>Application.oauthConfiguration.logoutURL</code> is defined it will be used instead.
   */
  public URI logoutURL;

  public MaximumPasswordAge maximumPasswordAge = new MaximumPasswordAge();

  public MinimumPasswordAge minimumPasswordAge = new MinimumPasswordAge();

  public TenantMultiFactorConfiguration multiFactorConfiguration = new TenantMultiFactorConfiguration();

  public String name;

  public TenantOAuth2Configuration oauthConfiguration = new TenantOAuth2Configuration();

  public PasswordEncryptionConfiguration passwordEncryptionConfiguration = new PasswordEncryptionConfiguration();

  public TenantPasswordlessSMSMethod passwordlessSMSMethod = new TenantPasswordlessSMSMethod();

  public PasswordValidationRules passwordValidationRules = new PasswordValidationRules();

  public TenantRateLimitConfiguration rateLimitConfiguration = new TenantRateLimitConfiguration();

  public TenantRegistrationConfiguration registrationConfiguration = new TenantRegistrationConfiguration();

  public TenantSCIMServerConfiguration scimServerConfiguration = new TenantSCIMServerConfiguration();

  public SMSConfiguration smsConfiguration = new SMSConfiguration();

  public TenantSSOConfiguration ssoConfiguration = new TenantSSOConfiguration();

  public ObjectState state;

  public UUID themeId;

  public TenantUserDeletePolicy userDeletePolicy = new TenantUserDeletePolicy();

  public TenantUsernameConfiguration usernameConfiguration = new TenantUsernameConfiguration();

  public TenantWebAuthnConfiguration webAuthnConfiguration = new TenantWebAuthnConfiguration();

  @JacksonConstructor
  public Tenant() {
  }

  public Tenant(Tenant other) {
    this.captchaConfiguration = new TenantCaptchaConfiguration(other.captchaConfiguration);
    this.configured = other.configured;
    this.connectorPolicies.addAll(other.connectorPolicies.stream().map(ConnectorPolicy::new).collect(Collectors.toList()));
    this.data.putAll(other.data);
    this.emailConfiguration = new EmailConfiguration(other.emailConfiguration);
    this.smsConfiguration = new SMSConfiguration(other.smsConfiguration);
    this.eventConfiguration = new EventConfiguration(other.eventConfiguration);
    this.externalIdentifierConfiguration = new ExternalIdentifierConfiguration(other.externalIdentifierConfiguration);
    this.failedAuthenticationConfiguration = new FailedAuthenticationConfiguration(other.failedAuthenticationConfiguration);
    this.familyConfiguration = new FamilyConfiguration(other.familyConfiguration);
    this.formConfiguration = new TenantFormConfiguration(other.formConfiguration);
    this.httpSessionMaxInactiveInterval = other.httpSessionMaxInactiveInterval;
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.accessControlConfiguration = new TenantAccessControlConfiguration(other.accessControlConfiguration);
    this.issuer = other.issuer;
    this.jwtConfiguration = new JWTConfiguration(other.jwtConfiguration);
    this.lambdaConfiguration = new TenantLambdaConfiguration(other.lambdaConfiguration);
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.loginConfiguration = new TenantLoginConfiguration(other.loginConfiguration);
    this.logoutURL = other.logoutURL;
    this.maximumPasswordAge = new MaximumPasswordAge(other.maximumPasswordAge);
    this.minimumPasswordAge = new MinimumPasswordAge(other.minimumPasswordAge);
    this.multiFactorConfiguration = new TenantMultiFactorConfiguration(other.multiFactorConfiguration);
    this.name = other.name;
    this.oauthConfiguration = new TenantOAuth2Configuration(other.oauthConfiguration);
    this.passwordEncryptionConfiguration = new PasswordEncryptionConfiguration(other.passwordEncryptionConfiguration);
    this.passwordValidationRules = new PasswordValidationRules(other.passwordValidationRules);
    this.passwordlessSMSMethod = new TenantPasswordlessSMSMethod(other.passwordlessSMSMethod);
    this.rateLimitConfiguration = new TenantRateLimitConfiguration(other.rateLimitConfiguration);
    this.registrationConfiguration = new TenantRegistrationConfiguration(other.registrationConfiguration);
    this.scimServerConfiguration = new TenantSCIMServerConfiguration(other.scimServerConfiguration);
    this.ssoConfiguration = new TenantSSOConfiguration(other.ssoConfiguration);
    this.state = other.state;
    this.themeId = other.themeId;
    this.userDeletePolicy = new TenantUserDeletePolicy(other.userDeletePolicy);
    this.usernameConfiguration = new TenantUsernameConfiguration(other.usernameConfiguration);
    this.webAuthnConfiguration = new TenantWebAuthnConfiguration(other.webAuthnConfiguration);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tenant)) {
      return false;
    }
    Tenant tenant = (Tenant) o;
    return configured == tenant.configured &&
           httpSessionMaxInactiveInterval == tenant.httpSessionMaxInactiveInterval &&
           Objects.equals(captchaConfiguration, tenant.captchaConfiguration) &&
           Objects.equals(connectorPolicies, tenant.connectorPolicies) &&
           Objects.equals(data, tenant.data) &&
           Objects.equals(emailConfiguration, tenant.emailConfiguration) &&
           Objects.equals(smsConfiguration, tenant.smsConfiguration) &&
           Objects.equals(eventConfiguration, tenant.eventConfiguration) &&
           Objects.equals(externalIdentifierConfiguration, tenant.externalIdentifierConfiguration) &&
           Objects.equals(failedAuthenticationConfiguration, tenant.failedAuthenticationConfiguration) &&
           Objects.equals(familyConfiguration, tenant.familyConfiguration) &&
           Objects.equals(formConfiguration, tenant.formConfiguration) &&
           Objects.equals(id, tenant.id) &&
           Objects.equals(insertInstant, tenant.insertInstant) &&
           Objects.equals(accessControlConfiguration, tenant.accessControlConfiguration) &&
           Objects.equals(issuer, tenant.issuer) &&
           Objects.equals(jwtConfiguration, tenant.jwtConfiguration) &&
           Objects.equals(lambdaConfiguration, tenant.lambdaConfiguration) &&
           Objects.equals(lastUpdateInstant, tenant.lastUpdateInstant) &&
           Objects.equals(loginConfiguration, tenant.loginConfiguration) &&
           Objects.equals(logoutURL, tenant.logoutURL) &&
           Objects.equals(maximumPasswordAge, tenant.maximumPasswordAge) &&
           Objects.equals(minimumPasswordAge, tenant.minimumPasswordAge) &&
           Objects.equals(multiFactorConfiguration, tenant.multiFactorConfiguration) &&
           Objects.equals(name, tenant.name) &&
           Objects.equals(passwordEncryptionConfiguration, tenant.passwordEncryptionConfiguration) &&
           Objects.equals(passwordValidationRules, tenant.passwordValidationRules) &&
           Objects.equals(passwordlessSMSMethod, tenant.passwordlessSMSMethod) &&
           Objects.equals(rateLimitConfiguration, tenant.rateLimitConfiguration) &&
           Objects.equals(registrationConfiguration, tenant.registrationConfiguration) &&
           Objects.equals(scimServerConfiguration, tenant.scimServerConfiguration) &&
           Objects.equals(ssoConfiguration, tenant.ssoConfiguration) &&
           Objects.equals(state, tenant.state) &&
           Objects.equals(themeId, tenant.themeId) &&
           Objects.equals(userDeletePolicy, tenant.userDeletePolicy) &&
           Objects.equals(usernameConfiguration, tenant.usernameConfiguration) &&
           Objects.equals(webAuthnConfiguration, tenant.webAuthnConfiguration);
  }

  @JsonIgnore
  public ConnectorPolicy getPolicyByConnectorId(UUID connectorId) {
    return connectorPolicies.stream().filter(policy -> policy.connectorId.equals(connectorId)).findFirst().orElse(null);
  }

  @Override
  public int hashCode() {
    return Objects.hash(captchaConfiguration,
                        configured,
                        connectorPolicies,
                        data,
                        emailConfiguration,
                        smsConfiguration,
                        eventConfiguration,
                        externalIdentifierConfiguration,
                        failedAuthenticationConfiguration,
                        familyConfiguration,
                        formConfiguration,
                        httpSessionMaxInactiveInterval,
                        id,
                        insertInstant,
                        accessControlConfiguration,
                        issuer,
                        jwtConfiguration,
                        lambdaConfiguration,
                        lastUpdateInstant,
                        loginConfiguration,
                        logoutURL,
                        maximumPasswordAge,
                        minimumPasswordAge,
                        name,
                        passwordEncryptionConfiguration,
                        passwordValidationRules,
                        passwordlessSMSMethod,
                        rateLimitConfiguration,
                        registrationConfiguration,
                        scimServerConfiguration,
                        state,
                        themeId,
                        userDeletePolicy,
                        usernameConfiguration,
                        webAuthnConfiguration);
  }

  @JsonIgnore
  public JWTConfiguration lookupJWTConfiguration(Application application) {
    if (application != null && application.jwtConfiguration != null && application.jwtConfiguration.enabled) {
      return application.jwtConfiguration;
    }

    return jwtConfiguration;
  }

  @JsonIgnore
  public MultiFactorLoginPolicy lookupMultiFactorLoginPolicy(Application application) {
    if (application != null && application.multiFactorConfiguration.loginPolicy != null) {
      return application.multiFactorConfiguration.loginPolicy;
    }

    return multiFactorConfiguration.loginPolicy;
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
    eventConfiguration.normalize();

    // Lower case any domain entries
    connectorPolicies.forEach(policy -> Normalizer.toLowerCase(policy.domains, HashSet::new));

    // Always have the FusionAuth Connector if the policies are empty (default) or ensure that the FusionAuth policy is migrate=false and '*' domains
    if (connectorPolicies.isEmpty()) {
      connectorPolicies.add(new ConnectorPolicy().with(cp -> cp.connectorId = BaseConnectorConfiguration.FUSIONAUTH_CONNECTOR_ID)
                                                 .with(cp -> cp.domains.add("*")));
    } else {
      connectorPolicies.stream()
                       .filter(cp -> cp.connectorId.equals(BaseConnectorConfiguration.FUSIONAUTH_CONNECTOR_ID))
                       .forEach(cp -> cp.with(cpInner -> cpInner.domains.clear())
                                        .with(cpInner -> cpInner.domains.add("*"))
                                        .with(cpInner -> cpInner.migrate = false));
    }

    familyConfiguration.normalize();
  }

  public Tenant secure() {
    this.emailConfiguration.password = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class TenantOAuth2Configuration {
    public UUID clientCredentialsAccessTokenPopulateLambdaId;

    public TenantOAuth2Configuration() {
    }

    public TenantOAuth2Configuration(TenantOAuth2Configuration other) {
      this.clientCredentialsAccessTokenPopulateLambdaId = other.clientCredentialsAccessTokenPopulateLambdaId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TenantOAuth2Configuration)) {
        return false;
      }
      TenantOAuth2Configuration that = (TenantOAuth2Configuration) o;
      return Objects.equals(clientCredentialsAccessTokenPopulateLambdaId, that.clientCredentialsAccessTokenPopulateLambdaId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(clientCredentialsAccessTokenPopulateLambdaId);
    }

    public String toString() {
      return ToString.toString(this);
    }
  }
}
