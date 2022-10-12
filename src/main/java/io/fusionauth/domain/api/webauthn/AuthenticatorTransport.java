/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
  usb("usb"),

  /**
   * Authenticator can be contacted over Near Field Communication (NFC)
   */
  nfc("nfc"),

  /**
   * Authenticator can be contacted over Bluetooth Smart/Bluetooth Low Energy (BLE)
   */
  ble("ble"),

  /**
   * Authenticator is contacted via a client device-specific transport (a platform authenticator)
   * <p>
   * Using 'platform' because 'internal' is a reserved word in C#.
   */
  platform("internal"),

  /**
   * Authenticator can be contacted using Google's "cloud-assisted BLE" pairing. Requires Chrome on host machine and Android device
   */
  cable("cable");

  private static final Map<String, AuthenticatorTransport> valueMap = new HashMap<>(AuthenticatorTransport.values().length);

  @JsonValue
  public final String value;

  AuthenticatorTransport(String value) {
    this.value = value;
  }

  @JsonCreator
  public static AuthenticatorTransport forValue(String value) {
    return valueMap.get(value);
  }

  static {
    for (AuthenticatorTransport transport : AuthenticatorTransport.values()) {
      valueMap.put(transport.value, transport);
    }
  }
}
