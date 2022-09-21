/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.api.webauthn.CoseAlgorithmIdentifier;

import java.io.IOException;

public class CoseAlgorithmIdentifierSerializer extends StdSerializer<CoseAlgorithmIdentifier> {
  @JacksonConstructor
  public CoseAlgorithmIdentifierSerializer() {
    this(null);
  }

  public CoseAlgorithmIdentifierSerializer(Class<CoseAlgorithmIdentifier> t) {
    super(t);
  }

  @Override
  public void serialize(CoseAlgorithmIdentifier value, JsonGenerator gen,
                        SerializerProvider provider) throws IOException {
    gen.writeNumber(value.alg);
  }
}
