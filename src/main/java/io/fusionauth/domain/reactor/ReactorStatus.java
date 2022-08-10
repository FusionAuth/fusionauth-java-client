/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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

  public ReactorFeatureStatus advancedRegistration = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus applicationMultiFactorAuthentication = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus applicationThemes = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus breachedPasswordDetection = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus connectors = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus entityManagement = ReactorFeatureStatus.UNKNOWN;

  public LocalDate expiration;

  public Map<String, String> licenseAttributes = new HashMap<>();

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
    applicationMultiFactorAuthentication = other.applicationMultiFactorAuthentication;
    applicationThemes = other.applicationThemes;
    breachedPasswordDetection = other.breachedPasswordDetection;
    connectors = other.connectors;
    entityManagement = other.entityManagement;
    expiration = other.expiration;
    licenseAttributes.putAll(other.licenseAttributes);
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
    return advancedIdentityProviders == that.advancedIdentityProviders &&
           advancedLambdas == that.advancedLambdas &&
           advancedMultiFactorAuthentication == that.advancedMultiFactorAuthentication &&
           advancedRegistration == that.advancedRegistration &&
           applicationMultiFactorAuthentication == that.applicationMultiFactorAuthentication &&
           applicationThemes == that.applicationThemes &&
           breachedPasswordDetection == that.breachedPasswordDetection &&
           connectors == that.connectors &&
           entityManagement == that.entityManagement &&
           Objects.equals(expiration, that.expiration) &&
           licensed == that.licensed &&
           Objects.equals(licenseAttributes, that.licenseAttributes) &&
           scimServer == that.scimServer &&
           threatDetection == that.threatDetection;
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
                        entityManagement,
                        expiration,
                        licensed,
                        licenseAttributes,
                        scimServer,
                        threatDetection);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}