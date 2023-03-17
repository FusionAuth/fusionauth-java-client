/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Webhook;
import io.fusionauth.domain.search.SearchResults;

/**
 * Webhook search response
 *
 * @author Spencer Witt
 */
public class WebhookSearchResponse {
  public long total;

  public List<Webhook> webhooks;

  @JacksonConstructor
  public WebhookSearchResponse() {
  }

  public WebhookSearchResponse(SearchResults<Webhook> searchResults) {
    this.webhooks = searchResults.results;
    this.total = searchResults.total;
  }
}
