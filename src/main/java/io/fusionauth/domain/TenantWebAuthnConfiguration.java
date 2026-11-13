/*
 * Copyright (c) 2022-2026, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package io.fusionauth.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.util.Normalizer;
import io.fusionauth.domain.webauthn.AuthenticatorAttachmentPreference;
import io.fusionauth.domain.webauthn.UserVerificationRequirement;

/**
 * Tenant-level configuration for WebAuthn
 *
 * @author Spencer Witt
 */
public class TenantWebAuthnConfiguration extends Enableable implements Buildable<TenantWebAuthnConfiguration> {
  public List<String> allowedOrigins = new ArrayList<>();

  public TenantWebAuthnWorkflowConfiguration bootstrapWorkflow = new TenantWebAuthnWorkflowConfiguration().with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.any)
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
    this.allowedOrigins.addAll(other.allowedOrigins);
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
           Objects.equals(allowedOrigins, that.allowedOrigins) &&
           Objects.equals(bootstrapWorkflow, that.bootstrapWorkflow) &&
           Objects.equals(reauthenticationWorkflow, that.reauthenticationWorkflow) &&
           Objects.equals(relyingPartyId, that.relyingPartyId) &&
           Objects.equals(relyingPartyName, that.relyingPartyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), allowedOrigins, debug, bootstrapWorkflow, reauthenticationWorkflow, relyingPartyId, relyingPartyName);
  }

  public void normalize() {
    Normalizer.removeEmpty(allowedOrigins);
    Normalizer.deDuplicate(allowedOrigins);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
