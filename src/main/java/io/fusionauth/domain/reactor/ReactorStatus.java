/*
 * Copyright (c) 2021-2025, FusionAuth, All Rights Reserved
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
import java.util.HashMap;
import java.util.Map;
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

  public ReactorFeatureStatus advancedOAuthScopes = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedOAuthScopesCustomScopes = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedOAuthScopesThirdPartyApplications = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus advancedRegistration = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus applicationMultiFactorAuthentication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus applicationThemes = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus breachedPasswordDetection = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus connectors = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus entityManagement = ReactorFeatureStatus.UNKNOWN;

  public LocalDate expiration;

  public Map<String, String> licenseAttributes = new HashMap<>();

  public boolean licensed;

  public ReactorFeatureStatus organizationAdminApplication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus scimServer = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus threatDetection = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus universalApplication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus webAuthn = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus webAuthnPlatformAuthenticators = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus webAuthnRoamingAuthenticators = ReactorFeatureStatus.UNKNOWN;

  @JacksonConstructor
  public ReactorStatus() {
  }

  public ReactorStatus(ReactorStatus other) {
    advancedIdentityProviders = other.advancedIdentityProviders;
    advancedLambdas = other.advancedLambdas;
    advancedMultiFactorAuthentication = other.advancedMultiFactorAuthentication;
    advancedRegistration = other.advancedRegistration;
    applicationMultiFactorAuthentication = other.applicationMultiFactorAuthentication;
    applicationThemes = other.applicationThemes;
    breachedPasswordDetection = other.breachedPasswordDetection;
    connectors = other.connectors;
    advancedOAuthScopes = other.advancedOAuthScopes;
    advancedOAuthScopesCustomScopes = other.advancedOAuthScopesCustomScopes;
    advancedOAuthScopesThirdPartyApplications = other.advancedOAuthScopesThirdPartyApplications;
    entityManagement = other.entityManagement;
    expiration = other.expiration;
    licenseAttributes.putAll(other.licenseAttributes);
    licensed = other.licensed;
    organizationAdminApplication = other.organizationAdminApplication;
    scimServer = other.scimServer;
    threatDetection = other.threatDetection;
    universalApplication = other.universalApplication;
    webAuthn = other.webAuthn;
    webAuthnPlatformAuthenticators = other.webAuthnPlatformAuthenticators;
    webAuthnRoamingAuthenticators = other.webAuthnRoamingAuthenticators;
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
    return advancedIdentityProviders == that.advancedIdentityProviders &&
           advancedLambdas == that.advancedLambdas &&
           advancedMultiFactorAuthentication == that.advancedMultiFactorAuthentication &&
           advancedRegistration == that.advancedRegistration &&
           applicationMultiFactorAuthentication == that.applicationMultiFactorAuthentication &&
           applicationThemes == that.applicationThemes &&
           breachedPasswordDetection == that.breachedPasswordDetection &&
           connectors == that.connectors &&
           advancedOAuthScopes == that.advancedOAuthScopes &&
           advancedOAuthScopesCustomScopes == that.advancedOAuthScopesCustomScopes &&
           advancedOAuthScopesThirdPartyApplications == that.advancedOAuthScopesThirdPartyApplications &&
           entityManagement == that.entityManagement &&
           Objects.equals(expiration, that.expiration) &&
           licensed == that.licensed &&
           Objects.equals(licenseAttributes, that.licenseAttributes) &&
           organizationAdminApplication == that.organizationAdminApplication &&
           scimServer == that.scimServer &&
           threatDetection == that.threatDetection &&
           universalApplication == that.universalApplication &&
           webAuthn == that.webAuthn &&
           webAuthnPlatformAuthenticators == that.webAuthnPlatformAuthenticators &&
           webAuthnRoamingAuthenticators == that.webAuthnRoamingAuthenticators;
  }

  @Override
  public int hashCode() {
    return Objects.hash(advancedIdentityProviders,
                        advancedLambdas,
                        advancedMultiFactorAuthentication,
                        advancedRegistration,
                        applicationMultiFactorAuthentication,
                        applicationThemes,
                        breachedPasswordDetection,
                        connectors,
                        advancedOAuthScopes,
                        advancedOAuthScopesCustomScopes,
                        advancedOAuthScopesThirdPartyApplications,
                        entityManagement,
                        expiration,
                        licensed,
                        licenseAttributes,
                        organizationAdminApplication,
                        scimServer,
                        threatDetection,
                        universalApplication,
                        webAuthn,
                        webAuthnPlatformAuthenticators,
                        webAuthnRoamingAuthenticators);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}