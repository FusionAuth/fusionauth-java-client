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
package io.fusionauth.domain.oauth2;

import java.util.UUID;

/**
 * @author Lyle Schemmerling
 */
public class DeviceAuthorizationRequest {
  /**
   * The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate.
   */
  public String client_id;

  /**
   * (Optional) The client secret. This value may optionally be provided in the request body instead of the Authorization header.
   */
  public String client_secret;

  /**
   * (Optional) A space-delimited string of the requested scopes. Defaults to all scopes configured in the Application's OAuth configuration.
   */
  public String scope;

  /**
   * (Optional) The Id of the tenant to use for this request.
   */
  public UUID tenantId;
}
