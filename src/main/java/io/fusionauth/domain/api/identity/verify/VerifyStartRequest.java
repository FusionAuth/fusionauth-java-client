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
package io.fusionauth.domain.api.identity.verify;

import java.util.Map;
import java.util.UUID;

import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.VerificationStrategy;

/**
 * @author Brady Wied
 */
public class VerifyStartRequest implements Buildable<VerifyStartRequest> {
  public UUID applicationId;

  /**
   * When MustExist (default), loginId/loginIdType must correspond to a user in the tenant. When this is MustNotExist, verification can begin
   * without a user, in order to perform verification before user creation.
   */
  public ExistingUserStrategy existingUserStrategy = ExistingUserStrategy.mustExist;

  public String loginId;

  public String loginIdType;

  public Map<String, Object> state;

  public VerificationStrategy verificationStrategy;
}
