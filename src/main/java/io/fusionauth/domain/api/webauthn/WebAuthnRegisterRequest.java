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
 * API request to start a WebAuthn registration ceremony
 *
 * @author Spencer Witt
 */
public class WebAuthnRegisterRequest implements Buildable<WebAuthnRegisterRequest> {
  public Map<String, Object> state;

  public UUID userId;

  public WebAuthnWorkflow workflow;

  @JacksonConstructor
  public WebAuthnRegisterRequest() {
  }
}
