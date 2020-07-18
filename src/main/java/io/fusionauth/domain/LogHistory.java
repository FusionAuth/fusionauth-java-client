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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * A historical state of a user log event. Since events can be modified, this stores the historical state.
 *
 * @author Brian Pontarelli
 */
public class LogHistory {
  public final List<HistoryItem> historyItems = new ArrayList<>();

  public LogHistory() {
  }

  public LogHistory(UUID actionerUserId, String comment, ZonedDateTime createInstant, ZonedDateTime expiry) {
    add(actionerUserId, comment, createInstant, expiry);
  }

  public LogHistory add(UUID actionerUserId, String comment, ZonedDateTime createInstant, ZonedDateTime expiry) {
    historyItems.add(new HistoryItem(actionerUserId, comment, createInstant, expiry));
    return this;
  }

  public HistoryItem earliest() {
    if (historyItems.isEmpty()) {
      return null;
    }

    return historyItems.get(0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LogHistory that = (LogHistory) o;
    return historyItems.equals(that.historyItems);
  }

  @Override
  public int hashCode() {
    return historyItems.hashCode();
  }

  public HistoryItem latest() {
    if (historyItems.isEmpty()) {
      return null;
    }

    return historyItems.get(historyItems.size() - 1);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class HistoryItem {
    public UUID actionerUserId;

    public String comment;

    public ZonedDateTime createInstant;

    public ZonedDateTime expiry;

    @JacksonConstructor
    public HistoryItem() {
    }

    public HistoryItem(UUID actionerUserId, String comment, ZonedDateTime createInstant, ZonedDateTime expiry) {
      this.actionerUserId = actionerUserId;
      this.comment = comment;
      this.createInstant = createInstant;
      this.expiry = expiry;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof HistoryItem)) {
        return false;
      }
      HistoryItem that = (HistoryItem) o;
      return Objects.equals(actionerUserId, that.actionerUserId) &&
             Objects.equals(comment, that.comment) &&
             Objects.equals(createInstant, that.createInstant) &&
             Objects.equals(expiry, that.expiry);
    }

    @Override
    public int hashCode() {
      return Objects.hash(actionerUserId, comment, createInstant, expiry);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
