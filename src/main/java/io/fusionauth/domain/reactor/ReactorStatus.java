/*
 * Copyright (c) 2021-2026, FusionAuth, All Rights Reserved
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

  public ReactorFeatureStatus clientRiskConfiguration = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus connectors = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus dPoP = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus entityManagement = ReactorFeatureStatus.UNKNOWN;

  public LocalDate expiration;

  public ReactorFeatureStatus imfaWebhooks = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus intelligentMFA = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus ipGeoLocation = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus ipReputation = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus legacyAdapter = ReactorFeatureStatus.UNKNOWN;

  public Map<String, String> licenseAttributes = new HashMap<>();

  public boolean licensed;

  public ReactorFeatureStatus multiFactorLambdas = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus scimServer = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus tenantManagerApplication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus threatDetection = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus universalApplication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus userAgentReputation = ReactorFeatureStatus.UNKNOWN;

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
    multiFactorLambdas = other.multiFactorLambdas;
    advancedRegistration = other.advancedRegistration;
    applicationMultiFactorAuthentication = other.applicationMultiFactorAuthentication;
    applicationThemes = other.applicationThemes;
    breachedPasswordDetection = other.breachedPasswordDetection;
    connectors = other.connectors;
    advancedOAuthScopes = other.advancedOAuthScopes;
    advancedOAuthScopesCustomScopes = other.advancedOAuthScopesCustomScopes;
    advancedOAuthScopesThirdPartyApplications = other.advancedOAuthScopesThirdPartyApplications;
    clientRiskConfiguration = other.clientRiskConfiguration;
    dPoP = other.dPoP;
    entityManagement = other.entityManagement;
    expiration = other.expiration;
    imfaWebhooks = other.imfaWebhooks;
    intelligentMFA = other.intelligentMFA;
    ipGeoLocation = other.ipGeoLocation;
    ipReputation = other.ipReputation;
    legacyAdapter = other.legacyAdapter;
    licenseAttributes.putAll(other.licenseAttributes);
    licensed = other.licensed;
    tenantManagerApplication = other.tenantManagerApplication;
    scimServer = other.scimServer;
    threatDetection = other.threatDetection;
    universalApplication = other.universalApplication;
    userAgentReputation = other.userAgentReputation;
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
           advancedOAuthScopes == that.advancedOAuthScopes &&
           advancedOAuthScopesCustomScopes == that.advancedOAuthScopesCustomScopes &&
           advancedOAuthScopesThirdPartyApplications == that.advancedOAuthScopesThirdPartyApplications &&
           advancedRegistration == that.advancedRegistration &&
           applicationMultiFactorAuthentication == that.applicationMultiFactorAuthentication &&
           applicationThemes == that.applicationThemes &&
           breachedPasswordDetection == that.breachedPasswordDetection &&
           connectors == that.connectors &&
           clientRiskConfiguration == that.clientRiskConfiguration &&
           dPoP == that.dPoP &&
           entityManagement == that.entityManagement &&
           imfaWebhooks == that.imfaWebhooks &&
           intelligentMFA == that.intelligentMFA &&
           ipGeoLocation == that.ipGeoLocation &&
           ipReputation == that.ipReputation &&
           legacyAdapter == that.legacyAdapter &&
           Objects.equals(expiration, that.expiration) &&
           licensed == that.licensed &&
           Objects.equals(licenseAttributes, that.licenseAttributes) &&
           multiFactorLambdas == that.multiFactorLambdas &&
           tenantManagerApplication == that.tenantManagerApplication &&
           scimServer == that.scimServer &&
           threatDetection == that.threatDetection &&
           universalApplication == that.universalApplication &&
           userAgentReputation == that.userAgentReputation &&
           webAuthn == that.webAuthn &&
           webAuthnPlatformAuthenticators == that.webAuthnPlatformAuthenticators &&
           webAuthnRoamingAuthenticators == that.webAuthnRoamingAuthenticators;
  }

  @Override
  public int hashCode() {
    return Objects.hash(advancedIdentityProviders,
                        advancedLambdas,
                        advancedMultiFactorAuthentication,
                        advancedOAuthScopes,
                        advancedOAuthScopesCustomScopes,
                        advancedOAuthScopesThirdPartyApplications,
                        advancedRegistration,
                        applicationMultiFactorAuthentication,
                        applicationThemes,
                        breachedPasswordDetection,
                        clientRiskConfiguration,
                        connectors,
                        dPoP,
                        entityManagement,
                        expiration,
                        imfaWebhooks,
                        intelligentMFA,
                        ipGeoLocation,
                        ipReputation,
                        legacyAdapter,
                        licensed,
                        licenseAttributes,
                        multiFactorLambdas,
                        tenantManagerApplication,
                        scimServer,
                        threatDetection,
                        universalApplication,
                        userAgentReputation,
                        webAuthn,
                        webAuthnPlatformAuthenticators,
                        webAuthnRoamingAuthenticators);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
