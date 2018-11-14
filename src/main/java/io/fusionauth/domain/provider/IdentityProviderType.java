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

import java.util.UUID;

public enum IdentityProviderType {
  ExternalJWT(null),

  OpenIDConnect(null),

  Facebook(UUID.fromString("56abdcc7-8bd9-4321-9621-4e9bbebae494")),

  Google(UUID.fromString("82339786-3dff-42a6-aac6-1f1ceecb6c46")),

  Twitter(UUID.fromString("45bb233c-0901-4236-b5ca-ac46e2e0a5a5"));

  public final UUID id;

  IdentityProviderType(UUID id) {
    this.id = id;
  }

  /**
   * @return the fixed Id for the type, or generate a new UUID.
   */
  public UUID getId() {
    if (id == null) {
      return UUID.randomUUID();
    }

    return id;
  }
}