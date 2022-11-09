/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ApplicationWebAuthnWorkflowConfiguration extends Enableable implements Buildable<ApplicationWebAuthnWorkflowConfiguration> {

  @JacksonConstructor
  public ApplicationWebAuthnWorkflowConfiguration() {
  }

  public ApplicationWebAuthnWorkflowConfiguration(ApplicationWebAuthnWorkflowConfiguration other) {
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