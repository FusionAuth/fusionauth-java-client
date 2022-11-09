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
 * Tenant-level configuration for WebAuthn
 *
 * @author Spencer Witt
 */
public class TenantWebAuthnConfiguration extends Enableable implements Buildable<TenantWebAuthnConfiguration> {
  public TenantWebAuthnWorkflowConfiguration bootstrapWorkflow = new TenantWebAuthnWorkflowConfiguration().with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.platform)
                                                                                                          .with(c -> c.userVerificationRequirement = UserVerificationRequirement.required);

  public boolean debug;

  // This configuration may be re-used per workflow so that it is not initialized in the workflow configuration itself.
  public TenantWebAuthnWorkflowConfiguration reauthenticationWorkflow = new TenantWebAuthnWorkflowConfiguration().with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.platform)
                                                                                                                 .with(c -> c.userVerificationRequirement = UserVerificationRequirement.required);

  public String relyingPartyId;

  public String relyingPartyName;

  @JacksonConstructor
  public TenantWebAuthnConfiguration() {
  }

  public TenantWebAuthnConfiguration(TenantWebAuthnConfiguration other) {
    this.bootstrapWorkflow = new TenantWebAuthnWorkflowConfiguration(other.bootstrapWorkflow);
    this.debug = other.debug;
    this.enabled = other.enabled;
    this.reauthenticationWorkflow = new TenantWebAuthnWorkflowConfiguration(other.reauthenticationWorkflow);
    this.relyingPartyId = other.relyingPartyId;
    this.relyingPartyName = other.relyingPartyName;
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
    return debug == that.debug &&
           Objects.equals(bootstrapWorkflow, that.bootstrapWorkflow) &&
           Objects.equals(reauthenticationWorkflow, that.reauthenticationWorkflow) &&
           Objects.equals(relyingPartyId, that.relyingPartyId) &&
           Objects.equals(relyingPartyName, that.relyingPartyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), debug, bootstrapWorkflow, reauthenticationWorkflow, relyingPartyId, relyingPartyName);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
