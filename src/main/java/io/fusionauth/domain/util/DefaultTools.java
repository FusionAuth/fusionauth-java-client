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
package io.fusionauth.domain.util;

import java.util.List;
import java.util.Optional;

/**
 * Helper for dealing with default values.
 *
 * @author Brian Pontarelli
 */
public final class DefaultTools {
  public static <T> void addIfEmpty(List<T> value, T defaultValue) {
    if (value.isEmpty()) {
      value.add(defaultValue);
    }
  }

  public static <T> T defaultIfNull(T value, T defaultValue) {
    return Optional.ofNullable(value).orElse(defaultValue);
  }

  public static int defaultIfZero(int value, int defaultValue) {
    return (value != 0) ? value : defaultValue;
  }

  public static long defaultIfZero(long value, long defaultValue) {
    return (value != 0) ? value : defaultValue;
  }
}
