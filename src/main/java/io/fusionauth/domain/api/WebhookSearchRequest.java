/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.search.WebhookSearchCriteria;

/**
 * Search request for webhooks
 *
 * @author Spencer Witt
 */
public class WebhookSearchRequest {
  public WebhookSearchCriteria search = new WebhookSearchCriteria();

  @JacksonConstructor
  public WebhookSearchRequest() {
  }

  public WebhookSearchRequest(WebhookSearchCriteria search) {
    this.search = search;
  }
}
