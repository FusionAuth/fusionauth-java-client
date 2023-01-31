/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Lyle Schemmerling
 */
public class SAMLv2AssertionConfiguration implements Buildable<SAMLv2AssertionConfiguration> {
  public SAMLv2DestinationAssertionConfiguration destination = new SAMLv2DestinationAssertionConfiguration();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SAMLv2AssertionConfiguration that = (SAMLv2AssertionConfiguration) o;
    return Objects.equals(destination, that.destination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(destination);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
