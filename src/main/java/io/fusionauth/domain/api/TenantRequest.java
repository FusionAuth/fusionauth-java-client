/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.Tenant;

/**
 * @author Daniel DeGroff
 */
public class TenantRequest extends BaseEventRequest {
  public UUID sourceTenantId;

  public Tenant tenant;

  public List<UUID> webhookIds;

  @JacksonConstructor
  public TenantRequest() {
  }

  public TenantRequest(Tenant tenant) {
    this.tenant = tenant;
  }

  public TenantRequest(EventInfo eventInfo, Tenant tenant, List<UUID> webhookIds) {
    super(eventInfo);
    this.tenant = tenant;
    this.webhookIds = webhookIds;
  }
}
