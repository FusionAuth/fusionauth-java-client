/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.SAMLv2DestinationAssertionPolicy;

/**
 * @author Lyle Schemmerling
 */
public class SAMLv2DestinationAssertionConfiguration implements Buildable<SAMLv2DestinationAssertionConfiguration> {
  public List<String> alternates = new ArrayList<>();

  public SAMLv2DestinationAssertionPolicy policy = SAMLv2DestinationAssertionPolicy.Enabled;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SAMLv2DestinationAssertionConfiguration that = (SAMLv2DestinationAssertionConfiguration) o;
    return Objects.equals(alternates, that.alternates) &&
           policy == that.policy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(alternates, policy);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
