[#import "_macros.ftl" as global/]
/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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

'use strict';

const RESTClient = require('./RESTClient.js');
require('promise');

const FusionAuthClient = function(apiKey, host) {
  this.apiKey = apiKey;
  this.host = host;
  this.tenantId = null;
};

FusionAuthClient.constructor = FusionAuthClient;
//noinspection JSUnusedGlobalSymbols
FusionAuthClient.prototype = {

  setTenantId: function(tenantId) {
    this.tenantId = tenantId;
    return this;
  },

[#list apis as api]
  /**
  [#list api.comments as comment]
   * ${comment}
  [/#list]
   *
  [#list api.params![] as param]
    [#if !param.constant??]
   * @param {${global.optional(param, "js")}${global.convertType(param.javaType, "js")}} ${param.name} ${param.comments?join("\n   *    ")}
    [/#if]
  [/#list]
   * @return {Promise} A Promise for the FusionAuth call.
   */
  ${api.methodName}: function(${global.methodParameters(api, "js")}) {
    return new Promise((resolve, reject) => {
      this._start()
          .uri('${api.uri}')
      [#if api.authorization??]
          .authorization(${api.authorization?replace('\"', '\'')})
      [/#if]
      [#list api.params![] as param]
        [#if param.type == "urlSegment"]
          .urlSegment(${(param.constant?? && param.constant)?then(param.value, param.name)})
        [#elseif param.type == "urlParameter"]
          .urlParameter('${param.parameterName}', ${(param.constant?? && param.constant)?then(param.value, param.name)})
        [#elseif param.type == "body"]
          .setJSONBody(${param.name})
        [/#if]
      [/#list]
          .${api.method}()
          .go(this._responseHandler(resolve, reject));
    });
  },

[/#list]
  /* ===================================================================================================================
   * Private methods
   * ===================================================================================================================*/

  /**
   * Require a parameter to be defined, if null or un-defined this throws an exception.
   * @param {Object} value The value that must be defined.
   * @param {string} name The name of the parameter.
   * @private
   */
  _requireNonNull: function(value, name) {
    if (typeof value === 'undefined' || value === null) {
      throw new Error(name + ' parameter is required.');
    }
  },

  /**
   * Returns a function to handle the promises for each call.
   *
   * @param resolve The promise's resolve function.
   * @param reject The promise's reject function.
   * @returns {Function} The function that will call either the resolve or reject functions based on the ClientResponse.
   * @private
   */
  _responseHandler: function(resolve, reject) {
    return function(response) {
      if (response.wasSuccessful()) {
        resolve(response);
      } else {
        reject(response);
      }
    };
  },

  /**
   * creates a rest client
   *
   * @returns {RESTClient} The RESTClient that will be used to call.
   * @private
   */
  _start: function() {
    const client = new RESTClient().authorization(this.apiKey).setUrl(this.host);

    if (this.tenantId !== null && typeof(this.tenantId) !== 'undefined') {
      client.header('X-FusionAuth-TenantId', this.tenantId);
    }

    return client;
  }
};

module.exports = FusionAuthClient;