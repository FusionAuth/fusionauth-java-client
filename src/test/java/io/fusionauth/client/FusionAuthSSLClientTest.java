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
package io.fusionauth.client;

import com.inversoft.error.Errors;
import com.inversoft.rest.ClientResponse;
import io.fusionauth.domain.User;
import io.fusionauth.domain.api.UserRequest;
import io.fusionauth.domain.api.UserResponse;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordResponse;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author zcyang
 * @description fusionauth ssl client test
 */
public class FusionAuthSSLClientTest {
  @Test
  public void forgotPassword_with_and_without_api_key() {
    String fusionauthURL = System.getenv().getOrDefault("FUSIONAUTH_URL", "https://localhost:9011");
    String fusionauthApiKey = System.getenv().getOrDefault("FUSIONAUTH_API_KEY", "api-key");
    FusionAuthClient apiKeyClient = new FusionAuthClient(fusionauthApiKey, fusionauthURL, Boolean.TRUE, "BASE64 Encode ssl certificate value");

    ClientResponse<UserResponse, Errors> userResponse = apiKeyClient.retrieveUserByEmail("client_java@fusionauth.io");
    if (userResponse.status != 404) {
      apiKeyClient.deleteUser(userResponse.successResponse.user.id);
    }

    ClientResponse<UserResponse, Errors> response = apiKeyClient.createUser(null, new UserRequest(null, new User()
        .with(u -> u.email = "client_java@fusionauth.io")
        .with(u -> u.password = "password")));
    assertTrue(response.wasSuccessful());

    // w/ API key, success with response body
    ClientResponse<ForgotPasswordResponse, Errors> forgotPasswordResponse = apiKeyClient.forgotPassword(new ForgotPasswordRequest(response.successResponse.user.email, false));
    assertTrue(forgotPasswordResponse.wasSuccessful());
    assertNotNull(forgotPasswordResponse.successResponse);
    assertNotNull(forgotPasswordResponse.successResponse.changePasswordId);


    // w/out API Key, success but no response body
    FusionAuthClient noApiKeyClient = new FusionAuthClient(null, fusionauthURL, Boolean.TRUE, "BASE64 Encode ssl certificate value");
    forgotPasswordResponse = noApiKeyClient.forgotPassword(new ForgotPasswordRequest(response.successResponse.user.email, false));
    assertTrue(forgotPasswordResponse.wasSuccessful());
    assertNull(forgotPasswordResponse.successResponse);
  }
}
