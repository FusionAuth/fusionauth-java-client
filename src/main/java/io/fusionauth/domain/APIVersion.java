/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum APIVersion {
  V1("v1"),
  V2("v2");

  public static final Map<String, APIVersion> allVersions = Arrays.stream(APIVersion.values())
                                                                  .collect(Collectors.toMap(v -> v.version, v -> v));

  @JsonValue
  public final String version;

  APIVersion(String version) {
    this.version = version;
  }

  public boolean greaterThan(APIVersion other) {
    return version.compareTo(other.version) > 0;
  }
}
