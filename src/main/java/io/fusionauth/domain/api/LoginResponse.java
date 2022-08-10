/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.AuthenticationThreats;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.ChangePasswordReason;
import io.fusionauth.domain.TwoFactorMethod;
import io.fusionauth.domain.User;

/**
 * @author Brian Pontarelli
 */
public class LoginResponse implements Buildable<LoginResponse> {
  public List<LoginPreventedResponse> actions;

  public String changePasswordId;

  public ChangePasswordReason changePasswordReason;

  public String emailVerificationId;

  public List<TwoFactorMethod> methods;

  public String pendingIdPLinkId;

  public String refreshToken;

  public UUID refreshTokenId;

  public String registrationVerificationId;

  public Map<String, Object> state;

  @JsonInclude(Include.NON_EMPTY)
  public Set<AuthenticationThreats> threatsDetected;

  public String token;

  public ZonedDateTime tokenExpirationInstant;

  public String trustToken;

  public String twoFactorId;

  public String twoFactorTrustId;

  public User user;

  @JacksonConstructor
  public LoginResponse() {
  }

  public LoginResponse(User user, String token) {
    this.user = user;
    this.token = token;
  }

  public LoginResponse(User user) {
    this.user = user;
  }

  public LoginResponse(List<LoginPreventedResponse> actions) {
    this.actions = actions;
  }
}
