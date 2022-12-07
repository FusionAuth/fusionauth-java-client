/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * @author Brian Pontarelli
 */
public class TwoFactorRequest extends BaseEventRequest implements Buildable<TwoFactorRequest> {
  public UUID applicationId;

  public String authenticatorId;

  public String code;

  public String email;

  public String method;

  public String mobilePhone;

  public String secret;

  public String secretBase32Encoded;

  public String twoFactorId;

  @JacksonConstructor
  public TwoFactorRequest() {
  }

  public TwoFactorRequest(String code, String secret) {
    this(code, null, secret);
  }

  public TwoFactorRequest(String code, String method, String secret) {
    this.code = code;
    this.method = method;
    this.secret = secret;
  }

  public TwoFactorRequest(EventInfo eventInfo, UUID applicationId, String code, String secret) {
    this(eventInfo, applicationId, code, null, secret);
  }

  public TwoFactorRequest(EventInfo eventInfo, UUID applicationId, String code, String method, String secret) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.code = code;
    this.method = method;
    this.secret = secret;
  }
}
