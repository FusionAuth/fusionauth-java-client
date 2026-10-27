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

package io.fusionauth.domain.lambda.parameters.tokenExchange;

import java.util.List;
import java.util.Map;

import io.fusionauth.domain.SubjectTokenValidationStatus;

/**
 * Immutable request data provided to a Token Exchange Policy Lambda.
 */
public class PolicyLambdaExchange {
  public final String clientId;

  public final List<String> requestedAudiencesAndResources;

  public final List<String> requestedScopes;

  public final Map<String, Object> subjectTokenClaims;

  public final SubjectTokenValidationStatus subjectTokenValidationStatus;

  public PolicyLambdaExchange(String clientId, List<String> requestedAudiencesAndResources, List<String> requestedScopes,
                              Map<String, Object> subjectTokenClaims,
                              SubjectTokenValidationStatus subjectTokenValidationStatus) {
    this.clientId = clientId;
    this.requestedAudiencesAndResources = requestedAudiencesAndResources;
    this.requestedScopes = requestedScopes;
    this.subjectTokenClaims = subjectTokenClaims;
    this.subjectTokenValidationStatus = subjectTokenValidationStatus;
  }
}
