/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * Application-level configuration for WebAuthn
 *
 * @author Daniel DeGroff
 */
public class ApplicationWebAuthnConfiguration extends Enableable implements Buildable<ApplicationWebAuthnConfiguration> {

  @JacksonConstructor
  public ApplicationWebAuthnConfiguration() {
  }

  public ApplicationWebAuthnConfiguration(ApplicationWebAuthnConfiguration other) {
    this.enabled = other.enabled;
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
