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
package io.fusionauth.client;

import java.util.Collection;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inversoft.error.Errors;
import com.inversoft.json.JacksonModule;
import com.inversoft.rest.ClientResponse;
import com.inversoft.rest.JSONBodyHandler;
import com.inversoft.rest.JSONResponseHandler;
import com.inversoft.rest.RESTClient;
import io.fusionauth.domain.api.ApplicationRequest;
import io.fusionauth.domain.api.ApplicationResponse;
import io.fusionauth.domain.api.AuditLogRequest;
import io.fusionauth.domain.api.AuditLogResponse;
import io.fusionauth.domain.api.AuditLogSearchRequest;
import io.fusionauth.domain.api.AuditLogSearchResponse;
import io.fusionauth.domain.api.EmailTemplateRequest;
import io.fusionauth.domain.api.EmailTemplateResponse;
import io.fusionauth.domain.api.GroupRequest;
import io.fusionauth.domain.api.GroupResponse;
import io.fusionauth.domain.api.IdentityProviderRequest;
import io.fusionauth.domain.api.IdentityProviderResponse;
import io.fusionauth.domain.api.IntegrationRequest;
import io.fusionauth.domain.api.IntegrationResponse;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.LoginResponse;
import io.fusionauth.domain.api.MemberDeleteRequest;
import io.fusionauth.domain.api.MemberRequest;
import io.fusionauth.domain.api.MemberResponse;
import io.fusionauth.domain.api.OAuthConfigurationResponse;
import io.fusionauth.domain.api.PasswordValidationRulesResponse;
import io.fusionauth.domain.api.PreviewRequest;
import io.fusionauth.domain.api.PreviewResponse;
import io.fusionauth.domain.api.PublicKeyResponse;
import io.fusionauth.domain.api.SystemConfigurationRequest;
import io.fusionauth.domain.api.SystemConfigurationResponse;
import io.fusionauth.domain.api.TenantRequest;
import io.fusionauth.domain.api.TenantResponse;
import io.fusionauth.domain.api.TwoFactorRequest;
import io.fusionauth.domain.api.UserActionReasonRequest;
import io.fusionauth.domain.api.UserActionReasonResponse;
import io.fusionauth.domain.api.UserActionRequest;
import io.fusionauth.domain.api.UserActionResponse;
import io.fusionauth.domain.api.UserCommentRequest;
import io.fusionauth.domain.api.UserCommentResponse;
import io.fusionauth.domain.api.UserDeleteRequest;
import io.fusionauth.domain.api.UserRequest;
import io.fusionauth.domain.api.UserResponse;
import io.fusionauth.domain.api.WebhookRequest;
import io.fusionauth.domain.api.WebhookResponse;
import io.fusionauth.domain.api.email.SendRequest;
import io.fusionauth.domain.api.email.SendResponse;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLoginRequest;
import io.fusionauth.domain.api.identityProvider.LookupResponse;
import io.fusionauth.domain.api.jwt.IssueResponse;
import io.fusionauth.domain.api.jwt.RefreshRequest;
import io.fusionauth.domain.api.jwt.RefreshResponse;
import io.fusionauth.domain.api.jwt.ValidateResponse;
import io.fusionauth.domain.api.report.DailyActiveUserReportResponse;
import io.fusionauth.domain.api.report.LoginReportResponse;
import io.fusionauth.domain.api.report.MonthlyActiveUserReportResponse;
import io.fusionauth.domain.api.report.RegistrationReportResponse;
import io.fusionauth.domain.api.report.TotalsReportResponse;
import io.fusionauth.domain.api.twoFactor.SecretResponse;
import io.fusionauth.domain.api.twoFactor.TwoFactorLoginRequest;
import io.fusionauth.domain.api.twoFactor.TwoFactorSendRequest;
import io.fusionauth.domain.api.user.ActionRequest;
import io.fusionauth.domain.api.user.ActionResponse;
import io.fusionauth.domain.api.user.ChangePasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordResponse;
import io.fusionauth.domain.api.user.ImportRequest;
import io.fusionauth.domain.api.user.RecentLoginResponse;
import io.fusionauth.domain.api.user.RegistrationRequest;
import io.fusionauth.domain.api.user.RegistrationResponse;
import io.fusionauth.domain.api.user.SearchRequest;
import io.fusionauth.domain.api.user.SearchResponse;
import io.fusionauth.domain.api.user.VerifyEmailResponse;
import io.fusionauth.domain.api.user.VerifyRegistrationResponse;

/**
 * Client that connects to a FusionAuth server and provides access to the full set of FusionAuth APIs.
 * <p>
 * When any method is called the return value is always a ClientResponse object. When an API call was successful, the
 * response will contain the response from the server. This might be empty or contain an success object or an error
 * object. If there was a validation error or any other type of error, this will return the Errors object in the
 * response. Additionally, if FusionAuth could not be contacted because it is down or experiencing a failure, the response
 * will contain an Exception, which could be an IOException.
 *
 * @author Brian Pontarelli
 */
@SuppressWarnings("unused")
public class FusionAuthClient {
  public static String TENANT_ID_HEADER = "X-FusionAuth-TenantId";

  public static final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                                                                    .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                                                                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                                                                    .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                                                                    .configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false)
                                                                    .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
                                                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                                                    .registerModule(new JacksonModule());

  private final String apiKey;

  private final String baseURL;

  private final String tenantId;

  public int connectTimeout = 2000;

  public int readTimeout = 2000;

  public FusionAuthClient(String apiKey, String baseURL) {
    this(apiKey, baseURL, null);
  }

  public FusionAuthClient(String apiKey, String baseURL, String tenantId) {
    this(apiKey, baseURL, 2000, 2000, tenantId);
  }

  public FusionAuthClient(String apiKey, String baseURL, int connectTimeout, int readTimeout) {
    this(apiKey, baseURL, connectTimeout, readTimeout, null);
  }

  public FusionAuthClient(String apiKey, String baseURL, int connectTimeout, int readTimeout, String tenantId) {
    this.apiKey = apiKey;
    this.baseURL = baseURL;
    this.connectTimeout = connectTimeout;
    this.readTimeout = readTimeout;
    this.tenantId = tenantId;
  }

  /**
  * Creates a new copy of this client with the provided tenant Id. When more than one tenant is configured in FusionAuth
  * use this method to set the tenant Id prior to making API calls.
  * <p>
  * When only one tenant is configured, or you have you have not configured tenants, setting the tenant is not necessary.
  *
  * @param tenantId The tenant Id
  * @return the new FusionAuthClient
  */
  public FusionAuthClient setTenantId(UUID tenantId) {
    if (tenantId == null) {
      return this;
    }

    return new FusionAuthClient(apiKey, baseURL, connectTimeout, readTimeout, tenantId.toString());
  }

[#list apis as api]
  /**
  [#list api.comments as comment]
   * ${comment}
  [/#list]
   *
  [#list api.params![] as param]
    [#if !param.constant??]
   * @param ${param.name} ${param.comments?join("\n   *     ")}
    [/#if]
  [/#list]
   * @return The ClientResponse object.
[#if api.deprecated??]
   * @deprecated ${api.deprecated}
[/#if]
   */
[#if api.deprecated??]
  @Deprecated
[/#if]
  public ClientResponse<${api.successResponse}, ${api.errorResponse}> ${api.methodName}(${global.methodParameters(api, "java")}) {
    return start(${api.successResponse}.${(api.successResponse == 'Void')?then('TYPE', 'class')}, ${api.errorResponse}.${(api.errorResponse == 'Void')?then('TYPE', 'class')}).uri("${api.uri}")
                        [#if api.authorization??]
                            .authorization(${api.authorization})
                        [/#if]
                        [#list api.params![] as param]
                          [#if param.type == "urlSegment"]
                            .urlSegment(${(param.constant?? && param.constant)?then(param.value, param.name)})
                          [#elseif param.type == "urlParameter"]
                            .urlParameter("${param.parameterName}", ${(param.constant?? && param.constant)?then(param.value, param.name)})
                          [#elseif param.type == "body"]
                            .bodyHandler(new JSONBodyHandler(${param.name}, objectMapper))
                          [/#if]
                        [/#list]
                            .${api.method}()
                            .go();
  }

[/#list]
  protected <T, U> RESTClient<T, U> start(Class<T> type, Class<U> errorType) {
    RESTClient<T, U> client = new RESTClient<>(type, errorType).authorization(apiKey)
                                               .successResponseHandler(type != Void.TYPE ? new JSONResponseHandler<>(type, objectMapper) : null)
                                               .errorResponseHandler(errorType != Void.TYPE ? new JSONResponseHandler<>(errorType, objectMapper) : null)
                                               .url(baseURL)
                                               .connectTimeout(connectTimeout)
                                               .readTimeout(readTimeout);

    if (tenantId != null) {
      client.header(TENANT_ID_HEADER, tenantId);
    }

    return client;
  }
}