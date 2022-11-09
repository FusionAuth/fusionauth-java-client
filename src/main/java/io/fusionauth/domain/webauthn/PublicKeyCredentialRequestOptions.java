/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;

/**
 * Provides the <i>authenticator</i> with the data it needs to generate an assertion.
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialRequestOptions implements Buildable<PublicKeyCredentialRequestOptions> {
  /**
   * List of credentials acceptable to the caller in order from most- to least-preferred
   */
  public List<PublicKeyCredentialDescriptor> allowCredentials;

  /**
   * The challenge the <i>authenticator</i> must sign to produce an authentication assertion in base64URL-encoded format
   */
  public String challenge;

  /**
   * A unique identifier for the Relying Party. Defaults to the calling host's <a
   * href="https://html.spec.whatwg.org/multipage/origin.html#concept-origin-effective-domain">effective domain</a>
   */
  @JsonProperty("rpId")
  public String relyingPartyId;

  /**
   * The time the caller is willing to wait for the operation to complete in milliseconds. This value is treated as a hint and may be overridden by
   * the client
   */
  public long timeout;

  /**
   * Specifies the Relying Party's requirements for user verification. <i>Authenticators</i> default this to
   * {@link UserVerificationRequirement#preferred}.
   */
  public UserVerificationRequirement userVerification;
}
