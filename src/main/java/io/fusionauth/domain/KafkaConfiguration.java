/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class KafkaConfiguration extends Enableable implements Buildable<KafkaConfiguration>, Integration {
  public String defaultTopic;

  // List of potentially sensitive configurations https://kafka.apache.org/documentation/#security_configclients
  public Map<String, String> producer = new TreeMap<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KafkaConfiguration that = (KafkaConfiguration) o;
    return super.equals(o) &&
           Objects.equals(defaultTopic, that.defaultTopic) &&
           Objects.equals(producer, that.producer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), defaultTopic, producer);
  }

  public void normalize() {
    if (!producer.containsKey("bootstrap.servers")) {
      producer.put("bootstrap.servers", "localhost:9092");
      producer.put("max.block.ms", "5000");
      producer.put("request.timeout.ms", "2000");
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
