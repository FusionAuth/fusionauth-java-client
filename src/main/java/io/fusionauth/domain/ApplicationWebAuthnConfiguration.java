/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * Application-level configuration for WebAuthn
 *
 * @author Daniel DeGroff
 */
public class ApplicationWebAuthnConfiguration extends Enableable implements Buildable<ApplicationWebAuthnConfiguration> {
  public ApplicationWebAuthnWorkflowConfiguration bootstrapWorkflow = new ApplicationWebAuthnWorkflowConfiguration();

  public ApplicationWebAuthnWorkflowConfiguration reauthenticationWorkflow = new ApplicationWebAuthnWorkflowConfiguration();

  @JacksonConstructor
  public ApplicationWebAuthnConfiguration() {
  }

  public ApplicationWebAuthnConfiguration(ApplicationWebAuthnConfiguration other) {
    this.bootstrapWorkflow = new ApplicationWebAuthnWorkflowConfiguration(other.bootstrapWorkflow);
    this.enabled = other.enabled;
    this.reauthenticationWorkflow = new ApplicationWebAuthnWorkflowConfiguration(other.reauthenticationWorkflow);
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
    ApplicationWebAuthnConfiguration that = (ApplicationWebAuthnConfiguration) o;
    return Objects.equals(bootstrapWorkflow, that.bootstrapWorkflow) && Objects.equals(reauthenticationWorkflow, that.reauthenticationWorkflow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), bootstrapWorkflow, reauthenticationWorkflow);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
