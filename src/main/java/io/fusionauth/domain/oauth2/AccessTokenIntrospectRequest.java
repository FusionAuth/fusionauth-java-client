/*
 * Copyright (c) 2025-2026, FusionAuth, All Rights Reserved
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
 * The request object for introspecting an access token.
 *
 * @author Lyle Schemmerling
 */
public class AccessTokenIntrospectRequest {

  /**
   * The unique client identifier. The client Id is the Id of the FusionAuth Application for which this token was generated.
   */
  public String client_id;

  /**
   * (Optional) The Id of the tenant to which this request is being made.
   */
  public String tenantId;

  /**
   * The access token returned by this OAuth provider as the result of a successful client credentials grant.
   */
  public String token;

  /**
   * An optional hint to identify the token type.
   * <p>
   * When this value is omitted, the token can be an access_token or id_token. When this value is specified, it must match the token type.
   * <p>
   * In order to use a refresh token, this value must be set to refresh_token.
   */
  public String token_type_hint;
}
