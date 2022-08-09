/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Enableable;



/**
 * @author Daniel DeGroff
 */
public abstract class BaseIdentityProviderApplicationConfiguration extends Enableable  {
  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public final Map<String, Object> data = new HashMap<>();

  
  public boolean createRegistration = true;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseIdentityProviderApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    BaseIdentityProviderApplicationConfiguration that = (BaseIdentityProviderApplicationConfiguration) o;
    return createRegistration == that.createRegistration &&
           Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), data, createRegistration);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
