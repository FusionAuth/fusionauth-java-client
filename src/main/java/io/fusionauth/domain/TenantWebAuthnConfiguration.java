/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.api.webauthn.AuthenticatorAttachmentPreference;
import io.fusionauth.domain.api.webauthn.UserVerificationRequirement;

/**
 * Tenant-level configuration for WebAuthn
 *
 * @author Spencer Witt
 */
public class TenantWebAuthnConfiguration extends Enableable implements Buildable<TenantWebAuthnConfiguration> {

  public WebAuthnWorkflowConfiguration reauthenticationWorkflowConfiguration = new WebAuthnWorkflowConfiguration()
      .with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.PLATFORM)
      .with(c -> c.userVerificationRequirement = UserVerificationRequirement.required);

  public String rpId = null;

  public String rpName = null;

  @JacksonConstructor
  public TenantWebAuthnConfiguration() {
  }

  public TenantWebAuthnConfiguration(TenantWebAuthnConfiguration other) {
    this.enabled = other.enabled;
    this.reauthenticationWorkflowConfiguration = new WebAuthnWorkflowConfiguration(other.reauthenticationWorkflowConfiguration);
    this.rpId = other.rpId;
    this.rpName = other.rpName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantWebAuthnConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TenantWebAuthnConfiguration that = (TenantWebAuthnConfiguration) o;
    return Objects.equals(reauthenticationWorkflowConfiguration, that.reauthenticationWorkflowConfiguration) &&
           Objects.equals(rpId, that.rpId) &&
           Objects.equals(rpName, that.rpName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), reauthenticationWorkflowConfiguration, rpId, rpName);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class WebAuthnWorkflowConfiguration extends Enableable implements Buildable<WebAuthnWorkflowConfiguration> {
    public AuthenticatorAttachmentPreference authenticatorAttachmentPreference;

    public UserVerificationRequirement userVerificationRequirement;

    @JacksonConstructor
    public WebAuthnWorkflowConfiguration() {
    }

    public WebAuthnWorkflowConfiguration(WebAuthnWorkflowConfiguration other) {
      this.enabled = other.enabled;
      this.authenticatorAttachmentPreference = other.authenticatorAttachmentPreference;
      this.userVerificationRequirement = other.userVerificationRequirement;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof WebAuthnWorkflowConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      WebAuthnWorkflowConfiguration that = (WebAuthnWorkflowConfiguration) o;
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
}
