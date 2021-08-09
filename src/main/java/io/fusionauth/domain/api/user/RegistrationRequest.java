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
package io.fusionauth.domain.api.user;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * Registration API request object.
 *
 * @author Brian Pontarelli
 */
public class RegistrationRequest extends BaseEventRequest {
  public boolean disableDomainBlock;

  public boolean generateAuthenticationToken;

  public UserRegistration registration;

  public boolean sendSetPasswordEmail;

  public boolean skipRegistrationVerification;

  public boolean skipVerification;

  public User user;

  @JacksonConstructor
  public RegistrationRequest() {
  }

  public RegistrationRequest(User user, UserRegistration registration) {
    this.user = user;
    this.registration = registration;
  }

  public RegistrationRequest(User user, UserRegistration registration, boolean sendSetPasswordEmail, boolean skipVerification) {
    this.user = user;
    this.registration = registration;
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
  }

  public RegistrationRequest(EventInfo eventInfo, User user, UserRegistration registration) {
    super(eventInfo);
    this.user = user;
    this.registration = registration;
  }

  public RegistrationRequest(EventInfo eventInfo, User user, UserRegistration registration, boolean sendSetPasswordEmail, boolean skipVerification) {
    super(eventInfo);
    this.user = user;
    this.registration = registration;
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
  }
}
