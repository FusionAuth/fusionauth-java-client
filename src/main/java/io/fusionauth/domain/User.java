/*
 * Copyright (c) 2019-2025, FusionAuth, All Rights Reserved
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.toLowerCase;
import static io.fusionauth.domain.util.Normalizer.trim;
import static io.fusionauth.domain.util.Normalizer.trimToNull;

/**
 * The public, global view of a User. This object contains all global information about the user including birthdate, registration information
 * preferred languages, global attributes, etc.
 *
 * @author Seth Musselman
 */
public class User extends SecureIdentity implements Buildable<User>, Tenantable {
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

  public UserTwoFactorConfiguration twoFactor = new UserTwoFactorConfiguration();

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
    this.identities.addAll(other.identities.stream().map(UserIdentity::new).collect(Collectors.toCollection(ArrayList::new)));
    this.memberships.addAll(other.memberships.stream().map(GroupMember::new).collect(Collectors.toCollection(ArrayList::new)));
    this.middleName = other.middleName;
    this.mobilePhone = other.mobilePhone;
    this.parentEmail = other.parentEmail;
    this.password = other.password;
    this.passwordChangeReason = other.passwordChangeReason;
    this.passwordChangeRequired = other.passwordChangeRequired;
    this.passwordLastUpdateInstant = other.passwordLastUpdateInstant;
    this.preferredLanguages.addAll(other.preferredLanguages);
    this.registrations.addAll(other.registrations.stream().map(UserRegistration::new).collect(Collectors.toCollection(ArrayList::new)));
    this.salt = other.salt;
    this.tenantId = other.tenantId;
    this.timezone = other.timezone;
    this.twoFactor = new UserTwoFactorConfiguration(other.twoFactor);
    this.uniqueUsername = other.uniqueUsername;
    this.username = other.username;
    this.usernameStatus = other.usernameStatus;
    this.verified = other.verified;
    this.verifiedInstant = other.verifiedInstant;

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

  /**
   * @return return a single identity value preferring email to username.
   */
  @JsonIgnore
  public String getLogin() {
    return email == null ? uniqueUsername : email;
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
   * Whether the user contains an identity of the type
   *
   * @param identityType type to check
   * @return true if exists, false if not
   */
  public boolean hasIdentityType(IdentityType identityType) {
    return identities.stream()
                     .anyMatch(ui -> ui.type.is(identityType));
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
    return Objects.hash(super.hashCode(), preferredLanguages, memberships, registrations, active, birthDate, cleanSpeakId, data,
                        email,
                        expiry, firstName, fullName, imageUrl, insertInstant, lastName, lastUpdateInstant, middleName, mobilePhone, parentEmail, tenantId, timezone, twoFactor);
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
   * Normalizes all the fields.
   */
  public void normalize() {
    Normalizer.removeEmpty(data);
    // TODO : ENG-1 : Daniel : Should we keep this or move it into the service? We might also want to remove any identity with an empty value?
    email = toLowerCase(trimToNull(email));
    identities.forEach(UserIdentity::normalize);
    encryptionScheme = trim(encryptionScheme);
    firstName = trim(firstName);
    fullName = trim(fullName);
    lastName = trim(lastName);
    middleName = trim(middleName);
    mobilePhone = trim(mobilePhone);
    parentEmail = toLowerCase(trim(parentEmail));
    Normalizer.removeEmpty(preferredLanguages);
    Normalizer.deDuplicate(preferredLanguages);
    preferredLanguages.removeIf(l -> l.toString().equals(""));
    // TODO : ENG-1 : Daniel : Should we keep this or move it into the service?
    username = trimToNull(username);

    // Clear out groups that don't have groupId
    memberships.removeIf(m -> m.groupId == null);

    // Normalize the registrations
    getRegistrations().forEach(UserRegistration::normalize);
  }

  /**
   * Removes identities of the specified type
   *
   * @param identityType identity type to remove
   */
  public void removeIdentitiesOfType(IdentityType identityType) {
    identities.removeIf(i -> i.type.is(identityType));
  }

  public void removeMembershipById(UUID groupId) {
    memberships.removeIf(m -> m.groupId.equals(groupId));
  }

  /**
   * Replaces identities in the identities collection, by type, with the supplied identities.
   * Any existing identities on the User that are not of the same type will be left alone
   *
   * @param replacementIdentities identities that should overwrite what is currently on the user
   */
  public void replaceIdentities(List<UserIdentity> replacementIdentities) {
    Set<IdentityType> replaceTypes = replacementIdentities.stream()
                                                          .map(i -> i.type)
                                                          .collect(Collectors.toSet());

    // first, remove the existing identities that our replacements will cover. we want to do this
    // first since we may have multiple replacements of the same type and we don't want to step on each other
    identities.removeIf(existing -> replaceTypes.contains(existing.type));

    // now we can add our replacements
    identities.addAll(replacementIdentities);
  }

  /**
   * @return email identity if it exists, if not, username if it exists, otherwise the first identity in the collection
   */
  public UserIdentity resolveFirstIdentity() {
    return Optional.ofNullable(resolveLegacyIdentity())
                   .orElse(identities.stream()
                                     .findFirst()
                                     .orElse(null));
  }

  /**
   * Resolves the identity based on the parameters
   *
   * @param loginId     loginId to lookup. Could be an email address, username
   * @param loginIdType identity type describing what loginId is
   * @return matching identity (or null if no matching identity exists)
   */
  public UserIdentity resolveIdentity(String loginId, IdentityType loginIdType) {
    List<IdentityType> identityTypes = null;
    if (loginIdType != null) {
      identityTypes = Arrays.asList(loginIdType);
    }

    return resolveIdentity(loginId, identityTypes);
  }

  /**
   * Resolves the identity based on the parameters
   *
   * @param loginId       loginId to lookup. Could be an email address, username
   * @param identityTypes identity types describing what loginId could represent. If null or empty, default of email, username will be used
   * @return First identity that matches the provided loginId/value and identityType in order (or null if no matching identity exists)
   */
  public UserIdentity resolveIdentity(String loginId, List<IdentityType> identityTypes) {
    if (identityTypes == null || identityTypes.isEmpty()) {
      identityTypes = Arrays.asList(IdentityType.email, IdentityType.username);
    }

    for (IdentityType type : identityTypes) {
      UserIdentity result = identities.stream()
                                      .filter(i -> i.type.is(type))
                                      // TODO : ENG-1757 : Brady : We need to properly canonicalize loginId here. lowerCase was added to get our existing
                                      //                           connector tests passing
                                      .filter(i -> i.value.equals(loginId.toLowerCase()))
                                      .findFirst()
                                      .orElse(null);

      if (result != null) {
        return result;
      }
    }

    return null;
  }

  /**
   * @return email identity if it exists, if not, username if it exists, otherwise null
   */
  public UserIdentity resolveLegacyIdentity() {
    return resolvePrimaryIdentity(IdentityType.email, IdentityType.username);
  }

  /**
   * Resolves the first (of the types provided) matching primary identity
   *
   * @param loginIdTypes types to match
   * @return First matching or null if none match
   */
  public UserIdentity resolvePrimaryIdentity(IdentityType... loginIdTypes) {
    for (IdentityType loginIdType : loginIdTypes) {
      UserIdentity result = resolvePrimaryIdentity(loginIdType);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  @JsonIgnore
  public UserIdentity resolvePrimaryIdentity(IdentityType loginIdType) {
    return identities.stream()
                     // TODO: ENG-1800 : Daniel : Finish this
                     .filter(i -> i.primary)
                     .filter(i -> i.type.is(loginIdType))
                     .findFirst()
                     .orElse(null);
  }

  /**
   * Retrieve all identities that match the supplied type
   *
   * @param identityType type to check
   * @return list of matching identities
   */
  public List<UserIdentity> retrieveIdentitiesOfType(IdentityType identityType) {
    return identities.stream().filter(i -> i.type.is(identityType))
                     .collect(Collectors.toList());
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
    // use same timestamp for all rows without an insert instant
    ZonedDateTime now = ZonedDateTime.now();
    this.identities.sort(Comparator.<UserIdentity, ZonedDateTime>comparing(i -> Optional.ofNullable(i.insertInstant)
                                                                                        // if we don't have one, assuming it's about
                                                                                        // to be inserted, which would put at the the 'bottom'
                                                                                        // of the list, is sensible
                                                                                        .orElse(now))
                                   .thenComparing(i -> i.type)
                                   .thenComparing(i -> i.value));
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
