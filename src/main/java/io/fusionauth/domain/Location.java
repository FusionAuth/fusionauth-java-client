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
package io.fusionauth.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Location information. Useful for IP addresses and other displayable data objects.
 *
 * @author Brian Pontarelli
 */
public class Location implements Buildable<Location> {
  public String city;

  public String country;

  public double latitude;

  public double longitude;

  public String region;

  public String zipcode;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Location)) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(city, location.city) &&
           Objects.equals(country, location.country) &&
           Objects.equals(latitude, location.latitude) &&
           Objects.equals(longitude, location.longitude) &&
           Objects.equals(region, location.region) &&
           Objects.equals(zipcode, location.zipcode);
  }

  @SuppressWarnings("unused")
  public String getDisplayString() {
    List<String> parts = Stream.of(city, region, country)
                               .filter(Objects::nonNull)
                               .collect(Collectors.toList());
    return String.join(", ", parts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, country, latitude, longitude, region, zipcode);
  }
}
