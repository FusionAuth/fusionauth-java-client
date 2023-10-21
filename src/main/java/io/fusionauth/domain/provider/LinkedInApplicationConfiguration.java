/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class LinkedInApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<LinkedInApplicationConfiguration> {
  public String buttonText;

  public String client_id;

  public String client_secret;

  public String scope;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LinkedInApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    LinkedInApplicationConfiguration that = (LinkedInApplicationConfiguration) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(client_secret, that.client_secret) &&
           Objects.equals(scope, that.scope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, client_id, client_secret, scope);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
