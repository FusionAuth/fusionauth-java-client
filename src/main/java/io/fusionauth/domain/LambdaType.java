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

/**
 * The types of lambdas that indicate how they are invoked by FusionAuth.
 *
 * @author Brian Pontarelli and Brett Pontarelli
 */
public enum LambdaType {
  // [brettp]TODO: HYPRReconcile and LDAPConnectorReconcile have slightly different text, but
  //               I am wondering if their text is really accurate still.  Can they not use
  //               the "reconcile" text like the others?
  AppleReconcile("reconcile", "idToken returned from Apple", "idToken"),

//  EpicReconcile("reconcile", "Epic API", "epicUser"),

  ExternalJWTReconcile("reconcile", "JWT provided by an external IdP", "jwt"),

  FacebookReconcile("reconcile", "Facebook Me API", "facebookUser"),

  GoogleReconcile("reconcile", "Google Token info API", "idToken"),

  HYPRReconcile("reconcile", "Reconcile the User and User Registration", ""),

  JWTPopulate("populate", "jwt object", "jwt"),

  LDAPConnectorReconcile("reconcile", "Using the response from an LDAP connector, reconcile the User", ""),

  LinkedInReconcile("reconcile", "LinkedIn Me API", "linkedInUser"),

  NintendoReconcile("reconcile", "Nintendo API", "nintendoUser"),

  OpenIDReconcile("reconcile", "JWT returned from UserInfo", "jwt"),

  SAMLv2Populate("populate", "SAML response", "samlResponse"),

  SAMLv2Reconcile("reconcile", "samlResponse", "samlResponse"),

//  SonyPSNReconcile("reconcile", "SonyPSN API", "sonyPSNUser"),

//  SteamReconcile("reconcile", "Steam API", "steamUser"),

//  TwitchReconcile("reconcile", "Twitch API", "twitchUser"),

  TwitterReconcile("reconcile", "Twitter verify_credentials API", "twitterUser"),

//  XBOXReconcile("reconcile", "XBOX API", "xboxUser");

  private final String functionName;

  private final String message;

  private final String variableName;

  LambdaType(String functionName, String message, String variableName) {
    this.functionName = functionName;
    this.message = message;
    this.variableName = variableName;
  }

  public String getExample() {
    String example = "";
    final String helpersText = "  //  When writing a lambda we've added a few helpers to make life easier.\n" +
                               "  //  console.info('Hello World');         # This will create an EventLog of type Information\n" +
                               "  //  console.error('Not good.');          # This will create an EventLog of type Error\n" +
                               "  //  console.debug('Step 42 completed.'); # This will create an EventLog of type Debug\n" +
                               "  //\n" +
                               "  //  To dump an entire object to the EventLog you can use JSON.stringify, for example:\n" +
                               "  //  console.info(JSON.stringify(user));\n" +
                               "\n";

    if (functionName.equals("populate")) {
      example = "// Using the user and registration parameters add additional values to the " + message + ".\n" +
                "function populate(" + variableName + ", user, registration) {\n" +
                helpersText +
                "  // Happy coding! Reconcile the User here.\n" +
                "\n" +
                "  console.info('Hello World!');" +
                "}\n";
    }

    if (functionName.equals("reconcile")) {
      if (variableName.equals("")) {
        example = "// " + message + ".\n" +
                  "function reconcile(user, registration) {\n" +
                  helpersText;
      } else {
        example = "// Using the response from the " + message + ", reconcile the User and User Registration.\n" +
                  "function reconcile(user, registration, " + variableName + ") {\n" +
                  helpersText;
      }

      example += "  // Happy coding! Reconcile the User here.\n" +
                 "\n" +
                 "  console.info('Hello World!');" +
                 "}\n";
    }

    return example;
  }

  public String getFunctionName() {
    return functionName;
  }
  }
