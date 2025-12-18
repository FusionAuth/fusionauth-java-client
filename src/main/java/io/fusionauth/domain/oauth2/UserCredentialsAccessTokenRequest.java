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
 * The request object for exchanging user credentials (username and password) for an access token.
 *
 * @author Lyle Schemmerling
 */
public class UserCredentialsAccessTokenRequest {
  /**
   * (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate. This
   * parameter is optional when the Authorization header is provided.
   * This parameter is optional when Basic Authorization is used to authenticate this request.
   */
  public String client_id;

  /**
   * (Optional) The client secret. This value may optionally be provided in the request body instead of the Authorization header.
   */
  public String client_secret;

  /**
   * The grant type to be used. This value must be set to password
   */
  public String grant_type;

  /**
   * The user’s password.
   */
  public String password;

  /**
   * (Optional) This parameter is optional and if omitted, the same scope requested during the authorization request will be used. If provided the
   * scopes must match those requested during the initial authorization request.
   */
  public String scope;

  /**
   * (Optional) The Id of the tenant to which this request is being made.
   */
  public String tenantId;

  /**
   * (Optional) The end-user verification code. This code is required if using this endpoint to approve the Device Authorization.
   */
  public String user_code;

  /**
   * The login identifier of the user. The login identifier can be either the email or the username.
   */
  public String username;
}
