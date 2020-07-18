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

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.inversoft.json.ToString;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * CleanSpeak configuration at the system and application level.
 *
 * @author Brian Pontarelli
 */
public class CleanSpeakConfiguration extends Enableable implements Buildable<CleanSpeakConfiguration>, Integration {
  /**
   * API Key used to connect to the CleanSpeak API. This may be null some versions of CleanSpeak do not require an API key.
   */
  public String apiKey;

  /**
   * Application Ids of CleanSpeak Applications. There may be one to many CleanSpeak applications associated with a single FusionAuth
   * application.
   * <p>
   * For example, a Forum Application in FusionAuth may map to CleanSpeak applications Forum User Names, Forum Chat, Forum Posts.
   * <p>
   * If there is a 1 to 1 relationship between the FusionAuth and CleanSpeak applications, the application Ids are expected to be equal.
   */
  @JsonMerge(OptBoolean.FALSE)
  public List<UUID> applicationIds = new ArrayList<>();

  /**
   * The CleanSpeak API URL.
   */
  public URI url;

  /**
   * Configuration for Username moderation.
   */
  public UsernameModeration usernameModeration = new UsernameModeration();

  public CleanSpeakConfiguration() {
  }

  public CleanSpeakConfiguration(String apiKey, URI url, UsernameModeration usernameModeration,
                                 UUID... applicationIds) {
    this.apiKey = apiKey;
    this.url = url;
    this.usernameModeration = usernameModeration;
    Collections.addAll(this.applicationIds, applicationIds);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CleanSpeakConfiguration)) {
      return false;
    }
    CleanSpeakConfiguration that = (CleanSpeakConfiguration) o;
    return super.equals(o) &&
           Objects.equals(apiKey, that.apiKey) &&
           Objects.equals(usernameModeration, that.usernameModeration) &&
           Objects.equals(url, that.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), apiKey, usernameModeration, url);
  }

  public void normalize() {
    apiKey = trim(apiKey);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class UsernameModeration extends Enableable implements Buildable<UsernameModeration> {
    public UUID applicationId;

    public UsernameModeration() {
    }

    public UsernameModeration(UUID applicationId, boolean enabled) {
      this.applicationId = applicationId;
      this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      UsernameModeration that = (UsernameModeration) o;
      return super.equals(o) &&
             Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), applicationId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
