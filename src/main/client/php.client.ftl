[#import "_macros.ftl" as global/]
[#function parameter_value param]
  [#if param.constant?? && param.constant]
    [#if param.value?starts_with("search")]
      [#return "$" + param.value/] [#-- Hack for the search functions --]
    [#else]
      [#return param.value/]
    [/#if]
  [#else]
    [#return "$" + param.name/]
  [/#if]
[/#function]
<?php
namespace fusionauth;

/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

/**
 * Client that connects to a FusionAuth server and provides access to the full set of FusionAuth APIs.
 * <p/>
 * When any method is called the return value is always a ClientResponse object. When an API call was successful, the
 * response will contain the response from the server. This might be empty or contain an success object or an error
 * object. If there was a validation error or any other type of error, this will return the Errors object in the
 * response. Additionally, if FusionAuth could not be contacted because it is down or experiencing a failure, the response
 * will contain an Exception, which could be an IOException.
 *
 * @author Brian Pontarelli
 */
class FusionAuthClient
{
  /**
   * @var string
   */
  private $apiKey;

  /**
   * @var string
   */
  private $baseURL;

  /**
   * @var int
   */
  public $connectTimeout = 2000;

  /**
   * @var int
   */
  public $readTimeout = 2000;

  public function __construct($apiKey, $baseURL)
  {
    include_once 'RESTClient.php';
    $this->apiKey = $apiKey;
    $this->baseURL = $baseURL;
  }

[#list apis as api]
  /**
  [#list api.comments as comment]
   * ${comment}
  [/#list]
   *
  [#list api.params![] as param]
    [#if !param.constant??]
   * @param ${global.convertType(param.javaType, "php")} $${param.name} ${param.comments?join("\n  *     ")}
    [/#if]
  [/#list]
   *
   * @return ClientResponse The ClientResponse.
   */
  public function ${api.methodName}(${global.methodParameters(api, "php")})
  {
    return $this->start()->uri("${api.uri}")
    [#if api.authorization??]
        ->authorization(${api.authorization?replace("+ ", ". $")})
    [/#if]
    [#list api.params![] as param]
      [#if param.type == "urlSegment"]
        ->urlSegment(${(param.constant?? && param.constant)?then(param.value, "$" + param.name)})
      [#elseif param.type == "urlParameter"]
        ->urlParameter("${param.parameterName}", ${parameter_value(param)})
      [#elseif param.type == "body"]
        ->bodyHandler(new JSONBodyHandler($${param.name}))
      [/#if]
    [/#list]
        ->${api.method}()
        ->go();
  }

[/#list]
  private function start()
  {
    $rest = new RESTClient();
    return $rest->authorization($this->apiKey)
        ->url($this->baseURL)
        ->connectTimeout($this->connectTimeout)
        ->readTimeout($this->readTimeout)
        ->successResponseHandler(new JSONResponseHandler())
        ->errorResponseHandler(new JSONResponseHandler());
  }
}