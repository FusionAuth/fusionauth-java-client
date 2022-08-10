/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.ConnectorRequestDeserializer;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;

/**
 * @author Trevor Smith
 */
@JsonDeserialize(using = ConnectorRequestDeserializer.class)
public class ConnectorRequest {
  public BaseConnectorConfiguration connector;

  @JacksonConstructor
  public ConnectorRequest() {
  }

  public ConnectorRequest(BaseConnectorConfiguration connector) {
    this.connector = connector;
  }
}
