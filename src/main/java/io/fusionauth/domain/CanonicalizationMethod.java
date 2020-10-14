/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
 * XML canonicalization method enumeration. This is used for the IdP and SP side of FusionAuth SAML.
 *
 * @author Brian Pontarelli
 */
public enum CanonicalizationMethod {
  exclusive(javax.xml.crypto.dsig.CanonicalizationMethod.EXCLUSIVE),

  exclusive_with_comments(javax.xml.crypto.dsig.CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS),

  inclusive(javax.xml.crypto.dsig.CanonicalizationMethod.INCLUSIVE),

  inclusive_with_comments(javax.xml.crypto.dsig.CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS);

  private final String uri;

  CanonicalizationMethod(String uri) {
    this.uri = uri;
  }

  public String getURI() {
    return uri;
  }
}
