/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Application;
import io.fusionauth.domain.ApplicationRole;
import io.fusionauth.domain.EventInfo;

/**
 * The Application API request object.
 *
 * @author Brian Pontarelli
 */
public class ApplicationRequest extends BaseEventRequest {
  public Application application;

  public ApplicationRole role;

  @JacksonConstructor
  public ApplicationRequest() {
  }

  public ApplicationRequest(Application application) {
    this.application = application;
  }

  public ApplicationRequest(ApplicationRole role) {
    this.role = role;
  }

  public ApplicationRequest(EventInfo info, Application application) {
    super(info);
    this.application = application;
  }

  public ApplicationRequest(EventInfo info, ApplicationRole role) {
    super(info);
    this.role = role;
  }
}
