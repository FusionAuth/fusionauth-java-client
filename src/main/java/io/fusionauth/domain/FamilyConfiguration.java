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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;

/**
 * @author Brian Pontarelli
 */
public class FamilyConfiguration extends Enableable implements Buildable<FamilyConfiguration> {
  public boolean allowChildRegistrations = true;

  @ExcludeFromDatabaseDataColumn
  public UUID confirmChildEmailTemplateId;

  public boolean deleteOrphanedAccounts;

  public int deleteOrphanedAccountsDays = 30;

  @ExcludeFromDatabaseDataColumn
  public UUID familyRequestEmailTemplateId;

  public int maximumChildAge = 12;

  public int minimumOwnerAge = 21;

  public boolean parentEmailRequired;

  @ExcludeFromDatabaseDataColumn
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

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}