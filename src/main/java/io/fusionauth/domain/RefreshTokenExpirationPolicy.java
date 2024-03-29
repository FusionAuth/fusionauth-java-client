/*
 * Copyright (c) 2020-2023, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

/**
 * @author Daniel DeGroff
 */
public enum RefreshTokenExpirationPolicy {
  // The token expiration is fixed from the time of issue to the configured TTL.
  Fixed,

  // The token expiration is reset on each use to the configured TTL.
  SlidingWindow,

  // The token expiration is reset on each use to the configured TTL, or until the maximum lifetime has been reached.
  SlidingWindowWithMaximumLifetime,
}
