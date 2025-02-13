/*
 * Copyright (c) 2021-2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.form;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the managed fields that are also put into the database during FusionAuth setup.
 * <p>
 * Internal Note: These fields are also declared in SQL in order to bootstrap the system. These need to stay in sync.
 * Any changes to these fields needs to also be reflected in mysql.sql and postgresql.sql
 *
 * @author Brian Pontarelli
 */
public final class ManagedFields {
  public static final Map<String, FormField> Values;

  static {
    Map<String, FormField> map = new HashMap<>();
    map.put("registration.preferredLanguages",
            new FormField().with(f -> f.key = "registration.preferredLanguages")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.select)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin Registration] preferred languages"));
    map.put("registration.roles",
            new FormField().with(f -> f.key = "registration.roles")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.checkbox)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin Registration] roles"));

    map.put("registration.timezone",
            new FormField().with(f -> f.key = "registration.timezone")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.select)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin Registration] timezone"));

    map.put("registration.username",
            new FormField().with(f -> f.key = "registration.username")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "user"))
                           .with(f -> f.name = "[Admin Registration] username"));

    map.put("user.birthDate",
            new FormField().with(f -> f.key = "user.birthDate")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.date)
                           .with(f -> f.data.put("leftAddon", "calendar"))
                           .with(f -> f.name = "[Admin User] birthdate"));

    map.put("user.email",
            new FormField().with(f -> f.key = "user.email")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.email)
                           .with(f -> f.data.put("leftAddon", "user"))
                           .with(f -> f.name = "[Admin User] email"));

    map.put("user.phoneNumber",
            new FormField().with(f -> f.key = "user.phoneNumber")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.phoneNumber)
                           .with(f -> f.data.put("leftAddon", "mobile"))
                           .with(f -> f.name = "[Admin User] phoneNumber"));

    map.put("identities.email",
            new FormField().with(f -> f.key = "identities.email")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.email)
                           .with(f -> f.data.put("leftAddon", "user"))
                           .with(f -> f.name = "[Admin User] identities.email"));

    map.put("identities.phoneNumber",
            new FormField().with(f -> f.key = "identities.phoneNumber")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.phoneNumber)
                           .with(f -> f.data.put("leftAddon", "mobile"))
                           .with(f -> f.name = "[Admin User] identities.phoneNumber"));

    map.put("identities.username",
            new FormField().with(f -> f.key = "identities.username")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "user"))
                           .with(f -> f.name = "[Admin User] identities.username"));

    map.put("user.firstName",
            new FormField().with(f -> f.key = "user.firstName")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] first name"));

    map.put("user.fullName",
            new FormField().with(f -> f.key = "user.fullName")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] full name"));

    map.put("user.imageUrl",
            new FormField().with(f -> f.key = "user.imageUrl")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] image URL"));

    map.put("user.lastName",
            new FormField().with(f -> f.key = "user.lastName")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] last name"));

    map.put("user.middleName",
            new FormField().with(f -> f.key = "user.middleName")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] middle name"));

    map.put("user.mobilePhone",
            new FormField().with(f -> f.key = "user.mobilePhone")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "mobile"))
                           .with(f -> f.name = "[Admin User] mobile phone"));

    map.put("user.password",
            new FormField().with(f -> f.key = "user.password")
                           .with(f -> f.confirm = true)
                           .with(f -> f.control = FormControl.password)
                           .with(f -> f.required = true)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "lock"))
                           .with(f -> f.name = "[Admin User] password"));

    map.put("user.parentEmail",
            new FormField().with(f -> f.key = "user.parentEmail")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.email)
                           .with(f -> f.data.put("leftAddon", "user"))
                           .with(f -> f.name = "[Registration] parent email"));

    map.put("user.preferredLanguages",
            new FormField().with(f -> f.key = "user.preferredLanguages")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.select)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] preferred languages"));

    map.put("user.timezone",
            new FormField().with(f -> f.key = "user.timezone")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.select)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "info"))
                           .with(f -> f.name = "[Admin User] timezone"));

    map.put("user.username",
            new FormField().with(f -> f.key = "user.username")
                           .with(f -> f.confirm = false)
                           .with(f -> f.control = FormControl.text)
                           .with(f -> f.required = false)
                           .with(f -> f.type = FormDataType.string)
                           .with(f -> f.data.put("leftAddon", "user"))
                           .with(f -> f.name = "[Admin User] username"));
    Values = Collections.unmodifiableMap(map);
  }
}
