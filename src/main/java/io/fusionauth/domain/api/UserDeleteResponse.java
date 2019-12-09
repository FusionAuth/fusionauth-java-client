/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import java.util.List;

import com.inversoft.json.JacksonConstructor;

/**
 * User API bulk response object.
 *
 * @author Trevor Smith
 */
public class UserDeleteResponse {
  public boolean dryRun;

  public boolean hardDelete;

  public int total;

  public List<String> userIds;

  @JacksonConstructor
  public UserDeleteResponse() {
  }

  public UserDeleteResponse(List<String> userIds, int total, boolean hardDelete, boolean dryRun) {
    this.userIds = userIds;
    this.total = total;
    this.hardDelete = hardDelete;
    this.dryRun = dryRun;
  }
}
