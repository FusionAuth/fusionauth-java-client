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
 * The request object to approve a device grant.
 *
 * @author Lyle Schemmerling
 */
public class DeviceApprovalRequest {
  /**
   * (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate.
   */
  public String client_id;

  /**
   * (Optional) The client secret. This value will be required if client authentication is enabled.
   */
  public String client_secret;

  /**
   * (Optional) The Id of the tenant to which this request is being made.
   */
  public UUID tenantId;

  /**
   * The access token used to identify the user.
   */
  public String token;

  /**
   * The end-user verification code.
   */
  public String user_code;
}
