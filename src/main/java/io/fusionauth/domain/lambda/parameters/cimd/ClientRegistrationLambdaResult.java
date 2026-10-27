/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.lambda.parameters.cimd;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.fusionauth.domain.CimdClientRegistrationDecision;
import io.fusionauth.domain.CimdDomainOAuthScope;
import io.fusionauth.domain.oauth2.ProvidedScopePolicy;

/**
 * Represents the outbound parameter 'result' for CIMD Client Registration lambdas. The parameter is pre-populated with defaults from the CIMD Domain
 * configuration
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRegistrationLambdaResult {
  /**
   * Defaults to ALLOW. Matching is case-sensitive, but surrounding whitespace is ignored. A missing or null decision is treated as DENY,
   * and a value that is not one of the enum constants fails the lambda invocation, which is also treated as DENY.
   */
  public CimdClientRegistrationDecision decision = CimdClientRegistrationDecision.ALLOW;

  public UUID jwtPopulateLambdaId;

  public ProvidedScopePolicy providedScopePolicy;

  public List<CimdDomainOAuthScope> scopes;

  public Integer ttlInSeconds;
}
