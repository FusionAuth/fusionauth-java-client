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
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.fusionauth.domain.provider.AppleIdentityProvider;
import io.fusionauth.domain.provider.BaseIdentityProvider;
import io.fusionauth.domain.provider.EpicGamesIdentityProvider;
import io.fusionauth.domain.provider.ExternalJWTIdentityProvider;
import io.fusionauth.domain.provider.FacebookIdentityProvider;
import io.fusionauth.domain.provider.GoogleIdentityProvider;
import io.fusionauth.domain.provider.HYPRIdentityProvider;
import io.fusionauth.domain.provider.IdentityProviderType;
import io.fusionauth.domain.provider.LinkedInIdentityProvider;
import io.fusionauth.domain.provider.NintendoIdentityProvider;
import io.fusionauth.domain.provider.OpenIdConnectIdentityProvider;
import io.fusionauth.domain.provider.SAMLv2IdPInitiatedIdentityProvider;
import io.fusionauth.domain.provider.SAMLv2IdentityProvider;
import io.fusionauth.domain.provider.SonyPSNIdentityProvider;
import io.fusionauth.domain.provider.SteamIdentityProvider;
import io.fusionauth.domain.provider.TwitchIdentityProvider;
import io.fusionauth.domain.provider.TwitterIdentityProvider;
import io.fusionauth.domain.provider.XboxIdentityProvider;
import static io.fusionauth.domain.provider.IdentityProviderType.ExternalJWT;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderJacksonHelper {
  public static IdentityProviderType extractType(DeserializationContext ctxt, JsonParser p, JsonNode idpNode)
      throws IOException {
    JsonNode node = idpNode.at("/type");
    String type = node.asText(ExternalJWT.name());

    IdentityProviderType identityProviderType = IdentityProviderType.safeValueOf(type);
    if (identityProviderType == null) {
      // This does actually throw an exception, but this is how Jackson rolls.
      String sorted = Arrays.stream(IdentityProviderType.values()).map(Enum::name).sorted().collect(Collectors.joining(", "));
      return (IdentityProviderType) ctxt.handleUnexpectedToken(BaseIdentityProvider.class, node.asToken(), p,
                                                               "Expected the type field to be one of [" + sorted + "], but found [" + node.asText() + "]");
    }

    return identityProviderType;
  }

  public static BaseIdentityProvider<?> newIdentityProvider(IdentityProviderType type) {
    switch (type) {
      case Apple:
        return new AppleIdentityProvider();
      case EpicGames:
        return new EpicGamesIdentityProvider();
      case ExternalJWT:
        return new ExternalJWTIdentityProvider();
      case Facebook:
        return new FacebookIdentityProvider();
      case Google:
        return new GoogleIdentityProvider();
      case HYPR:
        return new HYPRIdentityProvider();
      case LinkedIn:
        return new LinkedInIdentityProvider();
      case Nintendo:
        return new NintendoIdentityProvider();
      case OpenIDConnect:
        return new OpenIdConnectIdentityProvider();
      case SAMLv2:
        return new SAMLv2IdentityProvider();
      case SAMLv2IdPInitiated:
        return new SAMLv2IdPInitiatedIdentityProvider();
      case SonyPSN:
        return new SonyPSNIdentityProvider();
      case Steam:
        return new SteamIdentityProvider();
      case Twitch:
        return new TwitchIdentityProvider();
      case Twitter:
        return new TwitterIdentityProvider();
      case Xbox:
        return new XboxIdentityProvider();
      default:
        throw new IllegalStateException("Unexpected type [" + type + "]. This is a FusionAuth bug, someone forgot to add a case statement for a new type.");
    }
  }
}
