/*
 * Copyright (c) 2024-2025, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.fusionauth.domain.IdentityType;

/**
 * FusionAuth specific Jackson bindings. This can be used in the public FusionAuth client.
 *
 * @author Daniel DeGroff
 */
public class FusionAuthJacksonModule extends SimpleModule {
  public FusionAuthJacksonModule() {
    addSerializer(IdentityType.class, new IdentityTypeSerializer());
    addDeserializer(IdentityType.class, new IdentityTypeDeserializer());
  }
}
