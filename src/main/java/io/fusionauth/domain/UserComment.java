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
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * A log for an event that happened to a User.
 *
 * @author Brian Pontarelli
 */
public class UserComment implements Buildable<UserComment> {
  public String comment;

  public UUID commenterId;

  public UUID id;

  public ZonedDateTime insertInstant;

  public UUID userId;

  public UserComment() {
  }

  public UserComment(String comment, ZonedDateTime insertInstant, UUID id, UUID userId, UUID commenterId) {
    this.comment = comment;
    this.insertInstant = insertInstant;
    this.id = id;
    this.userId = userId;
    this.commenterId = commenterId;
  }

  public static UserActionLog toUserActionLog(UserComment userComment) {
    return new UserActionLog().with((l) -> l.actioneeUserId = userComment.userId)
                              .with((l) -> l.actionerUserId = userComment.commenterId)
                              .with((l) -> l.comment = userComment.comment)
                              .with((l) -> l.insertInstant = userComment.insertInstant);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserComment)) {
      return false;
    }
    UserComment that = (UserComment) o;
    return Objects.equals(comment, that.comment) &&
           Objects.equals(commenterId, that.commenterId) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment, commenterId, id, insertInstant, userId);
  }

  public void normalize() {
    comment = trim(comment);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
