/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.inversoft.json.ToString;

/**
 * Builds an HTTP URL Query string.
 *
 * @author Brian Pontarelli
 */
public class URLQueryBuilder {
  private final StringBuilder build = new StringBuilder();

  public static URLQueryBuilder builder() {
    return new URLQueryBuilder();
  }

  public String query() {
    return build.toString();
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public URLQueryBuilder with(String name, Object value) {
    if (value == null) {
      return this;
    }

    if (build.length() > 0) {
      build.append("&");
    }

    try {
      build.append(name).append("=").append(URLEncoder.encode(value.toString(), "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      // Never did
      throw new RuntimeException(e);
    }

    return this;
  }
}
