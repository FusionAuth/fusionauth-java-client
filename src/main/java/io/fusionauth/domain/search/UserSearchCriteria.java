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
package io.fusionauth.domain.search;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import io.fusionauth.domain.Buildable;

/**
 * This class is the user query. It provides a build pattern as well as public fields for use on forms and in actions.
 *
 * @author Brian Pontarelli
 */
public class UserSearchCriteria extends BaseElasticSearchCriteria implements Buildable<UserSearchCriteria> {
  public static final Set<String> SortableFields = new LinkedHashSet<>(Arrays.asList("birthDate",
                                                                                     "email",
                                                                                     "fullName",
                                                                                     "id",
                                                                                     "insertInstant",
                                                                                     "lastLoginInstant",
                                                                                     "login",
                                                                                     "tenantId",
                                                                                     "username",
                                                                                     "registrations.applicationId",
                                                                                     "registrations.id",
                                                                                     "registrations.insertInstant",
                                                                                     "registrations.lastLoginInstant",
                                                                                     "registrations.roles"));

  // Not currently supporting 'registration.*'
  public static final Set<String> DatabaseSortableFields = SortableFields.stream()
                                                                         .filter(field -> !field.startsWith("registrations."))
                                                                         .collect(Collectors.toSet());
}
