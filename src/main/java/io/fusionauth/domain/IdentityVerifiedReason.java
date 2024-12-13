/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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
 * Models the reason that {@link UserIdentity#verified} was set to true or false.
 *
 * @author Brady Wied
 */
public enum IdentityVerifiedReason {
  /**
   * Verification was skipped due to the `skipVerification` parameter
   */
  Skipped, // 0
  /**
   * Identity was created via an identity provider or a connector
   */
  Trusted, // 1
  /**
   * We don't know how to verify this identity type
   */
  Unverifiable, // 2
  /**
   * Happened implicitly by sending a set password or passwordless message that can only be
   * acted on if the user proves control of the identity.
   */
  Implicit, // 3
  /**
   * No verification has been performed yet
   */
  Pending, // 4
  /**
   * Verification was performed by FusionAuth
   */
  Completed, // 5
  /**
   * Tenant policy did not require verifying this identity
   */
  Disabled // 6
}
