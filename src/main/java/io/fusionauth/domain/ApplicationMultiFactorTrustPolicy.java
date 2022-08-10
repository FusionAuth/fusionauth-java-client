/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

/**
 * @author Daniel DeGroff
 */
public enum ApplicationMultiFactorTrustPolicy {
  // Any application means we can trust the MFA trust from all apps
  Any,
  // Only trust MFA trust gained from this app
  This,
  // Do not trust, always prompt for MFA
  None
}



