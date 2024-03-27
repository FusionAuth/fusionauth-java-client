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
 * Controls the policy for requesting user permission to grant access to requested scopes during an OAuth workflow
 * for a third-party application.
 *
 * @author Spencer Witt
 */
public enum OAuthScopeConsentMode {
  /**
   * A user will always be prompted to grant permission for requested scopes, even when they have not changed.
   */
  AlwaysPrompt,
  /**
   * A user's previous permission grant decision will be remembered. The user will not be prompted again
   * unless there are new required scopes that require consent.
   */
  RememberDecision,
  /**
   * A user will never be prompted to grant permission for requested scopes. Permission will be granted implicitly.
   */
  NeverPrompt
}
