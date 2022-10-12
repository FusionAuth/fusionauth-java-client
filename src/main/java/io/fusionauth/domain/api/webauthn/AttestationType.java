/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Used to indicate what type of attestation was included in the authenticator response for a given WebAuthn credential at the time it was created
 *
 * @author Spencer Witt
 */
public enum AttestationType {
  /**
   * The attestation key pair is specific to a particular model of authenticator. Also called "batch attestation"
   */
  basic,

  /**
   * The authenticator does not have a specific attestation key pair. Instead, it uses the new credential private key to create the attestation
   * signature. This option is used for authenticators without meaningful protection measures for a dedicated attestation private key.
   */
  self,

  /**
   * The authenticator holds its own "endorsement key" and can use that to securely request "attestation identity key pairs" and corresponding
   * certificates from a trusted third party.
   */
  @JsonProperty("attestation-ca")
  attestationCa,

  /**
   * The authenticator uses an Anonymization CA to dynamically generate per-credential certificates. These produce a verifiable certificate chain
   * without providing uniquely identifiable information to the Relying Party.
   */
  @JsonProperty("anonymization-ca")
  anonymizationCa,

  /**
   * No attestation information is available.
   */
  none
}
