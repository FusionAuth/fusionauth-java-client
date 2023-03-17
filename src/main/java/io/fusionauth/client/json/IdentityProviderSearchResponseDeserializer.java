/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.IdentityProviderSearchResponse;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.IdentityProviderType;

/**
 * Custom JSON de-serializer for IdentityProviderSearchResponse.
 *
 * @author Spencer Witt
 */
public class IdentityProviderSearchResponseDeserializer extends StdDeserializer<IdentityProviderSearchResponse> {
  public IdentityProviderSearchResponseDeserializer() {
    super(IdentityProviderSearchResponse.class);
  }

  @Override
  public IdentityProviderSearchResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new IdentityProviderSearchResponse());
  }

  @Override
  public IdentityProviderSearchResponse deserialize(JsonParser p, DeserializationContext ctxt, IdentityProviderSearchResponse resp)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    resp.total = node.at("/total").asLong();
    JsonNode idpNodes = node.at("/identityProviders");
    if (idpNodes != null && idpNodes.isArray()) {
      resp.identityProviders = new ArrayList<>();
      for (JsonNode idp : idpNodes) {
        IdentityProviderType idpType = IdentityProviderJacksonHelper.extractType(ctxt, p, idp);
        BaseIdentityProvider<?> baseIdp = IdentityProviderJacksonHelper.newIdentityProvider(idpType);
        ((ObjectMapper) p.getCodec()).readerForUpdating(baseIdp).readValue(idp);
        resp.identityProviders.add(baseIdp);
      }
    }

    return resp;
  }
}
