/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.twoFactor;

/**
 * @author Daniel DeGroff
 */
public class SecretResponse {
  public String secret;

  public String secretBase32Encoded;

  public SecretResponse() {
  }

  public SecretResponse(String secret, String secretBase32Encoded) {
    this.secret = secret;
    this.secretBase32Encoded = secretBase32Encoded;
  }
}
