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

import java.util.Map;
import java.util.Set;

import io.fusionauth.domain.AuthenticationThreats;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.UserRegistration;

/**
 * Represents the inbound lambda parameter 'context' for MFA Required lambdas.
 */
public class MFAContext {
  public final Set<AuthenticationThreats> authenticationThreats;

  public final EventInfo eventInfo;

  public final Map<String, Object> jwt;

  public final MFATrust mfaTrust;

  public final UserRegistration registration;

  public MFAContext(EventInfo eventInfo, Set<AuthenticationThreats> authenticationThreats, UserRegistration registration,
                    MFATrust mfaTrust,
                    Map<String, Object> jwt) {
    this.eventInfo = eventInfo;
    this.authenticationThreats = authenticationThreats;
    this.registration = registration;
    this.mfaTrust = mfaTrust;
    this.jwt = jwt;
  }
}
