[#import "_macros.ftl" as global/]
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

/// <reference lib="es2015"/>

'use strict';

import {ClientResponse, RESTClient} from "./RESTClient"

export class FusionAuthClient {
  constructor(public apiKey: string, public host: string) {
  }

[#-- @formatter:off --]
[#list apis as api]
  /**
  [#list api.comments as comment]
   * ${comment}
  [/#list]
   *
  [#list api.params![] as param]
    [#if !param.constant??]
   * @param {${global.optional(param, "ts")}${global.convertType(param.javaType, "ts")}} ${param.name} ${param.comments?join("\n   *    ")}
    [/#if]
  [/#list]
   */
  [#assign parameters = global.methodParameters(api, "ts")/]
  ${api.methodName}(${parameters}): Promise<ClientResponse> {
    return this.start()
  [#if api.method == "post" && !global.hasBodyParam(api.params![])]
    .withHeader('Content-Type', 'text/plain')
    [/#if]
    .withUri('${api.uri}')
  [#if api.authorization??]
    .withAuthorization(${api.authorization?replace('\"', '\'')})
  [/#if]
  [#list api.params![] as param]
    [#if param.type == "urlSegment"]
    .withUriSegment(${(param.constant?? && param.constant)?then(param.value, param.name)})
    [#elseif param.type == "urlParameter"]
    .withParameter('${param.parameterName}', ${(param.constant?? && param.constant)?then(param.value, param.name)})
    [#elseif param.type == "body"]
    .withJSONBody(${param.name})
    [/#if]
  [/#list]
    .withMethod("${api.method?capitalize}")
    .go();
  }

[/#list]
[#-- @formatter:on --]

  /* ===================================================================================================================
   * Private methods
   * ===================================================================================================================*/

  /**
   * creates a rest client
   *
   * @returns {RESTClient} The RESTClient that will be used to call.
   * @private
   */
  private start() {
    return new RESTClient().withAuthorization(this.apiKey).withUri(this.host);
  }
}