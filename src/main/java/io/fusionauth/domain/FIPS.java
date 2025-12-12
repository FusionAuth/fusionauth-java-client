/*
 * Copyright (c) 2025-2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

/**
 * Determines if FusionAuth is in FIPS mode based on the system property <code>fusionauth.fips.enabled</code>. This can only be enabled once and
 * should be enabled when the VM starts or as close to that point as possible.
 * <p>
 * Once this has been enabled, it cannot be disabled.
 * <p>
 * This also provides some helpers for FIPS things such as password length requirements.
 *
 * @author Brian Pontarelli & Daniel DeGroff
 */
public class FIPS {
  public static final int FIPS_MIN_PASSWORD_LENGTH = 14;

  public static final int STANDARD_MIN_PASSWORD_LENGTH = 8;

  private static volatile Boolean Enabled;

  /**
   * Lazily determines if the System configuration is set to enable FIPS mode. This is done on the first call to this method. Subsequent calls return
   * the cached value, regardless of the System properties changing.
   *
   * @return Whether or not FIPS is enabled.
   */
  public static boolean isEnabled() {
    if (Enabled != null) {
      return Enabled;
    }

    Enabled = Boolean.getBoolean("fusionauth.fips.enabled");
    return Boolean.TRUE.equals(Enabled);
  }

  /**
   * Returns the minimum password length requirement, which depends on whether FusionAuth is operating in FIPS mode.
   * If FIPS mode is enabled, the minimum password length will be {@code FIPS_MIN_PASSWORD_LENGTH}.
   * Otherwise, the minimum password length will be {@code STANDARD_MIN_PASSWORD_LENGTH}.
   *
   * @return The minimum password length, either {@code FIPS_MIN_PASSWORD_LENGTH} when FIPS mode is enabled or {@code STANDARD_MIN_PASSWORD_LENGTH}
   * when it is not.
   */
  public static int minimumPasswordLength() {
    return isEnabled() ? FIPS_MIN_PASSWORD_LENGTH : STANDARD_MIN_PASSWORD_LENGTH;
  }
}
