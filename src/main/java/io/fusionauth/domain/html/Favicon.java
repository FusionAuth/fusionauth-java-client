/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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

package io.fusionauth.domain.html;

import java.net.URI;
import java.util.Objects;

import io.fusionauth.domain.Buildable;

/**
 * Components of a Favicon in an HTML {@code <head>} element.
 */
public class Favicon implements Buildable<Favicon> {

  public URI href;

  public String rel = "icon";

  public String sizes;

  public String type = "image/x-icon";

  public Favicon() {
  }

  public Favicon(Favicon other) {
    this.href = other.href;
    this.rel = other.rel;
    this.sizes = other.sizes;
    this.type = other.type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Favicon favicon = (Favicon) o;
    return Objects.equals(href, favicon.href) &&
           Objects.equals(rel, favicon.rel) &&
           Objects.equals(sizes, favicon.sizes) &&
           Objects.equals(type, favicon.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, rel, sizes, type);
  }
}
