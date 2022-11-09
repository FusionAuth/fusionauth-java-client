/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes the <a href="https://www.w3.org/TR/webauthn-2/#authenticator-attachment-modality">authenticator attachment modality</a>.
 *
 * @author Spencer Witt
 */
public enum AuthenticatorAttachment {
  /**
   * Attached using a client device-specific transport and is usually not removable from the device (e.g. Face ID, fingerprint scanner)
   */
  @JsonProperty("platform")
  platform,

  /**
   * Roaming authenticators that are removable and can move between devices (e.g. Yubikey, Bluetooth/NFC device)
   */
  @JsonProperty("cross-platform")
  crossPlatform
}
