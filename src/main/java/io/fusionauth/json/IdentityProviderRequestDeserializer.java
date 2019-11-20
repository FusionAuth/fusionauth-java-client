/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.IdentityProviderRequest;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.ExternalJWTIdentityProvider;
import io.fusionauth.domain.provider.FacebookIdentityProvider;
import io.fusionauth.domain.provider.GoogleIdentityProvider;
import io.fusionauth.domain.provider.IdentityProviderType;
import io.fusionauth.domain.provider.OpenIdConnectIdentityProvider;
import io.fusionauth.domain.provider.SAMLv2IdentityProvider;
import io.fusionauth.domain.provider.TwitterIdentityProvider;
import static io.fusionauth.domain.provider.IdentityProviderType.ExternalJWT;

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
    return p.readValueAs(IdentityProviderRequest.class);
  }

  @Override
  public IdentityProviderRequest deserialize(JsonParser p, DeserializationContext ctxt, IdentityProviderRequest req) throws IOException {
    JsonNode incomingNode = p.getCodec().readTree(p);
    JsonNode idpNode = incomingNode.get("identityProvider");
    BaseIdentityProvider idp = req.identityProvider;
    if (idp == null) {
      idp = getIdentityProvider(idpNode);
    }

    if (idp != null) {
      ObjectReader reader = ((ObjectMapper) p.getCodec()).readerForUpdating(idp);
      reader.readValue(idpNode);
    }

    req.identityProvider = idp;
    return req;
  }

  private BaseIdentityProvider getIdentityProvider(JsonNode idpNode) {
    if (idpNode == null) {
      return null;
    }

    // Default to ExternalJWT to support backwards compatibility
    JsonNode typeNode = idpNode.get("type");
    IdentityProviderType type = typeNode == null ? ExternalJWT : IdentityProviderType.valueOf(typeNode.asText());

    switch (type) {
      case ExternalJWT:
        return new ExternalJWTIdentityProvider();
      case Facebook:
        return new FacebookIdentityProvider();
      case Google:
        return new GoogleIdentityProvider();
      case OpenIDConnect:
        return new OpenIdConnectIdentityProvider();
      case SAMLv2:
        return new SAMLv2IdentityProvider();
      case Twitter:
        return new TwitterIdentityProvider();
      default:
        throw new IllegalStateException("Unexpected type [" + type + "]. This is a FusionAuth bug, this deserializer is missing support for this new IdP type.");
    }
  }
}
