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
package io.fusionauth.domain.api.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
@SuppressWarnings("unused")
public class SendRequest {
  public List<String> bccAddresses;

  public List<String> ccAddresses;

  public Map<String, Object> requestData;

  public List<UUID> userIds;

  @JacksonConstructor
  public SendRequest() {
  }

  public SendRequest(List<UUID> userIds, List<String> ccAddresses, List<String> bccAddresses, Map<String, Object> requestData) {
    this.userIds = userIds;
    this.ccAddresses = ccAddresses;
    this.bccAddresses = bccAddresses;
    this.requestData = requestData;
  }

  public SendRequest(List<UUID> userIds, Map<String, Object> requestData) {
    this.userIds = userIds;
    this.requestData = requestData;
  }

  public SendRequest(List<UUID> userIds) {
    this.userIds = userIds;
  }

  public SendRequest normalize() {
    requestData = Optional.ofNullable(requestData).orElseGet(HashMap::new);
    userIds = Optional.ofNullable(userIds).orElseGet(ArrayList::new);
    ccAddresses = Optional.ofNullable(ccAddresses).orElseGet(ArrayList::new);
    bccAddresses = Optional.ofNullable(bccAddresses).orElseGet(ArrayList::new);
    return this;
  }
}
