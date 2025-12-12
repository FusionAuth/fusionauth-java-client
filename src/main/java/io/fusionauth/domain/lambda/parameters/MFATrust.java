/*
 * Copyright (c) 2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.lambda.parameters;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents the inbound lambda parameter 'mfaTrust' inside the 'context' parameter for MFA Required lambdas.
 */
public class MFATrust {
  public final UUID applicationId;

  public final Map<String, String> attributes = new LinkedHashMap<>();

  public final ZonedDateTime expirationInstant;

  public final String id;

  public final ZonedDateTime insertInstant;

  public final StartInstant startInstants;

  public final Map<String, Object> state;

  public final UUID tenantId;

  public final UUID userId;

  public MFATrust(UUID applicationId, ZonedDateTime expirationInstant, String id, ZonedDateTime insertInstant,
                  StartInstant startInstants, Map<String, Object> state, UUID tenantId, UUID userId) {
    this.applicationId = applicationId;
    this.expirationInstant = expirationInstant;
    this.id = id;
    this.insertInstant = insertInstant;
    this.startInstants = startInstants;
    this.state = state;
    this.tenantId = tenantId;
    this.userId = userId;
  }

  public static class StartInstant {
    public final Map<UUID, ZonedDateTime> applications;

    public final ZonedDateTime tenant;

    public StartInstant(Map<UUID, ZonedDateTime> applications, ZonedDateTime tenant) {
      this.applications = applications;
      this.tenant = tenant;
    }
  }
}
