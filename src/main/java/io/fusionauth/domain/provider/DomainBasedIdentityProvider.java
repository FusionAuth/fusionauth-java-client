/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.provider;

import java.util.HashSet;
import java.util.Set;

import io.fusionauth.domain.util.Normalizer;

/**
 * Interface for all identity providers that can be domain based.
 */
public interface DomainBasedIdentityProvider {
  /**
   * @return Any domains for the identity provider.
   */
  Set<String> getDomains();

  /**
   * Normalizes this IdP's domains by lower-casing them all.
   */
  default void normalizeDomains() {
    Normalizer.toLowerCase(getDomains(), HashSet::new);
  }
}
