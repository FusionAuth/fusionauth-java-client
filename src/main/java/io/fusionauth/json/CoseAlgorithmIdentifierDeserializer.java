/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.api.webauthn.enums.CoseAlgorithmIdentifier;

import java.io.IOException;

public class CoseAlgorithmIdentifierDeserializer extends StdDeserializer<CoseAlgorithmIdentifier> {
  @JacksonConstructor
  public CoseAlgorithmIdentifierDeserializer() {
    this(null);
  }

  public CoseAlgorithmIdentifierDeserializer(Class<CoseAlgorithmIdentifier> t) {
    super(t);
  }

  @Override
  public CoseAlgorithmIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    long alg = p.getLongValue();
    return CoseAlgorithmIdentifier.valueOfAlg(alg);
  }
}
