/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.oauth2.OAuth2Configuration;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Seth Musselman
 */
public class Application implements Buildable<Application>, _InternalJSONColumn {
  public static final UUID FUSIONAUTH_APP_ID = UUID.fromString("3c219e58-ed0e-4b18-ad48-f4f92793ae32");

  public boolean active;

  @InternalJSONColumn
  public AuthenticationTokenConfiguration authenticationTokenConfiguration = new AuthenticationTokenConfiguration();

  @InternalJSONColumn
  public CleanSpeakConfiguration cleanSpeakConfiguration;

  public Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  @InternalJSONColumn
  public JWTConfiguration jwtConfiguration = new JWTConfiguration();

  public LambdaConfiguration lambdaConfiguration = new LambdaConfiguration();

  @InternalJSONColumn
  public LoginConfiguration loginConfiguration = new LoginConfiguration();

  public String name;

  @InternalJSONColumn
  public OAuth2Configuration oauthConfiguration = new OAuth2Configuration();

  @InternalJSONColumn
  public PasswordlessConfiguration passwordlessConfiguration = new PasswordlessConfiguration();

  @InternalJSONColumn
  public RegistrationConfiguration registrationConfiguration = new RegistrationConfiguration();

  public List<ApplicationRole> roles = new ArrayList<>();

  @InternalJSONColumn
  public SAMLv2Configuration samlv2Configuration = new SAMLv2Configuration();

  public UUID tenantId;

  public UUID verificationEmailTemplateId;

  @InternalJSONColumn
  public boolean verifyRegistration;

  public Application() {
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
    return active == that.active &&
        verifyRegistration == that.verifyRegistration &&
        Objects.equals(authenticationTokenConfiguration, that.authenticationTokenConfiguration) &&
        Objects.equals(cleanSpeakConfiguration, that.cleanSpeakConfiguration) &&
        Objects.equals(data, that.data) &&
        Objects.equals(jwtConfiguration, that.jwtConfiguration) &&
        Objects.equals(lambdaConfiguration, that.lambdaConfiguration) &&
        Objects.equals(loginConfiguration, that.loginConfiguration) &&
        Objects.equals(name, that.name) &&
        Objects.equals(oauthConfiguration, that.oauthConfiguration) &&
        Objects.equals(passwordlessConfiguration, that.passwordlessConfiguration) &&
        Objects.equals(roles, that.roles) &&
        Objects.equals(tenantId, that.tenantId) &&
        Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId);
  }

  public ApplicationRole getRole(String name) {
    for (ApplicationRole role : roles) {
      if (role.name.equals(name)) {
        return role;
      }
    }

    return null;
  }

  public boolean hasDefaultRole() {
    return roles.size() > 0 && roles.stream().anyMatch(r -> r.isDefault);
  }

  @Override
  public int hashCode() {
    return Objects.hash(active, authenticationTokenConfiguration, cleanSpeakConfiguration, data, jwtConfiguration, lambdaConfiguration, loginConfiguration, name, oauthConfiguration,
                        passwordlessConfiguration, roles, tenantId, verificationEmailTemplateId, verifyRegistration);
  }

  public void normalize() {
    name = trim(name);

    if (cleanSpeakConfiguration != null) {
      cleanSpeakConfiguration.normalize();
    }

    if (oauthConfiguration != null) {
      oauthConfiguration.normalize();
    }

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

  public static class AuthenticationTokenConfiguration extends Enableable {

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

    public Requirable fullName = new Requirable();

    public Requirable lastName = new Requirable();

    public LoginIdType loginIdType = LoginIdType.email;

    public Requirable middleName = new Requirable();

    public Requirable mobilePhone = new Requirable();

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
          Objects.equals(fullName, that.fullName) &&
          Objects.equals(lastName, that.lastName) &&
          loginIdType == that.loginIdType &&
          Objects.equals(middleName, that.middleName) &&
          Objects.equals(mobilePhone, that.mobilePhone);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), birthDate, confirmPassword, firstName, fullName, lastName, loginIdType, middleName, mobilePhone);
    }

    public String toString() {
      return ToString.toString(this);
    }

    public enum LoginIdType {
      email,
      username
    }
  }

  public static class SAMLv2Configuration extends Enableable implements Buildable<SAMLv2Configuration> {
    public String audience;

    public URI callbackURL;

    public boolean debug;

    public String issuer;

    public UUID keyId;

    public URI logoutURL;

    public CanonicalizationMethod xmlSignatureC14nMethod = CanonicalizationMethod.exclusive_with_comments;

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
          Objects.equals(callbackURL, that.callbackURL) &&
          Objects.equals(debug, that.debug) &&
          Objects.equals(issuer, that.issuer) &&
          Objects.equals(keyId, that.keyId) &&
          Objects.equals(logoutURL, that.logoutURL) &&
          Objects.equals(xmlSignatureC14nMethod, that.xmlSignatureC14nMethod);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), audience, callbackURL, debug, issuer, keyId, logoutURL, xmlSignatureC14nMethod);
    }

    public enum CanonicalizationMethod {
      exclusive(javax.xml.crypto.dsig.CanonicalizationMethod.EXCLUSIVE),

      exclusive_with_comments(javax.xml.crypto.dsig.CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS),

      inclusive(javax.xml.crypto.dsig.CanonicalizationMethod.INCLUSIVE),

      inclusive_with_comments(javax.xml.crypto.dsig.CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS);

      private String uri;

      CanonicalizationMethod(String uri) {
        this.uri = uri;
      }

      public String getURI() {
        return uri;
      }
    }
  }
}
