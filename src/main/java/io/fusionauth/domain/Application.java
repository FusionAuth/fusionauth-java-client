/*
 * Copyright (c) 2019-2025, FusionAuth, All Rights Reserved
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

import java.lang.reflect.Field;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.oauth2.OAuth2Configuration;
import io.fusionauth.domain.provider.LoginHintConfiguration;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Seth Musselman
 */
public class Application implements Buildable<Application>, Tenantable {
  public static final UUID FUSIONAUTH_APP_ID = UUID.fromString("3c219e58-ed0e-4b18-ad48-f4f92793ae32");

  public ApplicationAccessControlConfiguration accessControlConfiguration = new ApplicationAccessControlConfiguration();

  /**
   * @deprecated prefer the use of {@link #state}.
   */
  @Deprecated
  public boolean active;

  public AuthenticationTokenConfiguration authenticationTokenConfiguration = new AuthenticationTokenConfiguration();

  public CleanSpeakConfiguration cleanSpeakConfiguration;

  public Map<String, Object> data = new LinkedHashMap<>();

  public ApplicationEmailConfiguration emailConfiguration = new ApplicationEmailConfiguration();

  public ApplicationExternalIdentifierConfiguration externalIdentifierConfiguration = new ApplicationExternalIdentifierConfiguration();

  public ApplicationFormConfiguration formConfiguration = new ApplicationFormConfiguration();

  public UUID id;

  public ZonedDateTime insertInstant;

  @JsonIgnoreProperties("refreshTokenRevocationPolicy")
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  public LambdaConfiguration lambdaConfiguration = new LambdaConfiguration();

  public ZonedDateTime lastUpdateInstant;

  public LoginConfiguration loginConfiguration = new LoginConfiguration();

  public ApplicationMultiFactorConfiguration multiFactorConfiguration = new ApplicationMultiFactorConfiguration();

  public String name;

  public OAuth2Configuration oauthConfiguration = new OAuth2Configuration();

  public PasswordlessConfiguration passwordlessConfiguration = new PasswordlessConfiguration();

  public RegistrationConfiguration registrationConfiguration = new RegistrationConfiguration();

  public ApplicationRegistrationDeletePolicy registrationDeletePolicy = new ApplicationRegistrationDeletePolicy();

  public List<ApplicationRole> roles = new ArrayList<>();

  public SAMLv2Configuration samlv2Configuration = new SAMLv2Configuration();

  // Do not include the application Id for individual scopes when returning as part of the full application
  @JsonIgnoreProperties("applicationId")
  public List<ApplicationOAuthScope> scopes = new ArrayList<>();

  public ObjectState state;

  public UUID tenantId;

  public UUID themeId;

  public UniversalConfiguration universalConfiguration = new UniversalConfiguration();

  public RegistrationUnverifiedOptions unverified = new RegistrationUnverifiedOptions();

  public UUID verificationEmailTemplateId;

  public VerificationStrategy verificationStrategy;

  public boolean verifyRegistration;

  public ApplicationWebAuthnConfiguration webAuthnConfiguration = new ApplicationWebAuthnConfiguration();

  @JacksonConstructor
  public Application() {
  }

  public Application(Application other) {
    this.active = other.active;
    this.accessControlConfiguration = new ApplicationAccessControlConfiguration(other.accessControlConfiguration);
    this.authenticationTokenConfiguration = new AuthenticationTokenConfiguration(other.authenticationTokenConfiguration);
    if (other.cleanSpeakConfiguration != null) {
      this.cleanSpeakConfiguration = new CleanSpeakConfiguration(other.cleanSpeakConfiguration);
    }
    this.data.putAll(other.data);
    this.emailConfiguration = new ApplicationEmailConfiguration(other.emailConfiguration);
    this.externalIdentifierConfiguration = new ApplicationExternalIdentifierConfiguration(other.externalIdentifierConfiguration);
    this.formConfiguration = new ApplicationFormConfiguration(other.formConfiguration);
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.jwtConfiguration = new JWTConfiguration(other.jwtConfiguration);
    this.lambdaConfiguration = new LambdaConfiguration(other.lambdaConfiguration);
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.loginConfiguration = new LoginConfiguration(other.loginConfiguration);
    this.multiFactorConfiguration = new ApplicationMultiFactorConfiguration(other.multiFactorConfiguration);
    this.name = other.name;
    this.oauthConfiguration = new OAuth2Configuration(other.oauthConfiguration);
    this.passwordlessConfiguration = new PasswordlessConfiguration(other.passwordlessConfiguration);
    this.registrationConfiguration = new RegistrationConfiguration(other.registrationConfiguration);
    this.registrationDeletePolicy = new ApplicationRegistrationDeletePolicy(other.registrationDeletePolicy);
    this.roles.addAll(other.roles.stream().map(ApplicationRole::new).collect(Collectors.toList()));
    this.samlv2Configuration = new SAMLv2Configuration(other.samlv2Configuration);
    this.scopes.addAll(other.scopes.stream().map(ApplicationOAuthScope::new).collect(Collectors.toList()));
    this.state = other.state;
    this.tenantId = other.tenantId;
    this.themeId = other.themeId;
    this.universalConfiguration = new UniversalConfiguration(other.universalConfiguration);
    this.unverified = new RegistrationUnverifiedOptions(other.unverified);
    this.verificationEmailTemplateId = other.verificationEmailTemplateId;
    this.verificationStrategy = other.verificationStrategy;
    this.verifyRegistration = other.verifyRegistration;
    this.webAuthnConfiguration = new ApplicationWebAuthnConfiguration(other.webAuthnConfiguration);
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application that = (Application) o;
    // active is omitted
    return verifyRegistration == that.verifyRegistration &&
           Objects.equals(accessControlConfiguration, that.accessControlConfiguration) &&
           Objects.equals(authenticationTokenConfiguration, that.authenticationTokenConfiguration) &&
           Objects.equals(cleanSpeakConfiguration, that.cleanSpeakConfiguration) &&
           Objects.equals(data, that.data) &&
           Objects.equals(emailConfiguration, that.emailConfiguration) &&
           Objects.equals(externalIdentifierConfiguration, that.externalIdentifierConfiguration) &&
           Objects.equals(formConfiguration, that.formConfiguration) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(jwtConfiguration, that.jwtConfiguration) &&
           Objects.equals(lambdaConfiguration, that.lambdaConfiguration) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(loginConfiguration, that.loginConfiguration) &&
           Objects.equals(multiFactorConfiguration, that.multiFactorConfiguration) &&
           Objects.equals(name, that.name) &&
           Objects.equals(oauthConfiguration, that.oauthConfiguration) &&
           Objects.equals(passwordlessConfiguration, that.passwordlessConfiguration) &&
           Objects.equals(registrationConfiguration, that.registrationConfiguration) &&
           Objects.equals(registrationDeletePolicy, that.registrationDeletePolicy) &&
           Objects.equals(roles, that.roles) &&
           Objects.equals(samlv2Configuration, that.samlv2Configuration) &&
           Objects.equals(scopes, that.scopes) &&
           state == that.state &&
           Objects.equals(tenantId, that.tenantId) &&
           Objects.equals(themeId, that.themeId) &&
           Objects.equals(universalConfiguration, that.universalConfiguration) &&
           Objects.equals(unverified, that.unverified) &&
           Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId) &&
           Objects.equals(webAuthnConfiguration, that.webAuthnConfiguration) &&
           verificationStrategy == that.verificationStrategy;
  }

  public boolean getActive() {
    return state == ObjectState.Active;
  }

  public void setActive(boolean active) {
    this.active = active;
    state = active ? ObjectState.Active : ObjectState.Inactive;
  }

  public ApplicationOAuthScope getOAuthScope(String name) {
    return scopes.stream()
                 .filter(s -> s.name.equals(name))
                 .findFirst().orElse(null);
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
    // active is omitted
    return Objects.hash(accessControlConfiguration, authenticationTokenConfiguration, cleanSpeakConfiguration, data, emailConfiguration, externalIdentifierConfiguration, formConfiguration, id, insertInstant, jwtConfiguration, lambdaConfiguration, lastUpdateInstant, loginConfiguration, multiFactorConfiguration, name, oauthConfiguration, passwordlessConfiguration, registrationConfiguration, registrationDeletePolicy, roles, samlv2Configuration, scopes, state, tenantId, themeId, universalConfiguration, unverified, verificationEmailTemplateId, verificationStrategy, verifyRegistration, webAuthnConfiguration);
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

    scopes.forEach(ApplicationOAuthScope::normalize);

    if (multiFactorConfiguration.loginPolicy == null) {
      multiFactorConfiguration.trustPolicy = null;
    }
  }

  public Application secure() {
    if (oauthConfiguration != null) {
      oauthConfiguration.clientSecret = null;
    }

    return this;
  }

  public Application sortOAuthScopes() {
    scopes.sort(ApplicationOAuthScope::compareTo);
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
    @JsonIgnore
    @SuppressWarnings("unused")
    public static List<String> EmailTemplateIdFieldNames = Arrays.stream(ApplicationEmailConfiguration.class.getDeclaredFields())
                                                                 .map(Field::getName)
                                                                 .filter(name -> name.endsWith("EmailTemplateId"))
                                                                 .sorted()
                                                                 .collect(Collectors.toList());

    @JsonIgnore
    @SuppressWarnings("unused")
    public static List<Field> EmailTemplateIdFields = Arrays.stream(ApplicationEmailConfiguration.class.getDeclaredFields())
                                                            .filter(f -> f.getName().endsWith("EmailTemplateId"))
                                                            .sorted(Comparator.comparing(Field::getName))
                                                            .collect(Collectors.toList());

    public UUID emailUpdateEmailTemplateId;

    public UUID emailVerificationEmailTemplateId;

    public UUID emailVerifiedEmailTemplateId;

    public UUID forgotPasswordEmailTemplateId;

    public UUID loginIdInUseOnCreateEmailTemplateId;

    public UUID loginIdInUseOnUpdateEmailTemplateId;

    public UUID loginNewDeviceEmailTemplateId;

    public UUID loginSuspiciousEmailTemplateId;

    public UUID passwordResetSuccessEmailTemplateId;

    public UUID passwordUpdateEmailTemplateId;

    public UUID passwordlessEmailTemplateId;

    public UUID setPasswordEmailTemplateId;

    public UUID twoFactorMethodAddEmailTemplateId;

    public UUID twoFactorMethodRemoveEmailTemplateId;

    @JacksonConstructor
    public ApplicationEmailConfiguration() {
    }

    public ApplicationEmailConfiguration(ApplicationEmailConfiguration other) {
      this.emailUpdateEmailTemplateId = other.emailUpdateEmailTemplateId;
      this.emailVerificationEmailTemplateId = other.emailVerificationEmailTemplateId;
      this.emailVerifiedEmailTemplateId = other.emailVerifiedEmailTemplateId;
      this.forgotPasswordEmailTemplateId = other.forgotPasswordEmailTemplateId;
      this.loginIdInUseOnCreateEmailTemplateId = other.loginIdInUseOnCreateEmailTemplateId;
      this.loginIdInUseOnUpdateEmailTemplateId = other.loginIdInUseOnUpdateEmailTemplateId;
      this.loginNewDeviceEmailTemplateId = other.loginNewDeviceEmailTemplateId;
      this.loginSuspiciousEmailTemplateId = other.loginSuspiciousEmailTemplateId;
      this.passwordResetSuccessEmailTemplateId = other.passwordResetSuccessEmailTemplateId;
      this.passwordUpdateEmailTemplateId = other.passwordUpdateEmailTemplateId;
      this.passwordlessEmailTemplateId = other.passwordlessEmailTemplateId;
      this.setPasswordEmailTemplateId = other.setPasswordEmailTemplateId;
      this.twoFactorMethodAddEmailTemplateId = other.twoFactorMethodAddEmailTemplateId;
      this.twoFactorMethodRemoveEmailTemplateId = other.twoFactorMethodRemoveEmailTemplateId;
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
      return Objects.equals(emailUpdateEmailTemplateId, that.emailUpdateEmailTemplateId) &&
             Objects.equals(emailVerificationEmailTemplateId, that.emailVerificationEmailTemplateId) &&
             Objects.equals(emailVerifiedEmailTemplateId, that.emailVerifiedEmailTemplateId) &&
             Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
             Objects.equals(loginIdInUseOnCreateEmailTemplateId, that.loginIdInUseOnCreateEmailTemplateId) &&
             Objects.equals(loginIdInUseOnUpdateEmailTemplateId, that.loginIdInUseOnUpdateEmailTemplateId) &&
             Objects.equals(loginNewDeviceEmailTemplateId, that.loginNewDeviceEmailTemplateId) &&
             Objects.equals(loginSuspiciousEmailTemplateId, that.loginSuspiciousEmailTemplateId) &&
             Objects.equals(passwordResetSuccessEmailTemplateId, that.passwordResetSuccessEmailTemplateId) &&
             Objects.equals(passwordUpdateEmailTemplateId, that.passwordUpdateEmailTemplateId) &&
             Objects.equals(passwordlessEmailTemplateId, that.passwordlessEmailTemplateId) &&
             Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId) &&
             Objects.equals(twoFactorMethodAddEmailTemplateId, that.twoFactorMethodAddEmailTemplateId) &&
             Objects.equals(twoFactorMethodRemoveEmailTemplateId, that.twoFactorMethodRemoveEmailTemplateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(emailUpdateEmailTemplateId, emailVerificationEmailTemplateId, emailVerifiedEmailTemplateId, forgotPasswordEmailTemplateId,
                          loginIdInUseOnCreateEmailTemplateId, loginIdInUseOnUpdateEmailTemplateId, loginNewDeviceEmailTemplateId,
                          loginSuspiciousEmailTemplateId, passwordResetSuccessEmailTemplateId, passwordUpdateEmailTemplateId,
                          passwordlessEmailTemplateId, setPasswordEmailTemplateId, twoFactorMethodAddEmailTemplateId, twoFactorMethodRemoveEmailTemplateId);
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

    public UUID selfServiceRegistrationValidationId;

    public UUID userinfoPopulateId;

    @JacksonConstructor
    public LambdaConfiguration() {
    }

    public LambdaConfiguration(LambdaConfiguration other) {
      this.accessTokenPopulateId = other.accessTokenPopulateId;
      this.idTokenPopulateId = other.idTokenPopulateId;
      this.samlv2PopulateId = other.samlv2PopulateId;
      this.selfServiceRegistrationValidationId = other.selfServiceRegistrationValidationId;
      this.userinfoPopulateId = other.userinfoPopulateId;
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
             Objects.equals(samlv2PopulateId, that.samlv2PopulateId) &&
             Objects.equals(selfServiceRegistrationValidationId, that.selfServiceRegistrationValidationId) &&
             Objects.equals(userinfoPopulateId, that.userinfoPopulateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(accessTokenPopulateId, idTokenPopulateId, samlv2PopulateId, selfServiceRegistrationValidationId, userinfoPopulateId);
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
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      LoginConfiguration that = (LoginConfiguration) o;
      return allowTokenRefresh == that.allowTokenRefresh &&
             generateRefreshTokens == that.generateRefreshTokens &&
             requireAuthentication == that.requireAuthentication;
    }

    @Override
    public int hashCode() {
      return Objects.hash(allowTokenRefresh, generateRefreshTokens, requireAuthentication);
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

    public Requirable preferredLanguages = new Requirable();

    public RegistrationType type = RegistrationType.basic;

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
      this.preferredLanguages = new Requirable(other.preferredLanguages);
      this.type = other.type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
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
             Objects.equals(preferredLanguages, that.preferredLanguages) &&
             type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), birthDate, confirmPassword, firstName, formId, fullName, lastName, loginIdType, middleName, mobilePhone, preferredLanguages, type);
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
    public SAMLv2AssertionEncryptionConfiguration assertionEncryptionConfiguration = new SAMLv2AssertionEncryptionConfiguration();

    public String audience;

    @JsonMerge(OptBoolean.FALSE)
    public List<URI> authorizedRedirectURLs = new ArrayList<>();

    public boolean debug;

    // Default verification key to use for HTTP Redirect Bindings, and for POST Bindings when no key is found in request.
      public UUID defaultVerificationKeyId;

    public SAMLv2IdPInitiatedLoginConfiguration initiatedLogin = new SAMLv2IdPInitiatedLoginConfiguration();

    public String issuer;

    // Key pair used to sign w/
      public UUID keyId;

    public LoginHintConfiguration loginHintConfiguration = new LoginHintConfiguration(true);

    public SAMLv2Logout logout = new SAMLv2Logout();

    public URI logoutURL;

    public boolean requireSignedRequests;

    public CanonicalizationMethod xmlSignatureC14nMethod = CanonicalizationMethod.exclusive_with_comments;

    public XMLSignatureLocation xmlSignatureLocation = XMLSignatureLocation.Assertion;

    @JacksonConstructor
    public SAMLv2Configuration() {
    }

    public SAMLv2Configuration(SAMLv2Configuration other) {
      this.assertionEncryptionConfiguration = other.assertionEncryptionConfiguration;
      this.audience = other.audience;
      this.authorizedRedirectURLs.addAll(other.authorizedRedirectURLs);
      this.debug = other.debug;
      this.defaultVerificationKeyId = other.defaultVerificationKeyId;
      this.enabled = other.enabled;
      this.initiatedLogin = new SAMLv2IdPInitiatedLoginConfiguration(other.initiatedLogin);
      this.issuer = other.issuer;
      this.keyId = other.keyId;
      this.loginHintConfiguration = new LoginHintConfiguration(other.loginHintConfiguration);
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
      return Objects.equals(assertionEncryptionConfiguration, that.assertionEncryptionConfiguration) &&
             Objects.equals(audience, that.audience) &&
             Objects.equals(authorizedRedirectURLs, that.authorizedRedirectURLs) &&
             Objects.equals(debug, that.debug) &&
             Objects.equals(defaultVerificationKeyId, that.defaultVerificationKeyId) &&
             Objects.equals(initiatedLogin, that.initiatedLogin) &&
             Objects.equals(issuer, that.issuer) &&
             Objects.equals(keyId, that.keyId) &&
             Objects.equals(loginHintConfiguration, that.loginHintConfiguration) &&
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
      return Objects.hash(super.hashCode(), assertionEncryptionConfiguration, audience, authorizedRedirectURLs, debug, defaultVerificationKeyId, initiatedLogin, issuer, keyId, loginHintConfiguration, logoutURL, requireSignedRequests, xmlSignatureLocation, xmlSignatureC14nMethod);
    }

    public enum SAMLLogoutBehavior {
      AllParticipants,
      OnlyOriginator
    }

    public enum XMLSignatureLocation {
      Assertion,
      Response
    }

    public static class SAMLv2AssertionEncryptionConfiguration extends Enableable implements Buildable<SAMLv2AssertionEncryptionConfiguration> {
      public String digestAlgorithm = "SHA256";

      public String encryptionAlgorithm = "AES256GCM";

      public String keyLocation = "Child";

      public String keyTransportAlgorithm = "RSA_OAEP";

      public UUID keyTransportEncryptionKeyId;

      public String maskGenerationFunction = "MGF1_SHA1";

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        if (!super.equals(o)) {
          return false;
        }
        SAMLv2AssertionEncryptionConfiguration that = (SAMLv2AssertionEncryptionConfiguration) o;
        return Objects.equals(digestAlgorithm, that.digestAlgorithm) && Objects.equals(encryptionAlgorithm, that.encryptionAlgorithm) && Objects.equals(keyLocation, that.keyLocation) && Objects.equals(keyTransportAlgorithm, that.keyTransportAlgorithm) && Objects.equals(keyTransportEncryptionKeyId, that.keyTransportEncryptionKeyId) && Objects.equals(maskGenerationFunction, that.maskGenerationFunction);
      }

      @Override
      public int hashCode() {
        return Objects.hash(super.hashCode(), digestAlgorithm, encryptionAlgorithm, keyLocation, keyTransportAlgorithm, keyTransportEncryptionKeyId, maskGenerationFunction);
      }

      @Override
      public String toString() {
        return ToString.toString(this);
      }
    }

    public static class SAMLv2Logout {
      public SAMLLogoutBehavior behavior = SAMLLogoutBehavior.AllParticipants;

      // Default verification key to use for HTTP Redirect Bindings, and for POST Bindings when no key is found in request.
          public UUID defaultVerificationKeyId;

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

  public static class UniversalConfiguration {

    // This is a flag to indicate that all tenants can use this universal application
    public boolean global;

    // This is a flag to indicate that this application is universal and can be used by the configured application tenants
      public boolean universal;

    @JacksonConstructor
    public UniversalConfiguration() {
    }

    public UniversalConfiguration(UniversalConfiguration other) {
      this.global = other.global;
      this.universal = other.universal;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      UniversalConfiguration that = (UniversalConfiguration) o;
      return global == that.global && universal == that.universal;
    }

    @Override
    public int hashCode() {
      return Objects.hash(global, universal);
    }
  }
}
