/*
 * Copyright (c) 2024-2024, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.fusionauth.domain.IdentityType;

/**
 * FusionAuth specific Jackson bindings. This can be used in the public FusionAuth client.
 *
 * @author Daniel DeGroff
 */
public class FusionAuthJacksonModule extends SimpleModule {
  private static final Version VERSION = VersionUtil.parseVersion("1.55.0", "io.fusionauth", "fusionauth-app");

  public FusionAuthJacksonModule() {
    super(VERSION);
    addSerializer(IdentityType.class, new IdentityTypeSerializer());
    addDeserializer(IdentityType.class, new IdentityTypeDeserializer());
  }
}
