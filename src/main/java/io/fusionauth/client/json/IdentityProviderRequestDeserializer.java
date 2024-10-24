/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.IdentityProviderRequest;
import io.fusionauth.domain.provider.IdentityProviderType;

/**
 * Custom JSON deserializer for IdentityProviderRequest.
 *
 * @author Matthew Altman
 */
public class IdentityProviderRequestDeserializer extends StdDeserializer<IdentityProviderRequest> {
  public IdentityProviderRequestDeserializer() {
    super(IdentityProviderRequest.class);
  }

  @Override
  public IdentityProviderRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new IdentityProviderRequest());
  }

  @Override
  public IdentityProviderRequest deserialize(JsonParser p, DeserializationContext ctxt, IdentityProviderRequest req)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    JsonNode idpNode = node.at("/identityProvider");

    if (req.identityProvider == null) {
      IdentityProviderType idpType = IdentityProviderJacksonHelper.extractType(ctxt, p, idpNode);
      req.identityProvider = IdentityProviderJacksonHelper.newIdentityProvider(idpType);
    }

    if (p.getCodec() instanceof ObjectMapper) {
      // If the available codec supports it, read the JsonNode directly into the object
      ((ObjectMapper) p.getCodec()).readerForUpdating(req.identityProvider).readValue(idpNode);
    } else if (p.getCodec() instanceof ObjectReader) {
      // Otherwise read the JsonNode and assign it to the request
      req.identityProvider = ((ObjectReader) p.getCodec()).readValue(idpNode, req.identityProvider.getClass());
    }
    return req;
  }
}
