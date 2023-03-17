/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.email.EmailTemplate;
import io.fusionauth.domain.search.SearchResults;

/**
 * Email template search response
 *
 * @author Mark Manes
 */
public class EmailTemplateSearchResponse {
  public List<EmailTemplate> emailTemplates = new ArrayList<>();

  public long total;

  @JacksonConstructor
  public EmailTemplateSearchResponse() {
  }

  public EmailTemplateSearchResponse(SearchResults<EmailTemplate> searchResults) {
    this.emailTemplates = searchResults.results;
    this.total = searchResults.total;
  }
}
