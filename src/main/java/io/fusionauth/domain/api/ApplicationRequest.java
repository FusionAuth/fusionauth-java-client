/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Application;
import io.fusionauth.domain.ApplicationRole;
import io.fusionauth.domain.EventInfo;

/**
 * The Application API request object.
 *
 * @author Brian Pontarelli
 */
public class ApplicationRequest extends BaseEventRequest {
  public Application application;

  public ApplicationRole role;

  public UUID sourceApplicationId;

  @JacksonConstructor
  public ApplicationRequest() {
  }

  public ApplicationRequest(Application application) {
    this.application = application;
  }

  public ApplicationRequest(ApplicationRole role) {
    this.role = role;
  }

  public ApplicationRequest(EventInfo info, Application application) {
    super(info);
    this.application = application;
  }

  public ApplicationRequest(EventInfo info, ApplicationRole role) {
    super(info);
    this.role = role;
  }
}
