/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package io.fusionauth.domain.lambda.parameters.cimd;

import java.util.List;

/**
 * Represents the inbound lambda parameter 'clientMetadata' for CIMD Client Registration lambdas.
 */
public class ClientMetadata {
  public final String clientId;

  public final String clientIdHost;

  public final String clientIdPath;

  public final String clientName;

  public final String clientUri;

  public final String clientUriHost;

  public final String clientUriPath;

  public final List<String> redirectUris;

  public ClientMetadata(String clientId, String clientIdHost, String clientIdPath, String clientName,
                        String clientUri, String clientUriHost, String clientUriPath,
                        List<String> redirectUris) {
    this.clientId = clientId;
    this.clientIdHost = clientIdHost;
    this.clientIdPath = clientIdPath;
    this.clientName = clientName;
    this.clientUri = clientUri;
    this.clientUriHost = clientUriHost;
    this.clientUriPath = clientUriPath;
    this.redirectUris = redirectUris;
  }
}
