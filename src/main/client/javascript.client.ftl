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

var FusionAuthClient = function(apiKey, host) {
  this.apiKey = apiKey;
  this.host = host;
};

FusionAuthClient.constructor = FusionAuthClient;
//noinspection JSUnusedGlobalSymbols
FusionAuthClient.prototype = {

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
   * @param {Function} callBack The response handler call back. This function will be passed the ClientResponse object.
   */
  [#assign parameters = global.methodParameters(api, "js")/]
  ${api.methodName}: function(${parameters}${parameters?has_content?then(', callBack', 'callBack')}) {
      return this._start()
      [#if api.method == "post" && !global.hasBodyParam(api.params![])]
          .header('Content-Type', 'text/plain')
      [/#if]
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
          .go(callBack);
  },

[/#list]
  /* ===================================================================================================================
   * Private methods
   * ===================================================================================================================*/

  /**
   * creates a rest client
   *
   * @returns {RESTClient} The RESTClient that will be used to call.
   * @private
   */
  _start: function() {
    return new RESTClient().authorization(this.apiKey).setUrl(this.host);
  }
};