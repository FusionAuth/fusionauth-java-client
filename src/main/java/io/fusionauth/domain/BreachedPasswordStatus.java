/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
 * @author Daniel DeGroff
 */
public enum BreachedPasswordStatus {
  // No password breach has been found
  None,

  // An exact match on loginId and password was detected
  ExactMatch,

  // A match on a an email sub-address and password was detected
  SubAddressMatch,

  // A password match was detected but it did not match the email or username
  PasswordOnly,

  // A match was found in the list of common passwords, these are known breached passwords and not good to use
  CommonPassword
}
