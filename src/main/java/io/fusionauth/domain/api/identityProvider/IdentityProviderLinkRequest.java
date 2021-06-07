/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.identityProvider;

import java.util.UUID;

import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderLinkRequest implements Buildable<IdentityProviderLinkRequest> {
  public UUID identityProviderId;

  public String identityProviderUserId;

  public String pendingIdPLinkId;

  public UUID userId;
}
