/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
@SuppressWarnings("JSUnusedLocalSymbols")
public enum LambdaType {
  // @formatter:off
  JWTPopulate("populate", "" +
      //language=JavaScript
      "// Using the user and registration parameters add additional values to the jwt object.\n" +
      "function populate(jwt, user, registration) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "// Using the JWT returned from UserInfo, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, jwt, id_token, tokens) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SAMLv2Reconcile("reconcile", "" +
      //language=JavaScript
      "// Using the samlResponse, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, samlResponse) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  SAMLv2Populate("populate", "" +
      //language=JavaScript
      "// Using the user and registration parameters add additional values to the SAML response.\n" +
      "function populate(samlResponse, user, registration) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "// Using the idToken returned from Apple, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, idToken) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  ExternalJWTReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the JWT provided by an external IdP, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, jwt) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "// Using the response from Facebook Me API, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, facebookUser) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  GoogleReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Google Token info API, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, idToken) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  HYPRReconcile("reconcile", "" +
      //language=JavaScript
      "// Reconcile the User and User Registration.\n" +
      "function reconcile(user, registration) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "// Using the response from the Twitter verify_credentials API, reconcile the User and User Registration.\n" +
       "function reconcile(user, registration, twitterUser) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
       "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
       "  //  console.info(JSON.stringify(user)); \n" +
       "\n" +
       "  // Happy coding! Reconcile the User here.\n" +
       "\n" +
       "  console.info('Hello World!');" +
       "\n" +
      "}\n"),

  LDAPConnectorReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from an LDAP connector, reconcile the User.\n" +
      "function reconcile(user, userAttributes) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "// Using the response returned from LinkedIn Me API, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, linkedInUser) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Reconcile the User here.\n" +
      "\n" +
      "  console.info('Hello World!');" +
      "\n" +
      "}\n"),

  EpicGamesReconcile("reconcile", "" +
      //language=JavaScript
      "// Using the response from the Epic API, reconcile the User and User Registration.\n" +
      "function reconcile(user, registration, userInfo) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function reconcile(user, registration, userInfo) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function reconcile(user, registration, userInfo) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function reconcile(user, registration, userInfo) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function reconcile(user, registration, userInfo) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function reconcile(user, registration, userInfo) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function populate(jwt, recipientEntity, targetEntities, permissions) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "   //  \n" +
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
      "function convert(group, members, options, scimGroup) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function convert(scimGroup, group, members) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
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
      "function convert(user, options, scimUser) {\n" +
       "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
       "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
       "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
       "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
       "  //  \n" +
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
      "function convert(scimUser, user) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
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
      "function validate(result, user, registration, context) {\n" +
      "    //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "    //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "    //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "    //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "    //  \n" +
      "    //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "    //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "    // Happy coding! Reconcile the User here.\n" +
      "    console.info('Hello World!');\n" +
      "}"),

  UserInfoPopulate("populate", "" +
      //language=JavaScript
      "// Using the user and registration parameters along with the JWT provided on the request add additional values to the userinfo response.\n" +
      "function populate(response, user, registration, jwt) {\n" +
      "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
      "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
      "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
      "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
      "  //  \n" +
      "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example: \n" +
      "  //  console.info(JSON.stringify(user)); \n" +
      "\n" +
      "  // Happy coding! Populate your userinfo response here.\n" +
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
