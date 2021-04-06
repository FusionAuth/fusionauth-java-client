/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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

// TODO : Code Review :
//    1. Thinking this should be in the provider package instead of oauth2.
//    2. Maybe rename enum to IdentityProviderLoginMethod, this is more verbose but we are beginning to realize
//       that because other client libraries we build such as Go and .NET core use either a global namespace or functionally have a
//       global namespace that naming objects more specifically helps us not have downstream name collisions in our clients.
// [brettp]TODO: Move to IDP package

/**
 * @author Brett Pontarelli
 */
public enum LoginMethod {
  UsePopup,
  UseRedirect
}