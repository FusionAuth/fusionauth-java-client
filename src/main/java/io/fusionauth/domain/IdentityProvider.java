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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class IdentityProvider implements Buildable<IdentityProvider> {
  @JsonUnwrapped
  public IdentityProviderData data = new IdentityProviderData();

  public Set<String> domains = new HashSet<>();

  public UUID id;

  public String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdentityProvider)) {
      return false;
    }
    IdentityProvider that = (IdentityProvider) o;
    return Objects.equals(data, that.data) &&
        Objects.equals(domains, that.domains) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, domains, name);
  }

  public void normalize() {
    if (data.keys != null) {
      // remove empty values, and then normalize PEM line returns
      data.keys.entrySet().removeIf(entry -> entry.getKey() == null || entry.getValue() == null || entry.getValue().isEmpty());
      data.keys.entrySet().forEach(entry -> entry.setValue(entry.getValue().trim().replace("\r\n", "\n").replace("\r", "\n")));
    }

    domains = domains.stream().map(d -> d.toLowerCase().trim()).collect(Collectors.toSet());
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
