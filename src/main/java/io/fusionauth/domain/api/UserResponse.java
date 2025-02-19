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
package io.fusionauth.domain.api;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.IdentityType;
import io.fusionauth.domain.User;

/**
 * User API response object.
 *
 * @author Brian Pontarelli
 */
public class UserResponse {
  public String emailVerificationId;

  public String emailVerificationOneTimeCode;

  public Map<UUID, String> registrationVerificationIds;

  public Map<UUID, String> registrationVerificationOneTimeCodes;

  public String token;

  public ZonedDateTime tokenExpirationInstant;

  public User user;

  public List<VerificationId> verificationIds;

  @JacksonConstructor
  public UserResponse() {
  }

  public UserResponse(User user) {
    this.user = user;
  }

  public static class VerificationId implements Buildable<VerificationId> {
    public String id;

    public String oneTimeCode;

    public IdentityType type;

    public String value;
  }
}
