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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class Tenant implements Buildable<Tenant>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public TenantEmailConfiguration emailConfiguration = new TenantEmailConfiguration();

  @InternalJSONColumn
  public FamilyConfiguration familyConfiguration = new FamilyConfiguration();

  public UUID id;

  public String name;

  public Tenant() {
  }

  public Tenant(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public Tenant(Tenant tenant) {
    this.data.putAll(tenant.data);
    this.emailConfiguration = new TenantEmailConfiguration(tenant.emailConfiguration);
    this.familyConfiguration = new FamilyConfiguration(tenant.familyConfiguration);
    this.id = tenant.id;
    this.name = tenant.name;
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
    return Objects.equals(data, tenant.data) &&
        Objects.equals(emailConfiguration, tenant.emailConfiguration) &&
        Objects.equals(name, tenant.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, emailConfiguration, name);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class FamilyConfiguration extends Enableable implements Buildable<FamilyConfiguration> {
    public boolean allowChildRegistrations = true;

    // TODO - Need to validate this option and all email template options in the edit SystemConfiguration form in the UI if the email
    //        configuration is not valid. This should produce an error if the email configuration is disabled and the user tries to
    //        select a template here.
    public UUID confirmChildEmailTemplateId;

    public boolean deleteOrphanedAccounts;

    public int deleteOrphanedAccountsDays = 30;

    // TODO - Need to validate this option and all email template options in the edit SystemConfiguration form in the UI if the email
    //        configuration is not valid. This should produce an error if the email configuration is disabled and the user tries to
    //        select a template here.
    public UUID familyRequestEmailTemplateId;

    public int maximumChildAge = 12;

    public int minimumOwnerAge = 21;

    public boolean parentEmailRequired;

    // TODO - Need to validate this option and all email template options in the edit SystemConfiguration form in the UI if the email
    //        configuration is not valid. This should produce an error if the email configuration is disabled and the user tries to
    //        select a template here.
    public UUID parentRegistrationEmailTemplateId;

    public FamilyConfiguration() {
    }

    public FamilyConfiguration(FamilyConfiguration other) {
      this.allowChildRegistrations = other.allowChildRegistrations;
      this.familyRequestEmailTemplateId = other.familyRequestEmailTemplateId;
      this.confirmChildEmailTemplateId = other.confirmChildEmailTemplateId;
      this.deleteOrphanedAccounts = other.deleteOrphanedAccounts;
      this.deleteOrphanedAccountsDays = other.deleteOrphanedAccountsDays;
      this.maximumChildAge = other.maximumChildAge;
      this.minimumOwnerAge = other.minimumOwnerAge;
      this.parentEmailRequired = other.parentEmailRequired;
      this.parentRegistrationEmailTemplateId = other.parentRegistrationEmailTemplateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FamilyConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      FamilyConfiguration that = (FamilyConfiguration) o;
      return allowChildRegistrations == that.allowChildRegistrations &&
          deleteOrphanedAccounts == that.deleteOrphanedAccounts &&
          deleteOrphanedAccountsDays == that.deleteOrphanedAccountsDays &&
          maximumChildAge == that.maximumChildAge &&
          minimumOwnerAge == that.minimumOwnerAge &&
          parentEmailRequired == that.parentEmailRequired &&
          Objects.equals(familyRequestEmailTemplateId, that.familyRequestEmailTemplateId) &&
          Objects.equals(confirmChildEmailTemplateId, that.confirmChildEmailTemplateId) &&
          Objects.equals(parentRegistrationEmailTemplateId, that.parentRegistrationEmailTemplateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), allowChildRegistrations, familyRequestEmailTemplateId, confirmChildEmailTemplateId,
                          deleteOrphanedAccounts, deleteOrphanedAccountsDays, maximumChildAge, minimumOwnerAge, parentEmailRequired,
                          parentRegistrationEmailTemplateId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public class TenantEmailConfiguration extends Enableable {
    public UUID forgotPasswordEmailTemplateId;

    public UUID passwordlessEmailTemplateId;

    public UUID setPasswordEmailTemplateId;

    public UUID verificationEmailTemplateId;

    public boolean verifyEmail;

    public boolean verifyEmailWhenChanged;

    public TenantEmailConfiguration() {
    }

    public TenantEmailConfiguration(TenantEmailConfiguration other) {
      this.forgotPasswordEmailTemplateId = other.forgotPasswordEmailTemplateId;
      this.passwordlessEmailTemplateId = other.passwordlessEmailTemplateId;
      this.setPasswordEmailTemplateId = other.setPasswordEmailTemplateId;
      this.verificationEmailTemplateId = other.verificationEmailTemplateId;
      this.verifyEmail = other.verifyEmail;
      this.verifyEmailWhenChanged = other.verifyEmailWhenChanged;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TenantEmailConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }

      TenantEmailConfiguration that = (TenantEmailConfiguration) o;
      return verifyEmail == that.verifyEmail &&
          verifyEmailWhenChanged == that.verifyEmailWhenChanged &&
          Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
          Objects.equals(passwordlessEmailTemplateId, that.passwordlessEmailTemplateId) &&
          Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId) &&
          Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), forgotPasswordEmailTemplateId, passwordlessEmailTemplateId, setPasswordEmailTemplateId,
                          verificationEmailTemplateId, verifyEmail, verifyEmailWhenChanged);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
