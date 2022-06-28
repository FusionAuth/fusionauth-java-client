/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.api.webauthn.enums.AttestationConveyancePreference;

import java.util.List;

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
   * Information about desired properties of the credential to be created. Ordered from most- to least-preferred
   */
  public List<PublicKeyCredentialParameters> pubKeyCredParams;

  /**
   * Information about the Relying Party responsible for the request
   */
  public PublicKeyCredentialRpEntity rp;

  /**
   * The time the caller is willing to wait for the operation to complete in milliseconds. This value is treated as a hint and may be overridden by
   * the client
   */
  public long timeout = 180_000;

  /**
   * Data about the user account for which the Relying Party is requesting a credential
   */
  public PublicKeyCredentialUserEntity user;

  // TODO : WebAuthn - Requested Extensions

  @JacksonConstructor
  public PublicKeyCredentialCreationOptions() {
  }

  public PublicKeyCredentialCreationOptions(AttestationConveyancePreference attestation, AuthenticatorSelectionCriteria authenticatorSelection,
                                            String challenge, List<PublicKeyCredentialDescriptor> excludeCredentials,
                                            List<PublicKeyCredentialParameters> pubKeyCredParams, PublicKeyCredentialRpEntity rp, long timeout,
                                            PublicKeyCredentialUserEntity user) {
    this.attestation = attestation;
    this.authenticatorSelection = authenticatorSelection;
    this.challenge = challenge;
    this.excludeCredentials = excludeCredentials;
    this.pubKeyCredParams = pubKeyCredParams;
    this.rp = rp;
    this.timeout = timeout;
    this.user = user;
  }
}
