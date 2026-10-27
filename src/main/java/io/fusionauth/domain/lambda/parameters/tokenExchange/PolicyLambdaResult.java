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

package io.fusionauth.domain.lambda.parameters.tokenExchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.fusionauth.domain.TokenExchangePolicyDecision;

/**
 * Mutable decision written by a Token Exchange Policy Lambda.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyLambdaResult {
  public TokenExchangePolicyDecision decision;

  public Denial denial;

  public Granted granted;

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Denial {
    @JsonProperty("error_code")
    public String errorCode;

    @JsonProperty("error_description")
    public String errorDescription;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Granted {
    public List<String> aud;

    @JsonProperty("expires_in")
    public Integer expiresIn;

    public List<String> scope;

    private static boolean isNotBlank(Object value) {
      return value instanceof String && !((String) value).trim().isEmpty();
    }

    private static List<String> nonBlankStrings(List<?> values) {
      return values.stream()
                   .filter(Granted::isNotBlank)
                   .map(v -> (String) v)
                   .collect(Collectors.toList());
    }

    /**
     * Flexible setter for the audience claim, aud: accepts string or array, matching JWT claim flexibility.
     */
    @JsonSetter("aud")
    public void setAud(Object value) {
      if (value instanceof List) {
        this.aud = nonBlankStrings((List<?>) value);
      } else if (isNotBlank(value)) {
        this.aud = Collections.singletonList((String) value);
      } else {
        this.aud = null;
      }
    }

    /**
     * Flexible setter for scope: accepts space-delimited string or array, matching JWT claim flexibility.
     */
    @JsonSetter("scope")
    public void setScope(Object value) {
      if (value instanceof List) {
        this.scope = nonBlankStrings((List<?>) value);
      } else if (value instanceof String) {
        this.scope = nonBlankStrings(Arrays.asList(((String) value).split(" ")));
      } else {
        this.scope = null;
      }
    }
  }
}
