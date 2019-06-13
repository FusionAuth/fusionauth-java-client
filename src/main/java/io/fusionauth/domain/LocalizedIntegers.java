/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import java.util.Locale;
import java.util.TreeMap;

import io.fusionauth.domain.util.Normalizer;

/**
 * Models a set of localized Integers that can be stored as JSON.
 *
 * @author Daniel DeGroff
 */
public class LocalizedIntegers extends TreeMap<Locale, Integer> {
  public LocalizedIntegers() {
    super((one, two) -> one == null ? -1 : two == null ? 1 : one.toString().compareTo(two.toString()));
  }

  public LocalizedIntegers(Locale locale, Integer integer) {
    super((one, two) -> one == null ? -1 : two == null ? 1 : one.toString().compareTo(two.toString()));
    put(locale, integer);
  }

  public LocalizedIntegers(Locale locale, Integer integer, Locale locale2, Integer integer2) {
    super((one, two) -> one == null ? -1 : two == null ? 1 : one.toString().compareTo(two.toString()));
    put(locale, integer);
    put(locale2, integer2);
  }

  public void removeEmpty() {
    Normalizer.removeEmpty(this);
  }
}
