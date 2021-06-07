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
package io.fusionauth.domain.provider;

/**
 * The IdP behavior when no user link has been made yet.
 *
 * @author Daniel DeGroff
 */
public enum IdentityProviderLinkingStrategy {
  /**
   * Resolve the user by an existing IdP link. If a link does not yet exist, a pending link Id will be returned allowing the API caller to
   * complete the link using the Link API to an existing FusionAuth user.
   * <p>
   * This will link only the IdP unique Id to the FusionAuth user unique Id.
   */
  CreatePendingLink,

  /**
   * Create a link w/out prompting the user to create a user or link to an existing user. This behavior allows you to login via a 3rd party IdP w/out
   * creating a full user in FusionAuth or prompting the user to create a user prior to linking.
   */
  LinkAnonymously,

  /**
   * Create a user if they do not yet exist using the <code>email</code> returned by the IdP and then link the user. If the link
   * already exists the <code>email</code> is not utilized to resolve the user.
   * <p>
   * This is essentially the same behavior of all IdPs prior to version 1.28.0.
   */
  LinkByEmail,

  /**
   * Link a user based upon the <code>email</code> returned by the IdP and then link the user, and then create an IdP link. If the link
   * already exists the <code>email</code> is not utilized to resolve the user.
   * <p>
   * If no user is found with the email returned by the IdP and the link does not exist, no user is created. In this mode, you must create
   * the user in FusionAuth first.
   */
  LinkByEmailForExistingUser,

  /**
   * Create a user if they do not yet exist using the <code>username</code> returned by the IdP and then link the user. If the link
   * already exists the <code>username</code> is not utilized to resolve the user.
   * <p>
   * This behavior should be used with great caution due to the potential for account takeover. In practice, if you let uses self-register
   * in FusionAuth, or configure more than one IdP with this behavior, you will have a high chance of account takeover through accidental or
   * explicitly malicious behavior.
   * <p>
   * For example, If you were to enable mode for a Twitter IdP, the user with handle @cool-guy is unique within Twitter. However if you add
   * a second IdP and use this mode, if that IdP has a user with a username of `@cool-guy`, you will have problems.
   * <p>
   * Use this mode at your own risk.
   */
  LinkByUsername,

  /**
   * Link a user based upon the  <code>username</code> returned by the IdP and then link the user, and then create an IdP link. If the link
   * already exists the <code>username</code> is not utilized to resolve the user.
   * <p>
   * If no user is found with the username returned by the IdP and the link does not exist, no user is created. In this mode, you must create
   * the user in FusionAuth first.
   */
  LinkByUsernameForExistingUser,

  /**
   * This IdP does not support linking.
   */
  Unsupported
}
