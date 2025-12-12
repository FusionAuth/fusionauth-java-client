/*
 * Copyright (c) 2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.lambda.parameters;

import io.fusionauth.domain.ApplicationMultiFactorTrustPolicy;
import io.fusionauth.domain.MultiFactorLoginPolicy;

/**
 * Represents the inbound lambda parameter 'policies' for MFA Required lambdas.
 */
public class MFAPolicies {
  public final MultiFactorLoginPolicy applicationLoginPolicy;

  public final ApplicationMultiFactorTrustPolicy applicationMultiFactorTrustPolicy;

  public final MultiFactorLoginPolicy tenantLoginPolicy;

  public MFAPolicies(MultiFactorLoginPolicy applicationLoginPolicy,
                     ApplicationMultiFactorTrustPolicy applicationMultiFactorTrustPolicy,
                     MultiFactorLoginPolicy tenantLoginPolicy) {
    this.applicationLoginPolicy = applicationLoginPolicy;
    this.tenantLoginPolicy = tenantLoginPolicy;
    this.applicationMultiFactorTrustPolicy = applicationMultiFactorTrustPolicy;
  }
}
