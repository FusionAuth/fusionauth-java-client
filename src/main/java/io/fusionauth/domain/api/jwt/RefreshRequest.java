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
package io.fusionauth.domain.api.jwt;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * @author Daniel DeGroff
 */
public class RefreshRequest extends BaseEventRequest implements Buildable<RefreshRequest> {
  public String refreshToken;

  // TODO : ENG-1731 : Daniel : Is this ok, needs to be documented, tested and reviewed.
  // Optional reduced TTL
  public Integer timeToLiveInSeconds;

  public String token;

  @JacksonConstructor
  public RefreshRequest() {
  }

  public RefreshRequest(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public RefreshRequest(EventInfo eventInfo, String refreshToken) {
    super(eventInfo);
    this.refreshToken = refreshToken;
  }
}
