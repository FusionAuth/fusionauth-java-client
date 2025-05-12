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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.SendSetPasswordIdentityType;
import io.fusionauth.domain.User;

/**
 * User API request object.
 *
 * @author Brian Pontarelli
 */
public class UserRequest extends BaseEventRequest implements Buildable<UserRequest> {
  public UUID applicationId;

  // Used for password update. If it's provided, it'll be validated.
  public String currentPassword;

  public boolean disableDomainBlock;

  /**
   * @deprecated This field is deprecated since 1.99.9 and will be removed in a future version. Use {@link #sendSetPasswordIdentityType} instead.
   */
  @Deprecated
  public boolean sendSetPasswordEmail;

  public SendSetPasswordIdentityType sendSetPasswordIdentityType = SendSetPasswordIdentityType.doNotSend;

  public boolean skipVerification;

  public User user;

  public List<String> verificationIds = new ArrayList<>();

  @JacksonConstructor
  public UserRequest() {
  }

  public UserRequest(User user) {
    this.sendSetPasswordEmail = false;
    this.sendSetPasswordIdentityType = SendSetPasswordIdentityType.doNotSend;
    this.skipVerification = true;
    this.user = user;
  }

  public UserRequest(boolean sendSetPasswordEmail, boolean skipVerification, User user) {
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
    this.user = user;
  }

  public UserRequest(EventInfo eventInfo, User user) {
    super(eventInfo);
    this.sendSetPasswordEmail = false;
    this.skipVerification = true;
    this.user = user;
  }

  public UserRequest(EventInfo eventInfo, UUID applicationId, boolean sendSetPasswordEmail, boolean skipVerification, String currentPassword,
                     User user) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.currentPassword = currentPassword;
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
    this.user = user;
  }
}
