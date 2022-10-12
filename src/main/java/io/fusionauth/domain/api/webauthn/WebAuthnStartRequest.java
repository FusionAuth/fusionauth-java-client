/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

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
   * The optional login ID for the user. Either this or {@link WebAuthnStartRequest#userId} must be specified.
   */
  public String loginId;

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
