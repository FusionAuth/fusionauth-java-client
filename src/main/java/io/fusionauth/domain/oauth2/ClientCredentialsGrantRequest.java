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

/**
 * The request object to make a Client Credentials grant request to obtain an access token.
 *
 * @author Lyle Schemmerling
 */
public class ClientCredentialsGrantRequest {
  /**
   * (Optional) The unique Id of the FusionAuth Application to use for this request. If not provided, the client_id must be a
   * valid API key.
   */
  public String client_id;

  /**
   * (Optional) The client secret used to authenticate this request.
   * This parameter is optional when Basic Authorization is used to authenticate this request.
   */
  public String client_secret;

  /**
   * The grant type to be used. This value must be set to client_credentials
   */
  public String grant_type;

  /**
   * (Optional) This parameter is used to indicate which target entity you are requesting access. To request access to an entity, use the format
   * target-entity:&lt;target-entity-id&gt;:&lt;roles&gt;. Roles are an optional comma separated list.
   */
  public String scope;

  /**
   * (Optional) The Id of the tenant to which this request is being made.
   */
  public String tenantId;
}
