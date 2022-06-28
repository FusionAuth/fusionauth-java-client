/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * Supply additional information about the Relying Party when creating a new credential
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialRpEntity extends PublicKeyCredentialEntity implements Buildable<PublicKeyCredentialRpEntity> {
  /**
   * A unique identifier for the Relying Party. Defaults to the calling host's <a
   * href="https://html.spec.whatwg.org/multipage/origin.html#concept-origin-effective-domain">effective domain</a>
   */
  public String id;

  @JacksonConstructor
  public PublicKeyCredentialRpEntity() {
  }

  public PublicKeyCredentialRpEntity(String name, String id) {
    super(name);
    this.id = id;
  }
}
