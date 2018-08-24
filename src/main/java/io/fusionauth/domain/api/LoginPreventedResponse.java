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

import java.time.ZonedDateTime;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.UserActionLog;

/**
 * The summary of the action that is preventing login to be returned on the login response.
 *
 * @author Daniel DeGroff
 */
public class LoginPreventedResponse implements Buildable<LoginPreventedResponse> {
  public UUID actionId;

  public UUID actionerUserId;

  public ZonedDateTime expiry;

  public String localizedName;

  public String localizedOption;

  public String localizedReason;

  public String name;

  public String option;

  public String reason;

  public String reasonCode;

  public LoginPreventedResponse(UserActionLog userActionLog) {
    this.actionId = userActionLog.userActionId;
    this.actionerUserId = userActionLog.actionerUserId;
    this.expiry = userActionLog.expiry;
    this.localizedName = userActionLog.localizedName;
    this.localizedReason = userActionLog.localizedReason;
    this.name = userActionLog.name;
    this.reason = userActionLog.reason;
    this.reasonCode = userActionLog.reasonCode;
  }

  @JacksonConstructor
  public LoginPreventedResponse() {
  }
}
