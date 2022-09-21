/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.api.webauthn.CoseKeyType;

public class CoseKeyTypeDeserializer extends StdDeserializer<CoseKeyType> {
  @JacksonConstructor
  public CoseKeyTypeDeserializer() {
    this(null);
  }

  public CoseKeyTypeDeserializer(Class<CoseKeyType> t) {
    super(t);
  }

  @Override
  public CoseKeyType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    int kty = p.getIntValue();
    return CoseKeyType.valueOfKty(kty);
  }
}
