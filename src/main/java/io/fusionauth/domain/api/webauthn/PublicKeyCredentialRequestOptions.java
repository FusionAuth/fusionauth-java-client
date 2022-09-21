/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
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

import java.util.List;

import com.inversoft.json.JacksonConstructor;
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
  public String rpId;

  /**
   * The time the caller is willing to wait for the operation to complete in milliseconds. This value is treated as a hint and may be overridden by
   * the client
   */
  public long timeout = 180_000;

  /**
   * Specifies the Relying Party's requirements for user verification. <i>Authenticators</i> default this to
   * {@link UserVerificationRequirement#preferred}.
   */
  public UserVerificationRequirement userVerification;

  // TODO : WebAuthn - Extensions

  @JacksonConstructor
  public PublicKeyCredentialRequestOptions() {
  }
}
