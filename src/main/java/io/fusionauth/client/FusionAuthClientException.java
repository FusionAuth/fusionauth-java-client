/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client;

import com.inversoft.error.Errors;

/**
 * @author Brian Pontarelli
 */
public class FusionAuthClientException extends RuntimeException {
  public final Errors errors;

  public FusionAuthClientException(String message) {
    super(message);
    this.errors = null;
  }

  public FusionAuthClientException(Errors errors) {
    this.errors = errors;
  }

  public FusionAuthClientException(Throwable cause) {
    super(cause);
    this.errors = null;
  }
}
