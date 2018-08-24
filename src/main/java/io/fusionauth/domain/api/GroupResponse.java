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

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Group;

/**
 * Group API response object.
 *
 * @author Daniel DeGroff
 */
public class GroupResponse {
  public Group group;

  public List<Group> groups;

  @JacksonConstructor
  public GroupResponse() {
  }

  public GroupResponse(Group group) {
    this.group = group;
  }

  public GroupResponse(List<Group> groups) {
    this.groups = groups;
  }
}
