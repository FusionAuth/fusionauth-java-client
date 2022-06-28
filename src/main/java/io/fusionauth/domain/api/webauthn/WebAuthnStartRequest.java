/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

import java.util.Map;
import java.util.UUID;

/**
 * @author Spencer Witt
 */
public class WebAuthnStartRequest implements Buildable<WebAuthnStartRequest> {
  public UUID applicationId;

  public String loginId;

  public Map<String, Object> state;

  @JacksonConstructor
  public WebAuthnStartRequest() {
  }

  @SuppressWarnings("unused")
  public WebAuthnStartRequest(UUID applicationId, String loginId, Map<String, Object> state) {
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.state = state;
  }
}
