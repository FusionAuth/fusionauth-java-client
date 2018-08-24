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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.util.Normalizer;

/**
 * An action that can be executed on a user (discipline or reward potentially).
 *
 * @author Brian Pontarelli
 */
public class UserAction implements Comparable<UserAction>, Buildable<UserAction> {
  public boolean active;

  /**
   * Only time-based actions. Template to use when cancelled
   */
  public UUID cancelEmailTemplateId;

  /**
   * Only time-based actions. Template to use when ended
   */
  public UUID endEmailTemplateId;

  public UUID id;

  public boolean includeEmailInEventJSON;

  public LocalizedStrings localizedNames;

  /**
   * Only time-based actions. Template to use when modified
   */
  public UUID modifyEmailTemplateId;

  public String name;

  public List<UserActionOption> options = new ArrayList<>();

  public boolean preventLogin;

  /**
   * Only time-based actions. This indicates FusionAuth will send an event when the action expires
   */
  public boolean sendEndEvent;

  /**
   * All actions. The template to be used when an action is first taken
   */
  public UUID startEmailTemplateId;

  public boolean temporal;

  public TransactionType transactionType;

  /**
   * FusionAuth emailing
   */
  public boolean userEmailingEnabled;

  /**
   * This is a flag that determines if the "Notify User" option is displayed on the manage page.
   */
  public boolean userNotificationsEnabled;

  public UserAction() {
  }

  public UserAction(String name) {
    this.name = name;
  }

  public UserAction(UUID id, String name, boolean active, LocalizedStrings localizedNames, boolean preventLogin,
                    boolean sendEndEvent, boolean temporal, boolean userNotificationsEnabled,
                    boolean userEmailingEnabled, boolean includeEmailInEventJSON,
                    UUID startEmailTemplateId, UUID modifyEmailTemplateId, UUID cancelEmailTemplateId,
                    UUID endEmailTemplateId, UserActionOption... options) {
    this.id = id;
    this.active = active;
    this.name = name;
    this.includeEmailInEventJSON = includeEmailInEventJSON;
    this.localizedNames = localizedNames;
    this.preventLogin = preventLogin;
    this.sendEndEvent = sendEndEvent;
    this.temporal = temporal;
    this.userNotificationsEnabled = userNotificationsEnabled;
    this.startEmailTemplateId = startEmailTemplateId;
    this.modifyEmailTemplateId = modifyEmailTemplateId;
    this.cancelEmailTemplateId = cancelEmailTemplateId;
    this.endEmailTemplateId = endEmailTemplateId;
    this.userEmailingEnabled = userEmailingEnabled;
    Collections.addAll(this.options, options);
  }

  @Override
  public int compareTo(UserAction o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserAction)) {
      return false;
    }
    UserAction that = (UserAction) o;
    return Objects.equals(active, that.active) &&
        Objects.equals(includeEmailInEventJSON, that.includeEmailInEventJSON) &&
        Objects.equals(preventLogin, that.preventLogin) &&
        Objects.equals(sendEndEvent, that.sendEndEvent) &&
        Objects.equals(temporal, that.temporal) &&
        Objects.equals(transactionType, that.transactionType) &&
        Objects.equals(userNotificationsEnabled, that.userNotificationsEnabled) &&
        Objects.equals(userEmailingEnabled, that.userEmailingEnabled) &&
        Objects.equals(localizedNames, that.localizedNames) &&
        Objects.equals(name, that.name) &&
        Objects.equals(options, that.options) &&
        Objects.equals(startEmailTemplateId, that.startEmailTemplateId) &&
        Objects.equals(modifyEmailTemplateId, that.modifyEmailTemplateId) &&
        Objects.equals(cancelEmailTemplateId, that.cancelEmailTemplateId) &&
        Objects.equals(endEmailTemplateId, that.endEmailTemplateId);
  }

  @JsonIgnore
  public UserActionOption getOption(String name) {
    if (name == null) {
      return null;
    }

    for (UserActionOption key : options) {
      if (key.name.equals(name)) {
        return key;
      }
    }

    return null;
  }

  @Override
  public int hashCode() {
    return Objects.hash(active, includeEmailInEventJSON, localizedNames, name, options, preventLogin, sendEndEvent,
                        temporal, transactionType, userNotificationsEnabled, userEmailingEnabled, startEmailTemplateId,
                        modifyEmailTemplateId, cancelEmailTemplateId, endEmailTemplateId);
  }

  public void normalize() {
    name = Normalizer.trim(name);

    if (localizedNames != null) {
      localizedNames.normalize();
    }

    if (options != null) {
      options.forEach(UserActionOption::normalize);
    }
  }

  public void sortOptions() {
    Collections.sort(options);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
