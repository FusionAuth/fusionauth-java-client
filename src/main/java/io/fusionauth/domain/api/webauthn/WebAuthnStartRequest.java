/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.api.webauthn.enums.WebAuthnWorkflow;

/**
 * API request to start a WebAuthn authentication ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnStartRequest implements Buildable<WebAuthnStartRequest> {
  public UUID applicationId;

  public String loginId;

  public Map<String, Object> state;

  public WebAuthnWorkflow workflow;

  @JacksonConstructor
  public WebAuthnStartRequest() {
  }

  public WebAuthnStartRequest(UUID applicationId, String loginId, Map<String, Object> state, WebAuthnWorkflow workflow) {
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.state = state;
    this.workflow = workflow;
  }
}
