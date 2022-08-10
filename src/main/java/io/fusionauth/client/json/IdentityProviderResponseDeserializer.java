/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.api.IdentityProviderResponse;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.IdentityProviderType;

/**
 * Custom JSON de-serializer for IdentityProviderResponse.
 *
 * @author Daniel DeGroff
 */
public class IdentityProviderResponseDeserializer extends StdDeserializer<IdentityProviderResponse> {
  public IdentityProviderResponseDeserializer() {
    super(IdentityProviderResponse.class);
  }

  @Override
  public IdentityProviderResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new IdentityProviderResponse());
  }

  @Override
  public IdentityProviderResponse deserialize(JsonParser p, DeserializationContext ctxt, IdentityProviderResponse resp) throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    JsonNode idpNode = node.at("/identityProvider");
    if (idpNode != null) {
      IdentityProviderType idpType = IdentityProviderJacksonHelper.extractType(ctxt, p, idpNode);
      resp.identityProvider = IdentityProviderJacksonHelper.newIdentityProvider(idpType);
      ((ObjectMapper) p.getCodec()).readerForUpdating(resp.identityProvider).readValue(idpNode);
    }

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
