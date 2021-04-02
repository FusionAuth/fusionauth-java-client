/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.reactor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Daniel DeGroff
 */
public class ReactorStatus {
  public ReactorFeatureStatus advancedRegistrationForms = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus breachedPasswordDetection = ReactorFeatureStatus.UNKNOWN;

  public Map<UUID, BreachedPasswordTenantMetric> breachedPasswordMetrics = new HashMap<>();

  public ReactorFeatureStatus connectors = ReactorFeatureStatus.UNKNOWN;

  public ReactorFeatureStatus entityManagement = ReactorFeatureStatus.UNKNOWN;

  public boolean licensed;

  public ReactorFeatureStatus multiFactorAuthentication = ReactorFeatureStatus.UNKNOWN;

  public int totalActionRequired() {
    return breachedPasswordMetrics.values().stream().mapToInt(m -> m.actionRequired).sum();
  }

  public int totalPasswordsBreached() {
    return breachedPasswordMetrics.values().stream().mapToInt(BreachedPasswordTenantMetric::totalBreached).sum();
  }

  public int totalPasswordsChecked() {
    return breachedPasswordMetrics.values().stream().mapToInt(m -> m.passwordsCheckedCount).sum();
  }

  public enum ReactorFeatureStatus {
    ACTIVE,
    DISCONNECTED,
    PENDING,
    UNKNOWN
  }
}
