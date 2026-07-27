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
package io.fusionauth.domain.api;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusionauth.domain.Buildable;

/**
 * Request to complete the WebAuthn registration ceremony for a new credential,.
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterCompleteRequest implements Buildable<WebAuthnRegisterCompleteRequest> {
  /**
   * Details on the new public key credential
   */
  public WebAuthnPublicKeyRegistrationRequest credential;

  /**
   * The request origin
   *
   * @deprecated This value is ignored. The origin is validated using the signed WebAuthn client data, not this request value. It remains
   * for backward compatibility and has no effect.
   */
  @Deprecated // (since = "1.69.0")
  public String origin;

  /**
   * The Relying Party Id
   *
   * @deprecated This value is ignored. The Relying Party Id is resolved from the tenant configuration and validated against the signed
   * authenticator data, not from this request value. It remains for backward compatibility and has no effect.
   */
  @Deprecated // (since = "1.69.0")
  @JsonProperty("rpId")
  public String relyingPartyId;

  /**
   * The User's database identifier
   */
  public UUID userId;
}
