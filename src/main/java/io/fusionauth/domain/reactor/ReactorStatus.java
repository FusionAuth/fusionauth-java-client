/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.reactor;

import java.util.Objects;

import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ReactorStatus {
  public ReactorFeatureStatus advancedIdentityProviders = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedMultiFactorAuthentication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedRegistrationForms = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus breachedPasswordDetection = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus connectors = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus entityManagement = ReactorFeatureStatus.UNKNOWN;

  public boolean licensed;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReactorStatus that = (ReactorStatus) o;
    return licensed == that.licensed &&
           advancedIdentityProviders == that.advancedIdentityProviders &&
           advancedMultiFactorAuthentication == that.advancedMultiFactorAuthentication &&
           advancedRegistrationForms == that.advancedRegistrationForms &&
           breachedPasswordDetection == that.breachedPasswordDetection &&
           connectors == that.connectors &&
           entityManagement == that.entityManagement;
  }

  @Override
  public int hashCode() {
    return Objects.hash(advancedIdentityProviders,
                        advancedMultiFactorAuthentication,
                        advancedRegistrationForms,
                        breachedPasswordDetection,
                        connectors,
                        entityManagement,
                        licensed);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}