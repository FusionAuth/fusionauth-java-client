/*
 * Copyright (c) 2024-2024, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.fusionauth.domain.IdentityType;

/**
 * Deserialize an IdentityType.
 *
 * @author Daniel DeGroff
 */
public class IdentityTypeDeserializer extends JsonDeserializer<IdentityType> {
  @Override
  public IdentityType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return IdentityType.of(p.getText());
  }
}
