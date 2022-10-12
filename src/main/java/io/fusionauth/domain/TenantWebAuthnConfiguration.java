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
// TODO : WebAuthn : Daniel Review : Do we need this Enableable
public class TenantWebAuthnConfiguration extends Enableable implements Buildable<TenantWebAuthnConfiguration> {
  // TODO : WebAuthn : Daniel Review : Naming?
  //        Bootstrap:        ?
  //        Reauthentication: ?
  //        TwoFactor:        ?
  // This configuration may be re-used per workflow so it is not initialized in the workflow configuration itself.
  public TenantWebAuthnWorkflowConfiguration reauthenticationWorkflowConfiguration = new TenantWebAuthnWorkflowConfiguration()
      .with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.platform)
      .with(c -> c.userVerificationRequirement = UserVerificationRequirement.required);

  // TODO : WebAuthn : Daniel Review : I think we need to add bootstrap or whatever it is called and then use it on the "manual" webauthn login page

  public String relyingPartyId;

  public String relyingPartyName;

  @JacksonConstructor
  public TenantWebAuthnConfiguration() {
  }

  public TenantWebAuthnConfiguration(TenantWebAuthnConfiguration other) {
    this.enabled = other.enabled;
    this.reauthenticationWorkflowConfiguration = new TenantWebAuthnWorkflowConfiguration(other.reauthenticationWorkflowConfiguration);
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
    return Objects.equals(reauthenticationWorkflowConfiguration, that.reauthenticationWorkflowConfiguration) &&
           Objects.equals(relyingPartyId, that.relyingPartyId) &&
           Objects.equals(relyingPartyName, that.relyingPartyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), reauthenticationWorkflowConfiguration, relyingPartyId, relyingPartyName);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
