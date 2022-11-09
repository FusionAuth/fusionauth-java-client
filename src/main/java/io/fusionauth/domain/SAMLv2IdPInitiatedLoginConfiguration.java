/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * IdP Initiated login configuration
 *
 * @author Daniel DeGroff
 */
public class SAMLv2IdPInitiatedLoginConfiguration extends Enableable implements Buildable<SAMLv2IdPInitiatedLoginConfiguration> {
  public String nameIdFormat = "urn:oasis:names:tc:SAML:2.0:nameid-format:persistent";

  @JacksonConstructor
  public SAMLv2IdPInitiatedLoginConfiguration() {
  }

  public SAMLv2IdPInitiatedLoginConfiguration(SAMLv2IdPInitiatedLoginConfiguration other) {
    this.enabled = other.enabled;
    this.nameIdFormat = other.nameIdFormat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SAMLv2IdPInitiatedLoginConfiguration that = (SAMLv2IdPInitiatedLoginConfiguration) o;
    return Objects.equals(nameIdFormat, that.nameIdFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nameIdFormat);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
