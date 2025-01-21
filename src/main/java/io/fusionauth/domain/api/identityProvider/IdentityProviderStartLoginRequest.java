/*
 * Copyright (c) 2019-2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.identityProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.api.BaseLoginRequest;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderStartLoginRequest extends BaseLoginRequest implements Buildable<IdentityProviderStartLoginRequest> {
  public Map<String, String> data;

  public UUID identityProviderId;

  public String loginId;

  public List<String> loginIdTypes = new ArrayList<>();

  public Map<String, Object> state;

  @JacksonConstructor
  public IdentityProviderStartLoginRequest() {
  }

  public IdentityProviderStartLoginRequest(UUID applicationId, Map<String, String> data, UUID identityProviderId) {
    this.applicationId = applicationId;
    this.data = data;
    this.identityProviderId = identityProviderId;
  }

  public IdentityProviderStartLoginRequest(UUID applicationId, UUID identityProviderId, String loginId, String ipAddress) {
    this.applicationId = applicationId;
    this.identityProviderId = identityProviderId;
    this.loginId = loginId;

    if (ipAddress != null) {
      eventInfo = eventInfo != null ? eventInfo : new EventInfo();
      eventInfo.ipAddress = ipAddress;
    }
  }

  public IdentityProviderStartLoginRequest(UUID applicationId, UUID identityProviderId, String loginId, String ipAddress,
                                           Map<String, Object> state) {
    this.applicationId = applicationId;
    this.identityProviderId = identityProviderId;
    this.loginId = loginId;
    this.state = state;

    if (ipAddress != null) {
      eventInfo = eventInfo != null ? eventInfo : new EventInfo();
      eventInfo.ipAddress = ipAddress;
    }
  }
}
