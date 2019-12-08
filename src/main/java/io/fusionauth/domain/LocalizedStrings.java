/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
 * Models a set of localized Strings that can be stored as JSON.
 *
 * @author Brian Pontarelli
 */
public class LocalizedStrings extends TreeMap<Locale, String> {
  public LocalizedStrings() {
    super((one, two) -> one == null ? -1 : two == null ? 1 : one.toString().compareTo(two.toString()));
  }

  public LocalizedStrings(Locale locale, String string) {
    super((one, two) -> one == null ? -1 : two == null ? 1 : one.toString().compareTo(two.toString()));
    put(locale, string);
  }

  public LocalizedStrings(Locale locale, String string, Locale locale2, String string2) {
    super((one, two) -> one == null ? -1 : two == null ? 1 : one.toString().compareTo(two.toString()));
    put(locale, string);
    put(locale2, string2);
  }

  public void normalize() {
    Normalizer.trimMap(this);
    Normalizer.lineReturnsMap(this);
  }

  public void removeEmpty() {
    Normalizer.removeEmpty(this);
  }
}
