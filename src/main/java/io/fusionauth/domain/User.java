/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.toLowerCase;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * The global view of a User. This object contains all global information about the user including birth date, registration information
 * preferred languages, global attributes, etc.
 *
 * @author Seth Musselman
 */
public class User extends SecureIdentity implements Buildable<User>, _InternalJSONColumn, Tenantable {
  @InternalJSONColumn
  public final List<Locale> preferredLanguages = new ArrayList<>();

  private final List<GroupMember> memberships = new ArrayList<>();

  private final List<UserRegistration> registrations = new ArrayList<>();

  public boolean active;

  public LocalDate birthDate;

  public UUID cleanSpeakId;

  public Map<String, Object> data = new LinkedHashMap<>();

  public String email;

  public ZonedDateTime expiry;

  public String firstName;

  public String fullName;

  public URI imageUrl;

  public ZonedDateTime insertInstant;

  public String lastName;

  public ZonedDateTime lastUpdateInstant;

  public String middleName;

  public String mobilePhone;

  public String parentEmail;

  public UUID tenantId;

  public ZoneId timezone;

  @InternalJSONColumn
  public UserTwoFactorConfiguration twoFactor = new UserTwoFactorConfiguration();
  //
  // [
  //   { type: "authenticator", "secret": "abc" },                   // 1st attempt
  //   { type: "authenticator", "secret": "def" },                   // 2nd attempt
  //   { type: "authenticator", "secret": "yzx" },                   // 3rd attempt
  //   { type: "sms", "mobilePhone": "720-443-3159" },               // Phone number ending in *3169
  //   { type: "sms", "mobilePhone": "720-872-1245" },               // Phone number ending in *1245
  //   { type: "email", "email": "daniel@fusionauth.io" },           // Email like this dan**@*auth.io
  //   { type: "email", "email": "djdmisc@gmail.com" }               // Email like this djd**@gmail.com
  // ]
  //
  // 1. For Authenticator, just try them all.
  // 2. For phone, when we send the message, validate it ok, does not matter which phone.
  // 3. For email, when we send the message, validate it ok, does not matter which email.
  // 4. For authenticator, to disable an authenticator, we can know which one to disable because the code will only match one. If matches more than one fail.
  //
  // Questions:
  // 1. More than one authenticator app?
  //    a) Someone walks by your desk and adds an authenticator?
  //    b) This is different than email and phone because any authenticator will work.
  //       With email and phone, at least you'd have to select to send the code there?
  // 2. Probably need notifications, "you just bought hot pockets!" (or you added an authenticator, sms or mobile phone)
  //    a) You added something
  //    b) You removed something
  //
  // TODO : Secure will remove the secret from the twoFactorMethod object.
  // TODO : If the "code" matches more than one on a disable / delete, fail and try again.
  // TODO : Add Auth App parameters to this when enabled

  // Scratch codes:
  // 1. Use them for login, I lost my device.
  // 2. Use them for login, I have my device, but not with me. Same as #1?
  // 3. Use them to remove 2FA methods/devices - Turn off the world.
  //    - Remove everything, you have to rebuild your configuration.
  //    - Causes your scratch to be cleared.
  //    - Rebuild from scratch, and get new recovery codes.


  @JacksonConstructor
  public User() {
  }

  public User(User other) {
    this.active = other.active;
    this.connectorId = other.connectorId;
    this.breachedPasswordLastCheckedInstant = other.breachedPasswordLastCheckedInstant;
    this.breachedPasswordStatus = other.breachedPasswordStatus;
    this.birthDate = other.birthDate;
    this.cleanSpeakId = other.cleanSpeakId;
    this.email = other.email;
    this.encryptionScheme = other.encryptionScheme;
    this.expiry = other.expiry;
    this.factor = other.factor;
    this.firstName = other.firstName;
    this.fullName = other.fullName;
    this.id = other.id;
    this.imageUrl = other.imageUrl;
    this.insertInstant = other.insertInstant;
    this.lastLoginInstant = other.lastLoginInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.lastName = other.lastName;
    this.memberships.addAll(other.memberships.stream().map(GroupMember::new).collect(Collectors.toList()));
    this.middleName = other.middleName;
    this.mobilePhone = other.mobilePhone;
    this.parentEmail = other.parentEmail;
    this.password = other.password;
    this.passwordChangeReason = other.passwordChangeReason;
    this.passwordChangeRequired = other.passwordChangeRequired;
    this.passwordLastUpdateInstant = other.passwordLastUpdateInstant;
    this.preferredLanguages.addAll(other.preferredLanguages);
    this.registrations.addAll(other.registrations.stream().map(UserRegistration::new).collect(Collectors.toList()));
    this.salt = other.salt;
    this.tenantId = other.tenantId;
    this.timezone = other.timezone;
    this.twoFactor = new UserTwoFactorConfiguration(other.twoFactor);
    this.username = other.username;
    this.usernameStatus = other.usernameStatus;
    this.verified = other.verified;

    if (other.data != null) {
      this.data.putAll(other.data);
    }
  }

  /**
   * Safely adds a membership to a user. This ensures no duplicates.
   *
   * @param member The member.
   */
  public void addMemberships(GroupMember member) {
    memberships.removeIf(m -> m.groupId.equals(member.groupId));
    memberships.add(member);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    User user = (User) o;
    sort();
    user.sort();
    return active == user.active &&
           Objects.equals(preferredLanguages, user.preferredLanguages) &&
           Objects.equals(memberships, user.memberships) &&
           Objects.equals(registrations, user.registrations) &&
           Objects.equals(birthDate, user.birthDate) &&
           Objects.equals(cleanSpeakId, user.cleanSpeakId) &&
           Objects.equals(data, user.data) &&
           Objects.equals(email, user.email) &&
           Objects.equals(expiry, user.expiry) &&
           Objects.equals(firstName, user.firstName) &&
           Objects.equals(fullName, user.fullName) &&
           Objects.equals(imageUrl, user.imageUrl) &&
           Objects.equals(insertInstant, user.insertInstant) &&
           Objects.equals(lastName, user.lastName) &&
           Objects.equals(lastUpdateInstant, user.lastUpdateInstant) &&
           Objects.equals(middleName, user.middleName) &&
           Objects.equals(mobilePhone, user.mobilePhone) &&
           Objects.equals(parentEmail, user.parentEmail) &&
           Objects.equals(twoFactor, user.twoFactor) &&
           Objects.equals(tenantId, user.tenantId) &&
           Objects.equals(timezone, user.timezone);
  }

  @JsonIgnore
  public int getAge() {
    if (birthDate == null) {
      return -1;
    }

    return (int) birthDate.until(LocalDate.now(), ChronoUnit.YEARS);
  }

  public GroupMember getGroupMemberForGroup(UUID id) {
    return getMemberships().stream()
                           .filter(m -> m.id.equals(id))
                           .findFirst()
                           .orElse(null);
  }

  /**
   * @return return a single identity value preferring email over username.
   */
  @JsonIgnore
  public String getLogin() {
    return email == null ? username : email;
  }

  public List<GroupMember> getMemberships() {
    return memberships;
  }

  @JsonIgnore
  public String getName() {
    if (fullName != null) {
      return fullName;
    }
    if (firstName != null) {
      return firstName + (lastName != null ? " " + lastName : "");
    }

    return null;
  }

  public UserRegistration getRegistrationForApplication(UUID id) {
    return getRegistrations().stream()
                             .filter(reg -> reg.applicationId.equals(id))
                             .findFirst()
                             .orElse(null);
  }

  public List<UserRegistration> getRegistrations() {
    return registrations;
  }

  public Set<String> getRoleNamesForApplication(UUID id) {
    UserRegistration registration = getRegistrationForApplication(id);
    return registration != null ? registration.roles : null;
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  /**
   * Return true if user data is provided for this user or any registrations.
   *
   * @return true if user data exists.
   */
  public boolean hasUserData() {
    if (!data.isEmpty()) {
      return true;
    }

    for (UserRegistration userRegistration : registrations) {
      if (userRegistration.hasRegistrationData()) {
        return true;
      }
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), preferredLanguages, memberships, registrations, active, birthDate, cleanSpeakId, data, email, expiry, firstName, fullName, imageUrl, insertInstant, lastName, lastUpdateInstant, middleName, mobilePhone, parentEmail, tenantId, timezone, twoFactor);
  }

  /**
   * Attempt to retrieve the users email address first by checking the top level and then in user data.
   *
   * @return an email address or null if no email address is found.
   */
  public String lookupEmail() {
    if (email != null) {
      return email;
    } else if (data.containsKey("email")) {
      return data.get("email").toString();
    }
    return null;
  }

  public Locale lookupPreferredLanguage(UUID applicationId) {
    for (UserRegistration registration : registrations) {
      if (registration.applicationId.equals(applicationId)) {
        if (registration.preferredLanguages.size() > 0) {
          return registration.preferredLanguages.get(0);
        }
      }
    }

    if (preferredLanguages.size() > 0) {
      return preferredLanguages.get(0);
    }

    return null;
  }

  /**
   * Normalizes all of the fields.
   */
  public void normalize() {
    Normalizer.removeEmpty(data);
    email = toLowerCase(trim(email));
    encryptionScheme = trim(encryptionScheme);
    firstName = trim(firstName);
    fullName = trim(fullName);
    lastName = trim(lastName);
    middleName = trim(middleName);
    mobilePhone = trim(mobilePhone);
    parentEmail = toLowerCase(trim(parentEmail));
    preferredLanguages.removeIf(Objects::isNull);
    Normalizer.deDuplicate(preferredLanguages);
    username = trim(username);
    if (username != null && username.length() == 0) {
      username = null;
    }

    // Clear out groups that don't have groupId
    memberships.removeIf(m -> m.groupId == null);

    // Normalize the registrations
    getRegistrations().forEach(UserRegistration::normalize);
  }

  public void removeMembershipById(UUID groupId) {
    memberships.removeIf(m -> m.groupId.equals(groupId));
  }

  /**
   * Clear out sensitive data. Password, salt, etc.
   *
   * @return this
   */
  public User secure() {
    encryptionScheme = null;
    factor = null;
    password = null;
    salt = null;
    twoFactor.secure();
    return this;
  }

  public User sort() {
    this.registrations.sort(Comparator.comparing(ur -> ur.applicationId));
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  // Synthetic method and used for backwards compatibility for the API response.
  public boolean twoFactorEnabled() {
    return twoFactor.methods.size() > 0;
  }
}
