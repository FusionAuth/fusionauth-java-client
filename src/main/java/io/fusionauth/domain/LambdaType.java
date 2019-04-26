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
 * @author Brian Pontarelli
 */
public enum LambdaType {
  JWTPopulate("populate", "" +
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
      "// Using the JWT returned from UserInfo, reconcile the User and User Registration.\n" +
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

  SAMLv2Reconcile("reconcile", "" +
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
      "}\n");

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
