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

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * Models content user action options.
 *
 * @author Brian Pontarelli
 */
public class UserActionOption implements Comparable<UserActionOption> {
  public LocalizedStrings localizedNames;

  public String name;

  @JacksonConstructor
  public UserActionOption() {
  }

  public UserActionOption(String name, LocalizedStrings localizedNames) {
    this.name = name;
    this.localizedNames = localizedNames;
  }

  @Override
  public int compareTo(UserActionOption o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserActionOption)) {
      return false;
    }
    UserActionOption that = (UserActionOption) o;
    return Objects.equals(localizedNames, that.localizedNames) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localizedNames, name);
  }

  public void normalize() {
    name = trim(name);

    if (localizedNames != null) {
      localizedNames.normalize();
    }
  }
}
