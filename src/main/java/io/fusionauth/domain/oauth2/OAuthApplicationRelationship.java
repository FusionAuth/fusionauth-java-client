/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.oauth2;

/**
 * The application's relationship to the authorization server. First-party applications will be granted implicit permission for requested scopes.
 * Third-party applications will use the {@link OAuthScopeConsentMode} policy.
 *
 * @author Spencer Witt
 */
public enum OAuthApplicationRelationship {
  /**
   * The application has the same owner as the authorization server. Permission for requested scopes is granted implicitly without prompting.
   */
  FirstParty,
  /**
   * The application is external to the authorization server. User will be prompted to grant permission to the requested scopes based on the
   * application's {@link OAuthScopeConsentMode}.
   */
  ThirdParty
}
