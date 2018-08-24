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

import java.util.HashMap;

import io.fusionauth.domain.util.Normalizer;

/**
 * Type for webhook headers.
 *
 * @author Brian Pontarelli
 */
public class HTTPHeaders extends HashMap<String, String> {
  public HTTPHeaders() {
  }

  public HTTPHeaders(String key, String value) {
    this.put(key, value);
  }

  public HTTPHeaders(String key, String value, String key2, String value2) {
    this.put(key, value);
    this.put(key2, value2);
  }

  public HTTPHeaders(String key, String value, String key2, String value2, String key3, String value3) {
    this.put(key, value);
    this.put(key2, value2);
    this.put(key3, value3);
  }

  public void normalize() {
    Normalizer.trimMap(this);
  }
}
