/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;

/**
 * Allows the Relying Party to specify desired attributes of a new credential.
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialCreationOptions implements Buildable<PublicKeyCredentialCreationOptions> {
  /**
   * Used by the Relying Party to express preference for <i>attestation conveyance</i>
   */
  public AttestationConveyancePreference attestation = AttestationConveyancePreference.none;

  /**
   * Used by the Relying Party to filter eligible <i>authenticators</i> to those with capabilities that meet its needs
   */
  public AuthenticatorSelectionCriteria authenticatorSelection;

  /**
   * Challenge intended for generating a new credential in base64URL-encoded format
   */
  public String challenge;

  /**
   * <i>Authenticators</i> that contain one of the credentials described here will be excluded from consideration for creating a new credential. This
   * is intended for Relying Parties to limit the creation of multiple credentials for the same account on a single <i>authenticator</i>
   */
  public List<PublicKeyCredentialDescriptor> excludeCredentials;

  /**
   * Use to request the use of extensions during credential registration
   */
  public WebAuthnRegistrationExtensionOptions extensions;

  /**
   * Information about desired properties of the credential to be created. Ordered from most- to least-preferred
   */
  public List<PublicKeyCredentialParameters> pubKeyCredParams;

  /**
   * Information about the Relying Party responsible for the request
   */
  @JsonProperty("rp")
  public PublicKeyCredentialRelyingPartyEntity relyingParty;

  /**
   * The time the caller is willing to wait for the operation to complete in milliseconds. This value is treated as a hint and may be overridden by
   * the client
   */
  public long timeout;

  /**
   * Data about the user account for which the Relying Party is requesting a credential
   */
  public PublicKeyCredentialUserEntity user;
}
