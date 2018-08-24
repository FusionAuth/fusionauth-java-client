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
import java.util.UUID;

import com.inversoft.json.ToString;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * Models action reasons.
 *
 * @author Brian Pontarelli
 */
public class UserActionReason implements Comparable<UserActionReason> {
  public String code;

  public UUID id;

  public LocalizedStrings localizedTexts;

  public String text;

  public UserActionReason() {
  }

  public UserActionReason(UUID id, String text, LocalizedStrings localizedTexts, String code) {
    this.id = id;
    this.text = text;
    this.localizedTexts = localizedTexts;
    this.code = code;
  }

  @Override
  public int compareTo(UserActionReason o) {
    return text.compareTo(o.text);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserActionReason)) {
      return false;
    }
    UserActionReason that = (UserActionReason) o;
    return Objects.equals(code, that.code) &&
        Objects.equals(localizedTexts, that.localizedTexts) &&
        Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, localizedTexts, text);
  }

  public void normalize() {
    code = trim(code);
    text = trim(text);

    if (localizedTexts != null) {
      localizedTexts.normalize();
    }
  }

  public String toString() {
    return ToString.toString(this);
  }
}
