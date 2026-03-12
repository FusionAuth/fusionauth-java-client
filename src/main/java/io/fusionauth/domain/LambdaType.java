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

/**
 * The types of lambdas that indicate how they are invoked by FusionAuth.
 *
 * @author Brian Pontarelli
 */
@SuppressWarnings("ALL")
public enum LambdaType {
  // This is an ordinal enum, so make sure new values are added to the end
  // @formatter:off
  JWTPopulate("populate", "" +
      //language=JavaScript
      "// Using the user and registration parameters add additional values to the jwt object.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/jwt-populate\n" +
      "// Optionally accept a fourth parameter named 'context' which provides additional request context.\n" +
      "// - If provided, FusionAuth will graft context.services.secrets for secret retrieval.\n" +
      "function populate(jwt, user, registration, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Populate your JWT here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),


  OpenIDReconcile("reconcile", "" +
       //language=JavaScript
      "// This is based on the default OpenID Connect reconcile using the response from the UserInfo API, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/openid-connect-response-reconcile\n" +
      "function reconcile(user, registration, jwt, id_token, tokens, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // For example: This line prints the jwt object to the event log\n" +
      "  console.info(JSON.stringify(jwt, null, 2));\n" +
      "\n" +
      "  // The following code is our default reconciliation. Happy coding!\n" +
      "\n" +
      "  user.firstName = jwt.given_name;\n" +
      "  user.middleName = jwt.middle_name;\n" +
      "  user.lastName = jwt.family_name;\n" +
      "  user.fullName = jwt.name;\n" +
      "  user.imageUrl = jwt.picture;\n" +
      "  user.mobilePhone = jwt.phone_number;\n" +
      "\n" +
      "  // https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims\n" +
      "  if (jwt.birthdate && jwt.birthdate !== '0000') {\n" +
      "    if (jwt.birthdate.length === 4) {\n" +
      "      // Only a year was provided, set to January 1.\n" +
      "      user.birthDate = jwt.birthdate + '-01-01';\n" +
      "    } else {\n" +
      "      user.birthDate = jwt.birthdate;\n" +
      "    }\n" +
      "  }\n" +
      "\n" +
      "  // https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims\n" +
      "  if (jwt.locale) {\n" +
      "    user.preferredLanguages = user.preferredLanguages || [];\n" +
      "    // Replace the dash with an under_score.\n" +
      "    user.preferredLanguages.push(jwt.locale.replace('-', '_'));\n" +
      "  }\n" +
      "\n" +
      "  // Set preferred_username in registration.\n" +
      "  // - This is just for display purposes, this value cannot be used to uniquely identify\n" +
      "  //   the user in FusionAuth.\n" +
      "  registration.username = jwt.preferred_username;\n" +
      "\n" +
      "  // Reconciled user:\n" +
      "  console.info(JSON.stringify(user, null, 2));\n" +
      "}\n"),

  SAMLv2Reconcile("reconcile", "" +
      //language=JavaScript
      "// This is based on the default SAML v2 reconcile using the AuthN response, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/samlv2-response-reconcile\n" +
      "function reconcile(user, registration, samlResponse, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // For example: This line prints the samlResponse object to the event log\n" +
      "  console.info(JSON.stringify(samlResponse, null, 2));\n" +
      "\n" +
      "  // The following code is our default reconciliation. Happy coding!\n" +
      "\n" +
      "  const getAttribute = function(samlResponse, attribute) {\n" +
      "    const values = samlResponse.assertion.attributes[attribute];\n" +
      "    if (values && values.length > 0) {\n" +
      "      return values[0];\n" +
      "    }\n" +
      "\n" +
      "    return null;\n" +
      "  };\n" +
      "\n" +
      "  // Retrieve an attribute from the samlResponse\n" +
      "  // - Arguments [2 .. ] provide a preferred order of attribute names to lookup the value in the response.\n" +
      "  const defaultIfNull = function(samlResponse) {\n" +
      "    for (let i = 1; i < arguments.length; i++) {\n" +
      "      const value = getAttribute(samlResponse, arguments[i]);\n" +
      "      if (value !== null) {\n" +
      "        return value;\n" +
      "      }\n" +
      "    }\n" +
      "  };\n" +
      "\n" +
      "  user.birthDate = defaultIfNull(samlResponse, 'http://schemas.xmlsoap.org/ws/2005/05/identity/claims/dateofbirth', 'birthdate', 'date_of_birth');\n" +
      "  user.firstName = defaultIfNull(samlResponse, 'http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname', 'first_name');\n" +
      "  user.lastName = defaultIfNull(samlResponse, 'http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname', 'last_name');\n" +
      "  user.fullName = defaultIfNull(samlResponse, 'http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name', 'name', 'full_name');\n" +
      "  user.mobilePhone = defaultIfNull(samlResponse, 'http://schemas.xmlsoap.org/ws/2005/05/identity/claims/mobilephone', 'mobile_phone');\n" +
      "\n" +
      "  // Reconciled user:\n" +
      "  console.info(JSON.stringify(user, null, 2));\n" +
      "}\n"),

  SAMLv2Populate("populate", "" +
      //language=JavaScript
      "// Using the user and registration parameters add additional values to the SAML response.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/samlv2-response-populate\n" +
      "function populate(samlResponse, user, registration, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(samlResponse)); \n" +
      "\n" +
      "  // Happy coding! Populate the SAML response here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  AppleReconcile("reconcile", "" +
      //language=JavaScript
      "// This is based on the default Apple reconcile using the id_token, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/apple-reconcile\n" +
      "function reconcile(user, registration, idToken, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // For example: This line prints the idToken object to the event log\n" +
      "  console.info(JSON.stringify(idToken, null, 2));\n" +
      "\n" +
      "  // The following code is our default reconciliation. Happy coding!\n" +
      "\n" +
      "  // During the first login attempt, the user object will be available which may contain first and last name.\n" +
      "  if (idToken.user && idToken.user.name) {\n" +
      "    user.firstName = idToken.user.name.firstName || user.firstName;\n" +
      "    user.lastName = idToken.user.name.lastName || user.lastName;\n" +
      "  }\n" +
      "\n" +
      "  // Reconciled user:\n" +
      "  console.info(JSON.stringify(user, null, 2));\n" +
      "}\n"),

  ExternalJWTReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the JWT provided by an external IdP, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/external-jwt-reconcile\n" +
      "function reconcile(user, registration, jwt, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  FacebookReconcile("reconcile", "" +
      //language=JavaScript
      "// This is based on the default Facebook reconcile using the response from Facebook Me API, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/facebook-reconcile\n" +
      "function reconcile(user, registration, facebookUser, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // For example: This line prints the facebookUser object to the event log\n" +
      "  console.info(JSON.stringify(facebookUser, null, 2));\n" +
      "\n" +
      "  // The following code is our default reconciliation. Happy coding!\n" +
      "\n" +
      "  user.firstName = facebookUser.first_name;\n" +
      "  user.middleName = facebookUser.middle_name;\n" +
      "  user.lastName = facebookUser.last_name;\n" +
      "  user.fullName = facebookUser.name;\n" +
      "\n" +
      "  if (facebookUser.picture && !facebookUser.picture.data.is_silhouette) {\n" +
      "    user.imageUrl = facebookUser.picture.data.url;\n" +
      "  }\n" +
      "\n" +
      "  if (facebookUser.birthday) {\n" +
      "    // Convert MM/dd/yyyy -> YYYY-MM-DD\n" +
      "    const parts = facebookUser.birthday.split('/');\n" +
      "    user.birthDate = parts[2] + '-' + parts[0] + '-' + parts[1];\n" +
      "  }\n" +
      "\n" +
      "  // Reconciled user:\n" +
      "  console.info(JSON.stringify(user, null, 2));\n" +
      "}\n"),

  GoogleReconcile("reconcile", "" +
      //language=JavaScript
      "// This is based on the default Google reconcile using the response from the Google Token Info API, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/google-reconcile\n" +
      "function reconcile(user, registration, idToken, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // For example: This line prints the idToken object to the event log\n" +
      "  console.info(JSON.stringify(idToken, null, 2));\n" +
      "\n" +
      "  // The following code is our default reconciliation. Happy coding!\n" +
      "\n" +
      "  // The idToken is the response from the tokeninfo endpoint\n" +
      "  // https://developers.google.com/identity/sign-in/web/backend-auth#calling-the-tokeninfo-endpoint\n" +
      "  user.firstName = idToken.given_name;\n" +
      "  user.lastName = idToken.family_name;\n" +
      "  user.fullName = idToken.name;\n" +
      "  user.imageUrl = idToken.picture;\n" +
      "\n" +
      "  // Reconciled user:\n" +
      "  console.info(JSON.stringify(user, null, 2));\n" +
      "}\n"),

  HYPRReconcile("reconcile", "" +
      //language=JavaScript
      "// Reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/hypr-reconcile\n" +
      "function reconcile(user, registration, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  TwitterReconcile("reconcile", "" +
      //language=JavaScript
      "// This is based on the default Twitter reconcile using the response from the Twitter Verify Credentials API, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/twitter-reconcile\n" +
       "function reconcile(user, registration, twitterUser, context) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
       "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
       "  //\n" +
       "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
       "  //  console.info(JSON.stringify(user)); \n" +
       "\n" +
       "  // For example: This line prints the twitterUser object to the event log\n" +
       "  console.info(JSON.stringify(twitterUser, null, 2));\n" +
       "\n" +
       "  // The following code is our default reconciliation. Happy coding!\n" +
       "\n" +
       "  // Set name if available in the response\n" +
       "  if (twitterUser.name) {\n" +
       "    user.fullName = twitterUser.name;\n" +
       "  }\n" +
       "\n" +
       "  // https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners.html\n" +
       "  if (twitterUser.profile_image_url_https) {\n" +
       "    // Remove the _normal suffix to get the original size.\n" +
       "    user.imageUrl = twitterUser.profile_image_url_https.replace('_normal.png', '.png');\n" +
       "  }\n" +
       "\n" +
       "  // Set twitter screen_name in registration.\n" +
       "  // - This is just for display purposes, this value cannot be used to uniquely identify\n" +
       "  //   the user in FusionAuth.\n" +
       "  registration.username = twitterUser.screen_name;\n" +
       "\n" +
       "  // Reconciled user:\n" +
       "  console.info(JSON.stringify(user, null, 2));\n" +
       "}\n"),

  LDAPConnectorReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from an LDAP connector, reconcile the User.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/ldap-connector-reconcile\n" +
      "function reconcile(user, userAttributes, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  LinkedInReconcile("reconcile", "" +
      //language=JavaScript
      "// This is based on the default LinkedIn reconcile using the response from LinkedIn, modify this to your liking.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/linkedin-reconcile\n" +
      "function reconcile(user, registration, linkedInUser, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // For example: This line prints the linkedInUser object to the event log\n" +
      "  console.info(JSON.stringify(linkedInUser, null, 2));\n" +
      "\n" +
      "  // The following code is our default reconciliation. Happy coding!\n" +
      "\n" +
      "  // Depending on how and when you have set up your LinkedIn application you may get a different response back in the linkedInUser.\n" +
      "  //\n" +
      "  // The first checks apply if you are using the \"openid\", \"email\", and \"profile\" scopes.\n" +
      "  // If so FusionAuth will call the LinkedIn UserInfo API.\n" +
      "  // See https://learn.microsoft.com/en-us/linkedin/consumer/integrations/self-serve/sign-in-with-linkedin-v2#api-request-to-retreive-member-details\n" +
      "  //\n" +
      "  // The second checks apply if you are using the legacy program and Profile API with the \"r_liteprofile\" or \"r_basicprofile\" scopes.\n" +
      "  // See https://learn.microsoft.com/en-us/linkedin/shared/integrations/people/profile-api\n" +
      "\n" +
      "  if (linkedInUser.given_name) {\n" +
      "    user.firstName = linkedInUser.given_name;\n" +
      "  } else if (linkedInUser.localizedFirstName) {\n" +
      "    user.firstName = linkedInUser.localizedFirstName;\n" +
      "  }\n" +
      "\n" +
      "  if (linkedInUser.family_name) {\n" +
      "    user.lastName = linkedInUser.family_name;\n" +
      "  } else if (linkedInUser.localizedLastName) {\n" +
      "    user.lastName = linkedInUser.localizedLastName;\n" +
      "  }\n" +
      "\n" +
      "  if (linkedInUser.picture) {\n" +
      "    // UserInfo will only supply one image size\n" +
      "    user.imageUrl = linkedInUser.picture;\n" +
      "  } else if (linkedInUser.profilePicture){\n" +
      "    // LinkedIn may return several images sizes.\n" +
      "    // See https://docs.microsoft.com/en-us/linkedin/shared/references/v2/profile/profile-picture\n" +
      "    // We'll sort the array by descending size and then grab the largest one.\n" +
      "    const images = linkedInUser.profilePicture['displayImage~'].elements || [];\n" +
      "    images.sort(function(a, b) {\n" +
      "      return b.data[\\\"com.linkedin.digitalmedia.mediaartifact.StillImage\\\"].displaySize.width - a.data[\\\"com.linkedin.digitalmedia.mediaartifact.StillImage\\\"].displaySize.width;\n" +
      "    });\n" +
      "    if (images.length > 0) {\n" +
      "      user.imageUrl = images[0].identifiers[0].identifier;\n" +
      "    }\n" +
      "  }\n" +
      "\n" +
      "  // Reconciled user:\n" +
      "  console.info(JSON.stringify(user, null, 2));\n" +
      "}\n"),

  EpicGamesReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Epic API, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/epic-games-reconcile\n" +
      "function reconcile(user, registration, userInfo, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  NintendoReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Nintendo API, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/nintendo-reconcile\n" +
      "function reconcile(user, registration, userInfo, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SonyPSNReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the SonyPSN API, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/sony-playstation-network-reconcile\n" +
      "function reconcile(user, registration, userInfo, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SteamReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Steam API, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/steam-reconcile\n" +
      "function reconcile(user, registration, userInfo, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  TwitchReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Twitch API, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/twitch-reconcile\n" +
      "function reconcile(user, registration, userInfo, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  XboxReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Xbox Token info API, reconcile the User and User Registration.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/xbox-reconcile\n" +
      "function reconcile(user, registration, userInfo, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  ClientCredentialsJWTPopulate("populate", "" +
      //language=JavaScript
      "// Using the two Entity and permission parameters you can populate a client credentials grant JWT.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/client-credentials-jwt-populate\n" +
      "function populate(jwt, recipientEntity, targetEntities, permissions, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Populate your JWT here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SCIMServerGroupRequestConverter("convert", "" +
      //language=JavaScript
      "// Using the incoming SCIM group, build a FusionAuth group by mapping incoming fields to the correct FusionAuth group fields.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/scim-group-request-converter\n" +
      "function convert(group, members, options, scimGroup, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(scimGroup)); \n" +
      "\n" +
      "  // Happy coding! Convert the SCIM group to a FusionAuth group here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SCIMServerGroupResponseConverter("convert", "" +
      //language=JavaScript
      "// Using the FusionAuth group, build a SCIM group by mapping group fields the correct SCIM group fields.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/scim-group-response-converter\n" +
      "function convert(scimGroup, group, members, context) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
       "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
       "  //\n" +
       "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
       "  //  console.info(JSON.stringify(group)); \n" +
       "\n" +
       "  // Happy coding! Convert the FusionAuth group to a SCIM group here.\n" +
       "\n" +
       "  console.info('Hello World!');" +
       "\n" +
      "}\n"),

  SCIMServerUserRequestConverter("convert", "" +
      //language=JavaScript
      "// Using the incoming SCIM user, build a FusionAuth user by mapping incoming fields to the correct FusionAuth user fields.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/scim-user-request-converter\n" +
      "function convert(user, options, scimUser, context) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
       "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
       "  //\n" +
       "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
       "  //  console.info(JSON.stringify(scimUser)); \n" +
       "\n" +
       "  // Happy coding! Convert the SCIM user to a FusionAuth user here.\n" +
       "\n" +
       "  console.info('Hello World!');" +
       "\n" +
      "}\n"),

  SCIMServerUserResponseConverter("convert", "" +
      //language=JavaScript
      "// Using the FusionAuth user, build a SCIM user by mapping user fields to the correct SCIM user fields.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/scim-user-response-converter\n" +
      "function convert(scimUser, user, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Convert the SCIM user to a FusionAuth user here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SelfServiceRegistrationValidation("validate",
      //language=JavaScript
       "// Validate the self-service registration form here\n" +
       "// https://fusionauth.io/docs/extend/code/lambdas/self-service-registration\n" +
      "function validate(result, user, registration, context) {\n" +
      "    //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "    //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "    //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "    //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "    //  \n" +
       "    //  const secretValue = context.services.secrets.get('secret-name');\n" +
       "    //\n" +
       "    //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "    //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "    // Happy coding! Reconcile the User here.\n" +
      "    console.info('Hello World!');\n" +
      "}"),

  UserInfoPopulate("populate", "" +
      //language=JavaScript
      "// Using the user and registration parameters along with the JWT provided on the request add additional values to the userInfo response.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/userinfo-populate\n" +
      "function populate(userInfo, user, registration, jwt, context) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
      "  //\n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Populate your userInfo response here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  LoginValidation("validate", "" +
       //language=JavaScript
       "// Validate the login request by optionally setting errors in the result.\n" +
       "// https://fusionauth.io/docs/extend/code/lambdas/login-validation\n" +
       "function validate(result, user, registration, context) {\n" +
       "  // Use the provided user, registration and context arguments to perform additional validation.\n" +
       "  // - To fail a login request, add one or more field or general errors to the result.\n" +
       "  //    The error schema can be found in the FusionAuth API documentation.\n" +
       "  //    - https://fusionauth.io/docs/apis/errors\n" +
       "  // \n"+
       "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
       "  //\n" +
       "  // Below is an example validation that will reject a login request when an email domain is\n" +
       "  // equal to acme.com and the authentication type is not PASSWORD.\n"+
       "  // \n"+
       "  //  if (user.email && user.email.endsWith(\"@acme.com\") && context.authenticationType !== \"PASSWORD\") {\n"+
       "  //    result.errors.generalErrors = [{\n"+
       "  //      code: \"[LoginRestricted]\",\n" +
       "  //      message: \"Your account is restricted.\"\n" +
       "  //    }];\n"+
       "  //  }\n"+
       "  // \n"+
       "\n" +
       "  // Happy coding! Populate your login validation lambda here.\n" +
       "\n" +
       "  console.info('Hello World!');" +
       "\n" +
       "}\n"),
  MFARequirement("checkRequired", "" +
      //language=JavaScript
      "// Check whether MFA is required, for a particular action, user, and application, in a given context.\n" +
      "// https://fusionauth.io/docs/extend/code/lambdas/mfa-requirement\n" +
      "function checkRequired(result, user, registration, context) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
       "  //  const secretValue = context.services.secrets.get('secret-name');\n" +
       "  //\n" +
       "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
       "  //  console.info(JSON.stringify(user)); \n" +
       "\n" +
       "  // Happy coding! Perform your MFA requirement check here.\n" +
       "\n" +
       "  console.info('Hello World!');" +
       "\n" +
      "}\n");
  // @formatter:on

  private final String example;

  private final String functionName;

  LambdaType(String functionName, String example) {
    this.functionName = functionName;
    this.example = example;
  }

  public String getExample() {
    return example;
  }

  public String getFunctionName() {
    return functionName;
  }
}
