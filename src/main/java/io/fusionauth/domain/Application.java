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

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.oauth2.OAuth2Configuration;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Seth Musselman
 */
public class Application implements Buildable<Application>, _InternalJSONColumn, Tenantable {
  public static final UUID FUSIONAUTH_APP_ID = UUID.fromString("3c219e58-ed0e-4b18-ad48-f4f92793ae32");

  /**
   * @deprecated prefer the use of {@link #state}.
   */
  @Deprecated
  public boolean active;

  @InternalJSONColumn
  public AuthenticationTokenConfiguration authenticationTokenConfiguration = new AuthenticationTokenConfiguration();

  @InternalJSONColumn
  public CleanSpeakConfiguration cleanSpeakConfiguration;

  public Map<String, Object> data = new LinkedHashMap<>();

  public ApplicationEmailConfiguration emailConfiguration = new ApplicationEmailConfiguration();

  @InternalJSONColumn
  public ApplicationFormConfiguration formConfiguration = new ApplicationFormConfiguration();

  public UUID id;

  public ZonedDateTime insertInstant;

  @InternalJSONColumn
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  public LambdaConfiguration lambdaConfiguration = new LambdaConfiguration();

  public ZonedDateTime lastUpdateInstant;

  @InternalJSONColumn
  public LoginConfiguration loginConfiguration = new LoginConfiguration();

  public String name;

  @InternalJSONColumn
  public OAuth2Configuration oauthConfiguration = new OAuth2Configuration();

  @InternalJSONColumn
  public PasswordlessConfiguration passwordlessConfiguration = new PasswordlessConfiguration();

  @InternalJSONColumn
  public RegistrationConfiguration registrationConfiguration = new RegistrationConfiguration();

  @InternalJSONColumn
  public ApplicationRegistrationDeletePolicy registrationDeletePolicy = new ApplicationRegistrationDeletePolicy();

  public List<ApplicationRole> roles = new ArrayList<>();

  @InternalJSONColumn
  public SAMLv2Configuration samlv2Configuration = new SAMLv2Configuration();

  @InternalJSONColumn
  public ObjectState state;

  public UUID tenantId;

  public UUID verificationEmailTemplateId;

  @InternalJSONColumn
  public boolean verifyRegistration;

  @JacksonConstructor
  public Application() {
  }

  public Application(Application other) {
    this.active = other.active;
    this.authenticationTokenConfiguration = new AuthenticationTokenConfiguration(other.authenticationTokenConfiguration);
    if (other.cleanSpeakConfiguration != null) {
      this.cleanSpeakConfiguration = new CleanSpeakConfiguration(other.cleanSpeakConfiguration);
    }
    this.data.putAll(other.data);
    this.emailConfiguration = new ApplicationEmailConfiguration(other.emailConfiguration);
    this.formConfiguration = new ApplicationFormConfiguration(other.formConfiguration);
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.jwtConfiguration = new JWTConfiguration(other.jwtConfiguration);
    this.lambdaConfiguration = new LambdaConfiguration(other.lambdaConfiguration);
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.loginConfiguration = new LoginConfiguration(other.loginConfiguration);
    this.name = other.name;
    this.oauthConfiguration = new OAuth2Configuration(other.oauthConfiguration);
    this.passwordlessConfiguration = new PasswordlessConfiguration(other.passwordlessConfiguration);
    this.registrationConfiguration = new RegistrationConfiguration(other.registrationConfiguration);
    this.registrationDeletePolicy = new ApplicationRegistrationDeletePolicy(other.registrationDeletePolicy);
    this.roles.addAll(other.roles.stream().map(ApplicationRole::new).collect(Collectors.toList()));
    this.samlv2Configuration = new SAMLv2Configuration(other.samlv2Configuration);
    this.state = other.state;
    this.tenantId = other.tenantId;
    this.verificationEmailTemplateId = other.verificationEmailTemplateId;
    this.verifyRegistration = other.verifyRegistration;
  }

  public Application(String name) {
    this.name = name;
  }

  public Application(UUID id, String name, boolean active, CleanSpeakConfiguration cleanSpeakConfiguration,
                     ApplicationRole... roles) {
    this.id = id;
    this.name = name;
    this.active = active;
    this.cleanSpeakConfiguration = cleanSpeakConfiguration;
    Collections.addAll(this.roles, roles);
  }

  public Application(UUID id, String name, boolean active, CleanSpeakConfiguration cleanSpeakConfiguration,
                     OAuth2Configuration oAuthConfiguration, ApplicationRole... roles) {
    this.id = id;
    this.name = name;
    this.active = active;
    this.cleanSpeakConfiguration = cleanSpeakConfiguration;
    this.oauthConfiguration = oAuthConfiguration;
    Collections.addAll(this.roles, roles);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Application)) {
      return false;
    }
    Application that = (Application) o;
    return verifyRegistration == that.verifyRegistration &&
           Objects.equals(authenticationTokenConfiguration, that.authenticationTokenConfiguration) &&
           Objects.equals(cleanSpeakConfiguration, that.cleanSpeakConfiguration) &&
           Objects.equals(data, that.data) &&
           Objects.equals(formConfiguration, that.formConfiguration) &&
           Objects.equals(id, that.id) &&
           Objects.equals(jwtConfiguration, that.jwtConfiguration) &&
           Objects.equals(lambdaConfiguration, that.lambdaConfiguration) &&
           Objects.equals(loginConfiguration, that.loginConfiguration) &&
           Objects.equals(name, that.name) &&
           Objects.equals(oauthConfiguration, that.oauthConfiguration) &&
           Objects.equals(passwordlessConfiguration, that.passwordlessConfiguration) &&
           Objects.equals(registrationConfiguration, that.registrationConfiguration) &&
           Objects.equals(registrationDeletePolicy, that.registrationDeletePolicy) &&
           Objects.equals(roles, that.roles) &&
           Objects.equals(samlv2Configuration, that.samlv2Configuration) &&
           Objects.equals(state, that.state) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(tenantId, that.tenantId) &&
           Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId);
  }

  public boolean getActive() {
    return state == ObjectState.Active;
  }

  public void setActive(boolean active) {
    this.active = active;
    state = active ? ObjectState.Active : ObjectState.Inactive;
  }

  public ApplicationRole getRole(String name) {
    for (ApplicationRole role : roles) {
      if (role.name.equals(name)) {
        return role;
      }
    }

    return null;
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  public boolean hasDefaultRole() {
    return roles.size() > 0 && roles.stream().anyMatch(r -> r.isDefault);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authenticationTokenConfiguration, cleanSpeakConfiguration, data, id, formConfiguration, jwtConfiguration, lambdaConfiguration, loginConfiguration, name, oauthConfiguration, passwordlessConfiguration, registrationConfiguration, registrationDeletePolicy, roles, samlv2Configuration, state, insertInstant, lastUpdateInstant, tenantId, verificationEmailTemplateId, verifyRegistration);
  }

  public void normalize() {
    name = trim(name);

    if (cleanSpeakConfiguration != null) {
      cleanSpeakConfiguration.normalize();
    }

    if (oauthConfiguration != null) {
      oauthConfiguration.normalize();
    }

    // Null un-available JWT Configuration options
    jwtConfiguration.refreshTokenRevocationPolicy = null;

    roles.forEach(ApplicationRole::normalize);
  }

  public Application secure() {
    if (oauthConfiguration != null) {
      oauthConfiguration.clientSecret = null;
    }

    return this;
  }

  public Application sortRoles() {
    roles.sort(ApplicationRole::compareTo);
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class ApplicationEmailConfiguration implements Buildable<ApplicationEmailConfiguration> {
    public UUID emailVerificationEmailTemplateId;

    public UUID forgotPasswordEmailTemplateId;

    public UUID passwordlessEmailTemplateId;

    public UUID setPasswordEmailTemplateId;

    @JacksonConstructor
    public ApplicationEmailConfiguration() {
    }

    public ApplicationEmailConfiguration(ApplicationEmailConfiguration other) {
      this.emailVerificationEmailTemplateId = other.emailVerificationEmailTemplateId;
      this.forgotPasswordEmailTemplateId = other.forgotPasswordEmailTemplateId;
      this.passwordlessEmailTemplateId = other.passwordlessEmailTemplateId;
      this.setPasswordEmailTemplateId = other.setPasswordEmailTemplateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      ApplicationEmailConfiguration that = (ApplicationEmailConfiguration) o;
      return Objects.equals(emailVerificationEmailTemplateId, that.emailVerificationEmailTemplateId) &&
             Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
             Objects.equals(passwordlessEmailTemplateId, that.passwordlessEmailTemplateId) &&
             Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(emailVerificationEmailTemplateId, forgotPasswordEmailTemplateId, passwordlessEmailTemplateId, setPasswordEmailTemplateId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class AuthenticationTokenConfiguration extends Enableable {

    @JacksonConstructor
    public AuthenticationTokenConfiguration() {
    }

    public AuthenticationTokenConfiguration(AuthenticationTokenConfiguration other) {
      this.enabled = other.enabled;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      return super.equals(o);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class LambdaConfiguration {
    public UUID accessTokenPopulateId;

    public UUID idTokenPopulateId;

    public UUID samlv2PopulateId;

    @JacksonConstructor
    public LambdaConfiguration() {
    }

    public LambdaConfiguration(LambdaConfiguration other) {
      this.accessTokenPopulateId = other.accessTokenPopulateId;
      this.idTokenPopulateId = other.idTokenPopulateId;
      this.samlv2PopulateId = other.samlv2PopulateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof LambdaConfiguration)) {
        return false;
      }
      LambdaConfiguration that = (LambdaConfiguration) o;
      return Objects.equals(accessTokenPopulateId, that.accessTokenPopulateId) &&
             Objects.equals(idTokenPopulateId, that.idTokenPopulateId) &&
             Objects.equals(samlv2PopulateId, that.samlv2PopulateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(accessTokenPopulateId, idTokenPopulateId, samlv2PopulateId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class LoginConfiguration implements Buildable<LoginConfiguration> {
    public boolean allowTokenRefresh;

    public boolean generateRefreshTokens;

    public boolean requireAuthentication = true;

    @JacksonConstructor
    public LoginConfiguration() {
    }

    public LoginConfiguration(LoginConfiguration other) {
      this.allowTokenRefresh = other.allowTokenRefresh;
      this.generateRefreshTokens = other.generateRefreshTokens;
      this.requireAuthentication = other.requireAuthentication;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof LoginConfiguration)) {
        return false;
      }
      LoginConfiguration that = (LoginConfiguration) o;
      return requireAuthentication == that.requireAuthentication &&
             allowTokenRefresh == that.allowTokenRefresh &&
             generateRefreshTokens == that.generateRefreshTokens;
    }

    @Override
    public int hashCode() {
      return Objects.hash(requireAuthentication, allowTokenRefresh, generateRefreshTokens);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class PasswordlessConfiguration extends Enableable {
    @JacksonConstructor
    public PasswordlessConfiguration() {
    }

    public PasswordlessConfiguration(PasswordlessConfiguration other) {
      this.enabled = other.enabled;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      return super.equals(o);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class RegistrationConfiguration extends Enableable implements Buildable<RegistrationConfiguration> {
    public Requirable birthDate = new Requirable();

    public boolean confirmPassword;

    public Requirable firstName = new Requirable();

    public UUID formId;

    public Requirable fullName = new Requirable();

    public Requirable lastName = new Requirable();

    public LoginIdType loginIdType = LoginIdType.email;

    public Requirable middleName = new Requirable();

    public Requirable mobilePhone = new Requirable();

    public RegistrationType type;

    @JacksonConstructor
    public RegistrationConfiguration() {
    }

    public RegistrationConfiguration(RegistrationConfiguration other) {
      this.birthDate = new Requirable(other.birthDate);
      this.confirmPassword = other.confirmPassword;
      this.enabled = other.enabled;
      this.firstName = new Requirable(other.firstName);
      this.formId = other.formId;
      this.fullName = new Requirable(other.fullName);
      this.lastName = new Requirable(other.lastName);
      this.loginIdType = other.loginIdType;
      this.middleName = new Requirable(other.middleName);
      this.mobilePhone = new Requirable(other.mobilePhone);
      this.type = other.type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof RegistrationConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      RegistrationConfiguration that = (RegistrationConfiguration) o;
      return confirmPassword == that.confirmPassword &&
             Objects.equals(birthDate, that.birthDate) &&
             Objects.equals(firstName, that.firstName) &&
             Objects.equals(formId, that.formId) &&
             Objects.equals(fullName, that.fullName) &&
             Objects.equals(lastName, that.lastName) &&
             loginIdType == that.loginIdType &&
             Objects.equals(middleName, that.middleName) &&
             Objects.equals(mobilePhone, that.mobilePhone) &&
             type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), birthDate, confirmPassword, firstName, formId, fullName, lastName, loginIdType, middleName, mobilePhone, type);
    }

    public String toString() {
      return ToString.toString(this);
    }

    public enum LoginIdType {
      email,
      username
    }

    public enum RegistrationType {
      basic,
      advanced
    }
  }

  public static class SAMLv2Configuration extends Enableable implements Buildable<SAMLv2Configuration> {
    public String audience;

    @JsonMerge(OptBoolean.FALSE)
    public List<URI> authorizedRedirectURLs = new ArrayList<>();

    public boolean debug;

    // Default verification key to use for HTTP Redirect Bindings, and for POST Bindings when no key is found in request.
    @ExcludeFromDatabaseDataColumn
    public UUID defaultVerificationKeyId;

    public String issuer;

    // Key pair used to sign w/
    @ExcludeFromDatabaseDataColumn
    public UUID keyId;

    public SAMLv2Logout logout = new SAMLv2Logout();

    public URI logoutURL;

    public boolean requireSignedRequests;

    public CanonicalizationMethod xmlSignatureC14nMethod = CanonicalizationMethod.exclusive_with_comments;

    public XMLSignatureLocation xmlSignatureLocation = XMLSignatureLocation.Assertion;

    @JacksonConstructor
    public SAMLv2Configuration() {
    }

    public SAMLv2Configuration(SAMLv2Configuration other) {
      this.audience = other.audience;
      this.authorizedRedirectURLs.addAll(other.authorizedRedirectURLs);
      this.debug = other.debug;
      this.defaultVerificationKeyId = other.defaultVerificationKeyId;
      this.enabled = other.enabled;
      this.issuer = other.issuer;
      this.keyId = other.keyId;
      this.logoutURL = other.logoutURL;
      this.logout = new SAMLv2Logout(other.logout);
      this.requireSignedRequests = other.requireSignedRequests;
      this.xmlSignatureLocation = other.xmlSignatureLocation;
      this.xmlSignatureC14nMethod = other.xmlSignatureC14nMethod;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SAMLv2Configuration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      SAMLv2Configuration that = (SAMLv2Configuration) o;
      return Objects.equals(audience, that.audience) &&
             Objects.equals(authorizedRedirectURLs, that.authorizedRedirectURLs) &&
             Objects.equals(debug, that.debug) &&
             Objects.equals(defaultVerificationKeyId, that.defaultVerificationKeyId) &&
             Objects.equals(issuer, that.issuer) &&
             Objects.equals(keyId, that.keyId) &&
             Objects.equals(logoutURL, that.logoutURL) &&
             Objects.equals(requireSignedRequests, that.requireSignedRequests) &&
             Objects.equals(xmlSignatureLocation, that.xmlSignatureLocation) &&
             Objects.equals(xmlSignatureC14nMethod, that.xmlSignatureC14nMethod);
    }

    /**
     * @return the configured callback URL or null if not defined.
     * @deprecated Prefer the use of {{@link #authorizedRedirectURLs}}
     */
    @Deprecated
    public URI getCallbackURL() {
      if (authorizedRedirectURLs.isEmpty()) {
        return null;
      }

      return authorizedRedirectURLs.get(0);
    }

    /**
     * @param callbackURL the callback URL
     * @deprecated Prefer the use of {{@link #authorizedRedirectURLs}}
     */
    @Deprecated
    public void setCallbackURL(URI callbackURL) {
      if (!authorizedRedirectURLs.contains(callbackURL)) {
        authorizedRedirectURLs.add(callbackURL);
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), audience, authorizedRedirectURLs, debug, defaultVerificationKeyId, issuer, keyId, logoutURL, requireSignedRequests, xmlSignatureLocation, xmlSignatureC14nMethod);
    }

    public enum SAMLLogoutBehavior {
      AllParticipants,
      OnlyOriginator
    }

    public enum XMLSignatureLocation {
      Assertion,
      Response
    }

    public static class SAMLv2Logout {
      public SAMLLogoutBehavior behavior;

      // Default verification key to use for HTTP Redirect Bindings, and for POST Bindings when no key is found in request.
      @ExcludeFromDatabaseDataColumn
      public UUID defaultVerificationKeyId;

      @ExcludeFromDatabaseDataColumn
      public UUID keyId;

      public boolean requireSignedRequests;

      public SAMLv2SingleLogout singleLogout = new SAMLv2SingleLogout();

      public CanonicalizationMethod xmlSignatureC14nMethod = CanonicalizationMethod.exclusive_with_comments;

      public SAMLv2Logout(SAMLv2Logout other) {
        this.defaultVerificationKeyId = other.defaultVerificationKeyId;
        this.keyId = other.keyId;
        this.behavior = other.behavior;
        this.requireSignedRequests = other.requireSignedRequests;
        this.singleLogout = new SAMLv2SingleLogout(other.singleLogout);
        this.xmlSignatureC14nMethod = other.xmlSignatureC14nMethod;
      }

      @JacksonConstructor
      public SAMLv2Logout() {
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        SAMLv2Logout that = (SAMLv2Logout) o;
        return requireSignedRequests == that.requireSignedRequests && Objects.equals(defaultVerificationKeyId, that.defaultVerificationKeyId) && Objects.equals(keyId, that.keyId) && behavior == that.behavior && Objects.equals(singleLogout, that.singleLogout) && xmlSignatureC14nMethod == that.xmlSignatureC14nMethod;
      }

      @Override
      public int hashCode() {
        return Objects.hash(defaultVerificationKeyId, keyId, behavior, requireSignedRequests, singleLogout, xmlSignatureC14nMethod);
      }

      @Override
      public String toString() {
        return ToString.toString(this);
      }
    }

    public static class SAMLv2SingleLogout extends Enableable {
      // Key pair used to sign w/
      @ExcludeFromDatabaseDataColumn
      public UUID keyId;

      public URI url;

      public CanonicalizationMethod xmlSignatureC14nMethod = CanonicalizationMethod.exclusive_with_comments;

      public SAMLv2SingleLogout(SAMLv2SingleLogout other) {
        this.enabled = other.enabled;
        this.keyId = other.keyId;
        this.url = other.url;
        this.xmlSignatureC14nMethod = other.xmlSignatureC14nMethod;
      }

      @JacksonConstructor
      public SAMLv2SingleLogout() {
      }

      @Override
      public String toString() {
        return ToString.toString(this);
      }
    }
  }
}
