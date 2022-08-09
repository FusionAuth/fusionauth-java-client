/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;


/**
 * @author Brian Pontarelli
 */
public class FamilyConfiguration extends Enableable implements Buildable<FamilyConfiguration> {
  public boolean allowChildRegistrations = true;

  
  public UUID confirmChildEmailTemplateId;

  public boolean deleteOrphanedAccounts;

  public int deleteOrphanedAccountsDays = 30;

  
  public UUID familyRequestEmailTemplateId;

  public int maximumChildAge = 12;

  public int minimumOwnerAge = 21;

  public boolean parentEmailRequired;

  
  public UUID parentRegistrationEmailTemplateId;

  @JacksonConstructor
  public FamilyConfiguration() {
  }

  public FamilyConfiguration(FamilyConfiguration other) {
    this.allowChildRegistrations = other.allowChildRegistrations;
    this.confirmChildEmailTemplateId = other.confirmChildEmailTemplateId;
    this.deleteOrphanedAccounts = other.deleteOrphanedAccounts;
    this.deleteOrphanedAccountsDays = other.deleteOrphanedAccountsDays;
    this.enabled = other.enabled;
    this.familyRequestEmailTemplateId = other.familyRequestEmailTemplateId;
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

  public void normalize() {
    if (!allowChildRegistrations) {
      parentEmailRequired = false;
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}