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
 * @author Daniel DeGroff
 */
public enum TenantIdentityConfigurationMode {
  /**
   * When operating in compatibility mode you may continue to utilize top level email and username fields and optionally start using discrete
   * identities.
   * <p>
   * When in this mode, the top level email and username represent the primary email and primary username identities, if defined.
   */
  Compatible,

  /**
   * When operating in Discrete Identity mode, you may only manage identities in the top level User Identities field.
   * <p>
   * When this mode is enabled, you may no longer utilize the top level email and username fields in the User object.
   */
  Discrete
}
