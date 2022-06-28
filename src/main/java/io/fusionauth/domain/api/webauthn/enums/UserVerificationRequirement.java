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

/**
 * Used to express whether the Relying Party requires <a href="https://www.w3.org/TR/webauthn-2/#user-verification">user verification</a> for the
 * current operation.
 *
 * @author Spencer Witt
 */
public enum UserVerificationRequirement {
  /**
   * The Relying Party requires user verification for the operation and will fail without it
   */
  required,
  /**
   * The Relying Party prefers user verification for the operation but will not fail without it
   */
  preferred,
  /**
   * The Relying Party does not want user verification for the operation
   */
  discouraged
}
