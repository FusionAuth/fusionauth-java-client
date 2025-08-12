/*
 * Copyright (c) 2024-2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.client.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.fusionauth.domain.IdentityType;

/**
 * FusionAuth specific Jackson bindings. This can be used in the public FusionAuth client.
 *
 * @author Daniel DeGroff
 */
public class FusionAuthJacksonModule extends SimpleModule {
  private static final Version VERSION = VersionUtil.parseVersion("1.59.0", "io.fusionauth", "fusionauth-app");

  public FusionAuthJacksonModule() {
    super(VERSION);
    addSerializer(IdentityType.class, new IdentityTypeSerializer());
    addDeserializer(IdentityType.class, new IdentityTypeDeserializer());
  }
}
