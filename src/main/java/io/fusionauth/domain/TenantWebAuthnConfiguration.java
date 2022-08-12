/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.api.webauthn.enums.AuthenticatorAttachment;
import io.fusionauth.domain.api.webauthn.enums.AuthenticatorAttachmentPreference;
import io.fusionauth.domain.api.webauthn.enums.CoseAlgorithmIdentifier;
import io.fusionauth.domain.api.webauthn.enums.UserVerificationRequirement;
import io.fusionauth.domain.api.webauthn.enums.WebAuthnWorkflow;

/**
 * Tenant-level configuration for WebAuthn
 *
 * @author Spencer Witt
 */
public class TenantWebAuthnConfiguration extends Enableable implements Buildable<TenantWebAuthnConfiguration> {
  @JsonIgnore
  public WebAuthnWorkflowConfiguration bootstrapWorkflowConfiguration = new WebAuthnWorkflowConfiguration()
      .with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.CROSS_PLATFORM)
      .with(c -> c.userVerificationRequirement = UserVerificationRequirement.required);

  @JsonIgnore
  public WebAuthnWorkflowConfiguration generalWorkflowConfiguration = new WebAuthnWorkflowConfiguration()
      .with(c -> c.enabled = true)
      .with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.EITHER)
      .with(c -> c.userVerificationRequirement = UserVerificationRequirement.preferred);

  public WebAuthnWorkflowConfiguration reauthenticationWorkflowConfiguration = new WebAuthnWorkflowConfiguration()
      .with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.PLATFORM)
      .with(c -> c.userVerificationRequirement = UserVerificationRequirement.required);

  @JsonIgnore
  public List<CoseAlgorithmIdentifier> signatureAlgorithmPreference = Arrays.asList(
      CoseAlgorithmIdentifier.ES512, CoseAlgorithmIdentifier.ES384, CoseAlgorithmIdentifier.ES256,
      CoseAlgorithmIdentifier.PS512, CoseAlgorithmIdentifier.PS384, CoseAlgorithmIdentifier.PS256,
      CoseAlgorithmIdentifier.RS512, CoseAlgorithmIdentifier.RS384, CoseAlgorithmIdentifier.RS256);

  @JsonIgnore
  public WebAuthnWorkflowConfiguration twoFactorWorkflowConfiguration = new WebAuthnWorkflowConfiguration()
      .with(c -> c.authenticatorAttachmentPreference = AuthenticatorAttachmentPreference.CROSS_PLATFORM)
      .with(c -> c.userVerificationRequirement = UserVerificationRequirement.discouraged);

  @JacksonConstructor
  public TenantWebAuthnConfiguration() {
  }

  public TenantWebAuthnConfiguration(TenantWebAuthnConfiguration other) {
    this.enabled = other.enabled;
    this.bootstrapWorkflowConfiguration = new WebAuthnWorkflowConfiguration(other.bootstrapWorkflowConfiguration);
    this.generalWorkflowConfiguration = new WebAuthnWorkflowConfiguration(other.generalWorkflowConfiguration);
    this.reauthenticationWorkflowConfiguration = new WebAuthnWorkflowConfiguration(other.reauthenticationWorkflowConfiguration);
    this.twoFactorWorkflowConfiguration = new WebAuthnWorkflowConfiguration(other.twoFactorWorkflowConfiguration);
    this.signatureAlgorithmPreference = other.signatureAlgorithmPreference.stream().collect(Collectors.toList());
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
    return reauthenticationWorkflowConfiguration.equals(that.reauthenticationWorkflowConfiguration) &&
           signatureAlgorithmPreference.equals(that.signatureAlgorithmPreference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), reauthenticationWorkflowConfiguration, signatureAlgorithmPreference);
  }

  @JsonIgnore
  public boolean isWebAuthnAvailable() {
    return this.enabled && Arrays.stream(WebAuthnWorkflow.values()).anyMatch(w -> retrieveWebAuthnWorkflowConfiguration(w).enabled);
  }

  public WebAuthnWorkflowConfiguration retrieveWebAuthnWorkflowConfiguration(WebAuthnWorkflow workflowType) {
    switch (workflowType) {
      case REAUTH:
        return reauthenticationWorkflowConfiguration;
      case BOOTSTRAP:
        return bootstrapWorkflowConfiguration;
      case TWO_FACTOR:
        return twoFactorWorkflowConfiguration;
      case GENERAL:
        return generalWorkflowConfiguration;
      default:
        return null;
    }
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

    @JsonIgnore
    public AuthenticatorAttachment getAuthenticatorAttachment() {
      switch (authenticatorAttachmentPreference) {
        case PLATFORM:
          return AuthenticatorAttachment.PLATFORM;
        case CROSS_PLATFORM:
          return AuthenticatorAttachment.CROSS_PLATFORM;
        default:
          // The EITHER option will return null
          return null;
      }
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
