/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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

/**
 * @author Daniel DeGroff
 */
public enum IdentityProviderType {
  Apple(UUID.fromString("13d2a5db-7ef9-4d62-b909-0df58612e775")),

  EpicGames(UUID.fromString("1b932b19-61a8-47c7-9e81-27dbf9011dad")),

  ExternalJWT(null),

  Facebook(UUID.fromString("56abdcc7-8bd9-4321-9621-4e9bbebae494")),

  Google(UUID.fromString("82339786-3dff-42a6-aac6-1f1ceecb6c46")),

  HYPR(UUID.fromString("778985b7-6fd8-414d-acf2-94f18fb7c7e0")),

  LinkedIn(UUID.fromString("6177c09d-3f0e-4d53-9504-3600b1b23f46")),

  Nintendo(UUID.fromString("b0ac2e16-d4af-483e-98c8-7f6693610665")),

  OpenIDConnect(null),

  SAMLv2(null),

  SAMLv2IdPInitiated(null),

  SonyPSN(UUID.fromString("7764b5c7-165b-4e7e-94aa-02ebe2a0a5fb")),

  Steam(UUID.fromString("e4f39345-7833-4b1d-b331-ca03bdc2c4be")),

  Twitch(UUID.fromString("bf4cf83f-e824-42d7-b4a3-5b10847a66b2")),

  Twitter(UUID.fromString("45bb233c-0901-4236-b5ca-ac46e2e0a5a5")),

  Xbox(UUID.fromString("af53ab21-34c3-468a-8ba2-ecb3905f67f2"));

  public final UUID id;

  IdentityProviderType(UUID id) {
    this.id = id;
  }

  public static IdentityProviderType safeValueOf(String value) {
    if (value == null) {
      return null;
    }

    try {
      return IdentityProviderType.valueOf(value);
    } catch (Exception e) {
      return null;
    }
  }

  public String displayName() {
    if (this == EpicGames) {
      return "Epic Games";
    } else if (this == SonyPSN) {
      return "Sony PlayStation Network";
    } else {
      return name();
    }
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
