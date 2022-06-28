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
package io.fusionauth.domain.api.webauthn.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes the <a href="https://www.w3.org/TR/webauthn-2/#authenticator-attachment-modality">authenticator attachment modality</a>.
 *
 * @author Spencer Witt
 */
public enum AuthenticatorAttachment {
  /**
   * Attached using a client device-specific transport and is usually not removable from the device (e.g. FaceID, fingerprint scanner)
   */
  @JsonProperty("platform")
  PLATFORM,
  /**
   * Roaming authenticators that are removable and can move between devices (e.g. Yubikey, Bluetooth/NFC device)
   */
  @JsonProperty("cross-platform")
  CROSS_PLATFORM
}
