/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.IdentityProviderLink;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderLinkRequest extends BaseEventRequest implements Buildable<IdentityProviderLinkRequest> {
  public IdentityProviderLink identityProviderLink = new IdentityProviderLink();

  public String pendingIdPLinkId;

  @Deprecated
  public void setDisplayName(String displayName) {
    this.identityProviderLink.displayName = displayName;
  }

  @Deprecated
  public void setIdentityProviderId(UUID identityProviderId) {
    this.identityProviderLink.identityProviderId = identityProviderId;
  }

  @Deprecated
  public void setIdentityProviderUserId(String identityProviderUserId) {
    this.identityProviderLink.identityProviderUserId = identityProviderUserId;
  }

  @Deprecated
  public void setUserId(UUID userId) {
    this.identityProviderLink.userId = userId;
  }
}
