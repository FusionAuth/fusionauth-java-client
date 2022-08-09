/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.twoFactor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class TwoFactorStatusResponse implements Buildable<TwoFactorStatusResponse> {
  public List<TwoFactorTrust> trusts = new ArrayList<>();

  public String twoFactorTrustId;

  public static class TwoFactorTrust {
    public UUID applicationId;

    public ZonedDateTime expiration;

    public ZonedDateTime startInstant;

    public TwoFactorTrust() {
    }

    public TwoFactorTrust(UUID applicationId, ZonedDateTime expiration, ZonedDateTime startInstant) {
      this.applicationId = applicationId;
      this.expiration = expiration;
      this.startInstant = startInstant;
    }

    public boolean isExpired() {
      return expiration.isBefore(ZonedDateTime.now(ZoneOffset.UTC));
    }
  }
}
