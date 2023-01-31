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

import java.time.ZoneId;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class SystemLogsExportRequest extends BaseExportRequest {
  public boolean includeArchived;

  // Default to 64k
  public int lastNBytes = 64 * 1024;

  @JacksonConstructor
  public SystemLogsExportRequest() {
  }

  public SystemLogsExportRequest(String dateTimeSecondsFormat, ZoneId zoneId, int lastNBytes, boolean includeArchived) {
    this.dateTimeSecondsFormat = dateTimeSecondsFormat;
    this.zoneId = zoneId;
    this.lastNBytes = lastNBytes;
    this.includeArchived = includeArchived;
  }
}
