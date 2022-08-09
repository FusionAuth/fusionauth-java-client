/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.UUID;

import io.fusionauth.domain.Buildable;

/**
 * Login Ping API request object.
 *
 * @author Daniel DeGroff
 */
public class LoginPingRequest extends BaseLoginRequest implements Buildable<LoginPingRequest> {
  public UUID userId;
}