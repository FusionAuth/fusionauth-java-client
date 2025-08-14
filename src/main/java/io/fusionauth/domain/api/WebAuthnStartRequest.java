/*
 * Copyright (c) 2022-2024, FusionAuth, All Rights Reserved
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.webauthn.WebAuthnWorkflow;

/**
 * API request to start a WebAuthn authentication ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnStartRequest implements Buildable<WebAuthnStartRequest> {
  /**
   * The application ID a user is authenticating to
   */
  public UUID applicationId;

  /**
   * Optional database credential ID. If present, the authentication ceremony will be limited to the particular credential
   */
  public UUID credentialId;

  /**
   * The optional login Id for the user. Either this or {@link WebAuthnStartRequest#userId} must be specified.
   */
  public String loginId;

  public List<String> loginIdTypes = new ArrayList<>();

  /**
   * The OAuth2 state
   */
  public Map<String, Object> state;

  /**
   * The optional ID for the user. Either this or {@link WebAuthnStartRequest#loginId} must be specified
   */
  public UUID userId;

  /**
   * The WebAuthn workflow the user is in
   */
  public WebAuthnWorkflow workflow;

  @JacksonConstructor
  public WebAuthnStartRequest() {
  }

  public WebAuthnStartRequest(UUID applicationId, UUID userId, String loginId, UUID credentialId, Map<String, Object> state,
                              WebAuthnWorkflow workflow) {
    this.applicationId = applicationId;
    this.credentialId = credentialId;
    this.loginId = loginId;
    this.state = state;
    this.userId = userId;
    this.workflow = workflow;
  }
}
