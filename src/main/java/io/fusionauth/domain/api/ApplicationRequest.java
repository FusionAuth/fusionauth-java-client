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
package io.fusionauth.domain.api;

import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Application;
import io.fusionauth.domain.ApplicationRole;

/**
 * The Application API request object.
 *
 * @author Brian Pontarelli
 */
public class ApplicationRequest {
  public Application application;

  public ApplicationRole role;

  public List<UUID> webhookIds;

  @JacksonConstructor
  public ApplicationRequest() {
  }

  public ApplicationRequest(Application application, List<UUID> webhookIds) {
    this.application = application;
    this.webhookIds = webhookIds;
  }

  public ApplicationRequest(ApplicationRole role) {
    this.role = role;
  }
}
