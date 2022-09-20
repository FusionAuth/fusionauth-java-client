/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn.enums;

/**
 * Describes how the authenticator communicates with a client. This can be used by the client as a hint to locate the
 * appropriate authenticator.
 *
 * @author Spencer Witt
 */
public enum AuthenticatorTransport {
  /**
   * Authenticator can be contacted over removable USB
   */
  usb,
  /**
   * Authenticator can be contacted over Near Field Communication (NFC)
   */
  nfc,
  /**
   * Authenticator can be contacted over Bluetooth Smart/Bluetooth Low Energy (BLE)
   */
  ble,
  /**
   * Authenticator is contacted via a client device-specific transport (a platform authenticator)
   */
  internal,
  /**
   * Authenticator can be contacted using Google's "cloud-assisted BLE" pairing. Requires Chrome on host machine and Android device
   */
  cable
}
