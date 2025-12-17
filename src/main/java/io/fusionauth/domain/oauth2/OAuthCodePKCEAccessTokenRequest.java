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
 * The request object to make a request to the Token endpoint to exchange the authorization code returned from the Authorize endpoint and a
 * code_verifier for an access token.
 *
 * @author Lyle Schemmerling
 */
public class OAuthCodePKCEAccessTokenRequest {
  /**
   * (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate.
   * This parameter is optional when the Authorization header is provided.
   */
  public String client_id;

  /**
   * (Optional) The client secret. This value may optionally be provided in the request body instead of the Authorization header.
   */
  public String client_secret;

  /**
   * The authorization code returned on the /oauth2/authorize response.
   */
  public String code;

  /**
   * The random string generated previously. Will be compared with the code_challenge sent previously, which allows the OAuth provider to authenticate
   * your app.
   */
  public String code_verifier;

  /**
   * The grant type to be used. This value must be set to authorization_code
   */
  public String grant_type;

  /**
   * The URI to redirect to upon a successful request.
   */
  public String redirect_uri;

  /**
   * (Optional) The Id of the tenant to which this request is being made.
   */
  public UUID tenantId;
}
