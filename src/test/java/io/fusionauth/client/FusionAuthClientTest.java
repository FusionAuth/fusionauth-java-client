/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client;

import com.inversoft.error.Errors;
import io.fusionauth.domain.User;
import io.fusionauth.domain.api.UserRequest;
import io.fusionauth.domain.api.UserResponse;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordResponse;
import com.inversoft.rest.ClientResponse;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Daniel DeGroff
 */
public class FusionAuthClientTest {
  @Test(enabled = false)
  public void forgotPassword_with_and_without_api_key() throws Exception {
    FusionAuthClient apiKeyClient = new FusionAuthClient("api-key", "http://localhost:9011");

    ClientResponse<UserResponse, Errors> userResponse = apiKeyClient.retrieveUserByEmail("client_java@fusionauth.io");
    if (userResponse.status != 404) {
      apiKeyClient.deleteUser(userResponse.successResponse.user.id);
    }


    ClientResponse<UserResponse, Errors> response = apiKeyClient.createUser(null, new UserRequest(new User()
                                                                                                      .with(u -> u.email = "client_java@fusionauth.io")
                                                                                                      .with(u -> u.password = "password")));
    assertTrue(response.wasSuccessful());

    // w/ API key, success with response body
    ClientResponse<ForgotPasswordResponse, Errors> forgotPasswordResponse = apiKeyClient.forgotPassword(new ForgotPasswordRequest(response.successResponse.user.email, false));
    System.out.println(forgotPasswordResponse.status);
    assertTrue(forgotPasswordResponse.wasSuccessful());
    assertNotNull(forgotPasswordResponse.successResponse);
    assertNotNull(forgotPasswordResponse.successResponse.changePasswordId);


    // w/out API Key, success but no response body
    FusionAuthClient noApiKeyClient = new FusionAuthClient(null, "http://localhost:9011");
    forgotPasswordResponse = noApiKeyClient.forgotPassword(new ForgotPasswordRequest(response.successResponse.user.email, false));
    assertTrue(forgotPasswordResponse.wasSuccessful());
    assertNull(forgotPasswordResponse.successResponse);
  }
}
