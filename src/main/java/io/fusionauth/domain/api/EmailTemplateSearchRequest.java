/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.search.EmailTemplateSearchCriteria;

/**
 * Search request for email templates
 *
 * @author Mark Manes
 */
public class EmailTemplateSearchRequest implements Buildable<EmailTemplateSearchRequest> {

  public EmailTemplateSearchCriteria search = new EmailTemplateSearchCriteria();

  @JacksonConstructor
  public EmailTemplateSearchRequest() {
  }

  public EmailTemplateSearchRequest(EmailTemplateSearchCriteria search) {
    this.search = search;
  }
}
