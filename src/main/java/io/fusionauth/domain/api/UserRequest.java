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
package io.fusionauth.domain.api;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.User;

/**
 * User API request object.
 *
 * @author Brian Pontarelli
 */
public class UserRequest {
  public boolean sendSetPasswordEmail;

  public boolean skipVerification;

  public User user;

  @JacksonConstructor
  public UserRequest() {
  }

  public UserRequest(User user) {
    this.sendSetPasswordEmail = false;
    this.skipVerification = true;
    this.user = user;
  }

  public UserRequest(boolean sendSetPasswordEmail, boolean skipVerification, User user) {
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
    this.user = user;
  }
}
