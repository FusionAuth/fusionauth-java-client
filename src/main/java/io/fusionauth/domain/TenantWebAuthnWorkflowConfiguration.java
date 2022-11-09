/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.webauthn.AuthenticatorAttachmentPreference;
import io.fusionauth.domain.webauthn.UserVerificationRequirement;

/**
 * @author Spencer Witt
 */
public class TenantWebAuthnWorkflowConfiguration extends Enableable implements Buildable<TenantWebAuthnWorkflowConfiguration> {
  public AuthenticatorAttachmentPreference authenticatorAttachmentPreference;

  public UserVerificationRequirement userVerificationRequirement;

  @JacksonConstructor
  public TenantWebAuthnWorkflowConfiguration() {
  }

  public TenantWebAuthnWorkflowConfiguration(TenantWebAuthnWorkflowConfiguration other) {
    this.enabled = other.enabled;
    this.authenticatorAttachmentPreference = other.authenticatorAttachmentPreference;
    this.userVerificationRequirement = other.userVerificationRequirement;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantWebAuthnWorkflowConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TenantWebAuthnWorkflowConfiguration that = (TenantWebAuthnWorkflowConfiguration) o;
    return authenticatorAttachmentPreference == that.authenticatorAttachmentPreference &&
           userVerificationRequirement == that.userVerificationRequirement;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), authenticatorAttachmentPreference, userVerificationRequirement);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}