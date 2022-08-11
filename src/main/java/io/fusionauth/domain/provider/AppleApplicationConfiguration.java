/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;


/**
 * @author Daniel DeGroff
 */
public class AppleApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<AppleApplicationConfiguration> {
  
  public String buttonText;

  public UUID keyId;

  
  public String scope;

  
  public String servicesId;

  
  public String teamId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AppleApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    AppleApplicationConfiguration that = (AppleApplicationConfiguration) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(keyId, that.keyId) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(servicesId, that.servicesId) &&
           Objects.equals(teamId, that.teamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, keyId, scope, servicesId, teamId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
