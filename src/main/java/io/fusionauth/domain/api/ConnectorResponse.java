/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.client.json.ConnectorResponseDeserializer;
import io.fusionauth.domain.connector.BaseConnectorConfiguration;

/**
 * @author Trevor Smith
 */
@JsonDeserialize(using = ConnectorResponseDeserializer.class)
public class ConnectorResponse {
  public BaseConnectorConfiguration connector;

  public List<BaseConnectorConfiguration> connectors;

  @JacksonConstructor
  public ConnectorResponse() {
  }

  public ConnectorResponse(BaseConnectorConfiguration connector) {
    this.connector = connector;
  }

  public ConnectorResponse(List<BaseConnectorConfiguration> connectors) {
    this.connectors = connectors;
  }
}
