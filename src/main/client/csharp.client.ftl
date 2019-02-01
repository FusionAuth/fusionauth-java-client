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

using FusionAuth.Error;
using FusionAuth.Domain;
using Inversoft.Restify;
using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace FusionAuth
{
  /**
   * Client that connects to a FusionAuth server and provides access to the full set of FusionAuth APIs.
   */
  public class FusionAuthClient
  {
    // Add our serializer here that includes our IdentityProviderConvert
    private static readonly JsonSerializer serializer = new JsonSerializer();

    static FusionAuthClient()
    {
      serializer.Converters.Add(new StringEnumConverter());
      serializer.Converters.Add(new DateTimeOffsetConverter());
      serializer.Converters.Add(new IdentityProviderConverter());
      serializer.NullValueHandling = NullValueHandling.Ignore;
    }

    public const string TENANT_ID_HEADER = "X-FusionAuth-TenantId";

    private readonly string apiKey;

    private readonly string baseUrl;

    private readonly string tenantId;

    private readonly IWebProxy webProxy;

    public int timeout = 2000;

    public int readWriteTimeout = 2000;

    public FusionAuthClient(string apiKey, string baseUrl)
    {
      this.apiKey = apiKey;
      this.baseUrl = baseUrl;
    }

    public FusionAuthClient(string apiKey, string baseUrl, string tenantId)
    {
      this.apiKey = apiKey;
      this.baseUrl = baseUrl;
      this.tenantId = tenantId;
    }

    public FusionAuthClient(string apiKey, string baseUrl, string tenantId, IWebProxy webProxy)
    {
      this.apiKey = apiKey;
      this.baseUrl = baseUrl;
      this.tenantId = tenantId;
      this.webProxy = webProxy;
    }

    public FusionAuthClient(string apiKey, string baseUrl, IWebProxy webProxy)
    {
      this.apiKey = apiKey;
      this.baseUrl = baseUrl;
      this.webProxy = webProxy;
    }

[#list apis as api]
    /**
  [#list api.comments as comment]
     * ${comment}
  [/#list]
     *
  [#list api.params![] as param]
    [#if !param.constant??]
     * @param ${param.name} ${param.comments?join("\n     * ")}
    [/#if]
  [/#list]
     * @return When successful, the response will contain the log of the action. If there was a validation error or any
     * other type of error, this will return the Errors object in the response. Additionally, if FusionAuth could not be
     * contacted because it is down or experiencing a failure, the response will contain an Exception, which could be an
     * IOException.
     */
    public ClientResponse<${global.convertType(api.successResponse, "csharp")}, ${global.convertType(api.errorResponse, "csharp")}> ${api.methodName?cap_first}(${global.methodParameters(api, "csharp")})
    {
        return Start<${global.convertType(api.successResponse, "csharp")}, ${global.convertType(api.errorResponse, "csharp")}>().Uri("${api.uri}")
                                      [#if api.authorization??]
                                          .Authorization(${api.authorization})
                                      [/#if]
                                      [#list api.params![] as param]
                                        [#if param.type == "urlSegment"]
                                          .UrlSegment(${(param.constant?? && param.constant)?then(param.value, param.name)})
                                        [#elseif param.type == "urlParameter"]
                                          .UrlParameter("${param.parameterName}", ${(param.constant?? && param.constant)?then(param.value, param.name)})
                                        [#elseif param.type == "body"]
                                          .BodyHandler(new JSONBodyHandler(${param.name}, serializer))
                                        [/#if]
                                      [/#list]
                                          .${api.method?cap_first}()
                                          .Go();
    }

[/#list]
    // Start initializes and returns RESTClient
    private RESTClient<T, U> Start<T, U>()
    {
        var client = new RESTClient<T, U>().Authorization(apiKey)
                                   .SuccessResponseHandler(typeof(T) == typeof(RESTVoid) ? null : new JSONResponseHandler<T>(serializer))
                                   .ErrorResponseHandler(typeof(U) == typeof(RESTVoid) ? null : new JSONResponseHandler<U>(serializer))
                                   .Url(baseUrl)
                                   .Timeout(timeout)
                                   .ReadWriteTimeout(readWriteTimeout)
                                   .Proxy(webProxy);


        if (tenantId != null) {
          client.Header(TENANT_ID_HEADER, tenantId);
        }

        return client;
    }
  }
}