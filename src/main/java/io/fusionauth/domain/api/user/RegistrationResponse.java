/*
 * Copyright (c) 2018-2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.user;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;
import io.fusionauth.domain.api.UserResponse.VerificationId;

/**
 * Registration API request object.
 *
 * @author Brian Pontarelli
 */
public class RegistrationResponse {
  public String refreshToken;

  public UUID refreshTokenId;

  public UserRegistration registration;

  public String registrationVerificationId;

  public String registrationVerificationOneTimeCode;

  public String token;

  public ZonedDateTime tokenExpirationInstant;

  public User user;

  public List<VerificationId> verificationIds;

  @JacksonConstructor
  public RegistrationResponse() {
  }

  public RegistrationResponse(User user, UserRegistration registration) {
    this.user = user;
    this.registration = registration;
  }
}
