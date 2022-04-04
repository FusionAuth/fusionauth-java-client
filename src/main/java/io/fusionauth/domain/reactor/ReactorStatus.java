/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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

import java.time.LocalDate;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ReactorStatus {
  public ReactorFeatureStatus advancedIdentityProviders = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedLambdas = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedMultiFactorAuthentication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedRegistration = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus applicationThemes = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus breachedPasswordDetection = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus connectors = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus entityManagement = ReactorFeatureStatus.UNKNOWN;

  public LocalDate expiration;

  public boolean licensed;

  public ReactorFeatureStatus scimServer = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus threatDetection = ReactorFeatureStatus.UNKNOWN;

  @JacksonConstructor
  public ReactorStatus() {
  }

  public ReactorStatus(ReactorStatus other) {
    advancedIdentityProviders = other.advancedIdentityProviders;
    advancedLambdas = other.advancedLambdas;
    advancedMultiFactorAuthentication = other.advancedMultiFactorAuthentication;
    advancedRegistration = other.advancedRegistration;
    applicationThemes = other.applicationThemes;
    breachedPasswordDetection = other.breachedPasswordDetection;
    connectors = other.connectors;
    entityManagement = other.entityManagement;
    expiration = other.expiration;
    licensed = other.licensed;
    scimServer = other.scimServer;
    threatDetection = other.threatDetection;
  }

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
           advancedLambdas == that.advancedLambdas &&
           advancedMultiFactorAuthentication == that.advancedMultiFactorAuthentication &&
           advancedRegistration == that.advancedRegistration &&
           applicationThemes == that.applicationThemes &&
           breachedPasswordDetection == that.breachedPasswordDetection &&
           connectors == that.connectors &&
           entityManagement == that.entityManagement &&
           Objects.equals(expiration, that.expiration) &&
           scimServer == that.scimServer &&
           threatDetection == that.threatDetection;
  }

  @Override
  public int hashCode() {
    return Objects.hash(advancedIdentityProviders,
                        advancedLambdas,
                        advancedMultiFactorAuthentication,
                        advancedRegistration,
                        applicationThemes,
                        breachedPasswordDetection,
                        connectors,
                        entityManagement,
                        expiration,
                        licensed,
                        scimServer,
                        threatDetection);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}