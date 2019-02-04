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
  public JWTConfiguration jwtConfiguration;

  public String name;

  @InternalJSONColumn
  public OAuth2Configuration oauthConfiguration = new OAuth2Configuration();

  @InternalJSONColumn
  public RegistrationConfiguration registrationConfiguration = new RegistrationConfiguration();

  public List<ApplicationRole> roles = new ArrayList<>();

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
        Objects.equals(name, that.name) &&
        Objects.equals(oauthConfiguration, that.oauthConfiguration) &&
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
    return Objects.hash(active, authenticationTokenConfiguration, cleanSpeakConfiguration, data, jwtConfiguration, name, oauthConfiguration,
                        roles, tenantId, verificationEmailTemplateId, verifyRegistration);
  }

  public void normalize() {
    name = trim(name);

    if (cleanSpeakConfiguration != null) {
      cleanSpeakConfiguration.normalize();
    }

    if (oauthConfiguration != null) {
      oauthConfiguration.normalize();
    }

    if (jwtConfiguration != null) {
      jwtConfiguration.normalize();
      // issuer only applies to the global JWT configuration.
      jwtConfiguration.issuer = null;
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

  public static class RegistrationConfiguration extends Enableable implements Buildable<RegistrationConfiguration> {
    public Requirable birthDate = new Requirable();

    public boolean confirmPassword;

    public Requirable firstName = new Requirable();

    public Requirable fullName = new Requirable();

    public Requirable lastName = new Requirable();

    public LoginIdType loginIdType = LoginIdType.email;

    public Requirable middleName = new Requirable();

    public Requirable mobilePhone = new Requirable();

    public enum LoginIdType {
      email,
      username
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
  }
}
