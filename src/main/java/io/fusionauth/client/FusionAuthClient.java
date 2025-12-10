/*
 * Copyright (c) 2018-2025, FusionAuth, All Rights Reserved
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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inversoft.error.Errors;
import com.inversoft.json.JacksonModule;
import com.inversoft.rest.ClientResponse;
import com.inversoft.rest.FormDataBodyHandler;
import com.inversoft.rest.JSONBodyHandler;
import com.inversoft.rest.JSONResponseHandler;
import com.inversoft.rest.RESTClient;
import io.fusionauth.domain.LambdaType;
import io.fusionauth.domain.OpenIdConfiguration;
import io.fusionauth.domain.api.APIKeyRequest;
import io.fusionauth.client.json.FusionAuthJacksonModule;
import io.fusionauth.domain.api.APIKeyResponse;
import io.fusionauth.domain.api.ApplicationOAuthScopeRequest;
import io.fusionauth.domain.api.ApplicationOAuthScopeResponse;
import io.fusionauth.domain.api.ApplicationRequest;
import io.fusionauth.domain.api.ApplicationResponse;
import io.fusionauth.domain.api.ApplicationSearchRequest;
import io.fusionauth.domain.api.ApplicationSearchResponse;
import io.fusionauth.domain.api.AuditLogRequest;
import io.fusionauth.domain.api.AuditLogResponse;
import io.fusionauth.domain.api.AuditLogSearchRequest;
import io.fusionauth.domain.api.AuditLogSearchResponse;
import io.fusionauth.domain.api.ConnectorRequest;
import io.fusionauth.domain.api.ConnectorResponse;
import io.fusionauth.domain.api.ConsentRequest;
import io.fusionauth.domain.api.ConsentResponse;
import io.fusionauth.domain.api.ConsentSearchRequest;
import io.fusionauth.domain.api.ConsentSearchResponse;
import io.fusionauth.domain.api.EmailTemplateRequest;
import io.fusionauth.domain.api.EmailTemplateResponse;
import io.fusionauth.domain.api.EmailTemplateSearchRequest;
import io.fusionauth.domain.api.EmailTemplateSearchResponse;
import io.fusionauth.domain.api.EntityGrantRequest;
import io.fusionauth.domain.api.EntityGrantResponse;
import io.fusionauth.domain.api.EntityGrantSearchRequest;
import io.fusionauth.domain.api.EntityGrantSearchResponse;
import io.fusionauth.domain.api.EntityRequest;
import io.fusionauth.domain.api.EntityResponse;
import io.fusionauth.domain.api.EntitySearchRequest;
import io.fusionauth.domain.api.EntitySearchResponse;
import io.fusionauth.domain.api.EntityTypeRequest;
import io.fusionauth.domain.api.EntityTypeResponse;
import io.fusionauth.domain.api.EntityTypeSearchRequest;
import io.fusionauth.domain.api.EntityTypeSearchResponse;
import io.fusionauth.domain.api.EventLogResponse;
import io.fusionauth.domain.api.EventLogSearchRequest;
import io.fusionauth.domain.api.EventLogSearchResponse;
import io.fusionauth.domain.api.FamilyEmailRequest;
import io.fusionauth.domain.api.FamilyRequest;
import io.fusionauth.domain.api.FamilyResponse;
import io.fusionauth.domain.api.FormFieldRequest;
import io.fusionauth.domain.api.FormFieldResponse;
import io.fusionauth.domain.api.FormRequest;
import io.fusionauth.domain.api.FormResponse;
import io.fusionauth.domain.api.GroupMemberSearchRequest;
import io.fusionauth.domain.api.GroupMemberSearchResponse;
import io.fusionauth.domain.api.GroupSearchRequest;
import io.fusionauth.domain.api.GroupSearchResponse;
import io.fusionauth.domain.api.GroupRequest;
import io.fusionauth.domain.api.GroupResponse;
import io.fusionauth.domain.api.IPAccessControlListRequest;
import io.fusionauth.domain.api.IPAccessControlListResponse;
import io.fusionauth.domain.api.IPAccessControlListSearchRequest;
import io.fusionauth.domain.api.IPAccessControlListSearchResponse;
import io.fusionauth.domain.api.IdentityProviderSearchRequest;
import io.fusionauth.domain.api.IdentityProviderSearchResponse;
import io.fusionauth.domain.api.IdentityProviderRequest;
import io.fusionauth.domain.api.IdentityProviderResponse;
import io.fusionauth.domain.api.IntegrationRequest;
import io.fusionauth.domain.api.IntegrationResponse;
import io.fusionauth.domain.api.KeyRequest;
import io.fusionauth.domain.api.KeyResponse;
import io.fusionauth.domain.api.KeySearchRequest;
import io.fusionauth.domain.api.KeySearchResponse;
import io.fusionauth.domain.api.LambdaRequest;
import io.fusionauth.domain.api.LambdaResponse;
import io.fusionauth.domain.api.LambdaSearchRequest;
import io.fusionauth.domain.api.LambdaSearchResponse;
import io.fusionauth.domain.api.LoginRecordSearchRequest;
import io.fusionauth.domain.api.LoginRecordSearchResponse;
import io.fusionauth.domain.api.LoginPingRequest;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.LoginResponse;
import io.fusionauth.domain.api.LogoutRequest;
import io.fusionauth.domain.api.MemberDeleteRequest;
import io.fusionauth.domain.api.MemberRequest;
import io.fusionauth.domain.api.MemberResponse;
import io.fusionauth.domain.api.MessageTemplateRequest;
import io.fusionauth.domain.api.MessageTemplateResponse;
import io.fusionauth.domain.api.MessengerRequest;
import io.fusionauth.domain.api.MessengerResponse;
import io.fusionauth.domain.api.OAuthConfigurationResponse;
import io.fusionauth.domain.api.PasswordValidationRulesResponse;
import io.fusionauth.domain.api.PendingResponse;
import io.fusionauth.domain.api.PreviewMessageTemplateRequest;
import io.fusionauth.domain.api.PreviewMessageTemplateResponse;
import io.fusionauth.domain.api.PreviewRequest;
import io.fusionauth.domain.api.PreviewResponse;
import io.fusionauth.domain.api.PublicKeyResponse;
import io.fusionauth.domain.api.ReactorMetricsResponse;
import io.fusionauth.domain.api.ReactorRequest;
import io.fusionauth.domain.api.ReactorResponse;
import io.fusionauth.domain.api.ReindexRequest;
import io.fusionauth.domain.api.StatusResponse;
import io.fusionauth.domain.api.SystemConfigurationRequest;
import io.fusionauth.domain.api.SystemConfigurationResponse;
import io.fusionauth.domain.api.TenantDeleteRequest;
import io.fusionauth.domain.api.TenantRequest;
import io.fusionauth.domain.api.TenantResponse;
import io.fusionauth.domain.api.TenantSearchRequest;
import io.fusionauth.domain.api.TenantSearchResponse;
import io.fusionauth.domain.api.ThemeRequest;
import io.fusionauth.domain.api.ThemeResponse;
import io.fusionauth.domain.api.ThemeSearchRequest;
import io.fusionauth.domain.api.ThemeSearchResponse;
import io.fusionauth.domain.api.TwoFactorDisableRequest;
import io.fusionauth.domain.api.TwoFactorRecoveryCodeResponse;
import io.fusionauth.domain.api.TwoFactorRequest;
import io.fusionauth.domain.api.TwoFactorResponse;
import io.fusionauth.domain.api.UserActionReasonRequest;
import io.fusionauth.domain.api.UserActionReasonResponse;
import io.fusionauth.domain.api.UserActionRequest;
import io.fusionauth.domain.api.UserActionResponse;
import io.fusionauth.domain.api.UserCommentRequest;
import io.fusionauth.domain.api.UserCommentResponse;
import io.fusionauth.domain.api.UserCommentSearchRequest;
import io.fusionauth.domain.api.UserCommentSearchResponse;
import io.fusionauth.domain.api.UserConsentRequest;
import io.fusionauth.domain.api.UserConsentResponse;
import io.fusionauth.domain.api.UserDeleteRequest;
import io.fusionauth.domain.api.UserDeleteResponse;
import io.fusionauth.domain.api.UserDeleteSingleRequest;
import io.fusionauth.domain.api.UserRequest;
import io.fusionauth.domain.api.UserResponse;
import io.fusionauth.domain.api.VersionResponse;
import io.fusionauth.domain.api.WebAuthnAssertResponse;
import io.fusionauth.domain.api.WebAuthnCredentialImportRequest;
import io.fusionauth.domain.api.WebAuthnCredentialResponse;
import io.fusionauth.domain.api.WebAuthnLoginRequest;
import io.fusionauth.domain.api.WebAuthnRegisterCompleteRequest;
import io.fusionauth.domain.api.WebAuthnRegisterCompleteResponse;
import io.fusionauth.domain.api.WebAuthnRegisterStartRequest;
import io.fusionauth.domain.api.WebAuthnRegisterStartResponse;
import io.fusionauth.domain.api.WebAuthnStartRequest;
import io.fusionauth.domain.api.WebAuthnStartResponse;
import io.fusionauth.domain.api.WebhookAttemptLogResponse;
import io.fusionauth.domain.api.WebhookEventLogResponse;
import io.fusionauth.domain.api.WebhookEventLogSearchRequest;
import io.fusionauth.domain.api.WebhookEventLogSearchResponse;
import io.fusionauth.domain.api.WebhookRequest;
import io.fusionauth.domain.api.WebhookResponse;
import io.fusionauth.domain.api.WebhookSearchRequest;
import io.fusionauth.domain.api.WebhookSearchResponse;
import io.fusionauth.domain.api.email.SendRequest;
import io.fusionauth.domain.api.email.SendResponse;
import io.fusionauth.domain.api.identity.verify.VerifyCompleteRequest;
import io.fusionauth.domain.api.identity.verify.VerifyCompleteResponse;
import io.fusionauth.domain.api.identity.verify.VerifyRequest;
import io.fusionauth.domain.api.identity.verify.VerifyStartRequest;
import io.fusionauth.domain.api.identity.verify.VerifyStartResponse;
import io.fusionauth.domain.api.identity.verify.VerifySendRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLinkRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLinkResponse;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLoginRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderPendingLinkResponse;
import io.fusionauth.domain.api.identityProvider.IdentityProviderStartLoginRequest;
import io.fusionauth.domain.api.identityProvider.IdentityProviderStartLoginResponse;
import io.fusionauth.domain.api.identityProvider.LookupResponse;
import io.fusionauth.domain.api.jwt.IssueResponse;
import io.fusionauth.domain.api.jwt.JWTRefreshResponse;
import io.fusionauth.domain.api.jwt.JWTVendRequest;
import io.fusionauth.domain.api.jwt.JWTVendResponse;
import io.fusionauth.domain.api.jwt.RefreshRequest;
import io.fusionauth.domain.api.jwt.RefreshTokenResponse;
import io.fusionauth.domain.api.jwt.RefreshTokenRevokeRequest;
import io.fusionauth.domain.api.jwt.ValidateResponse;
import io.fusionauth.domain.api.passwordless.PasswordlessLoginRequest;
import io.fusionauth.domain.api.passwordless.PasswordlessSendRequest;
import io.fusionauth.domain.api.passwordless.PasswordlessStartRequest;
import io.fusionauth.domain.api.passwordless.PasswordlessStartResponse;
import io.fusionauth.domain.api.report.DailyActiveUserReportResponse;
import io.fusionauth.domain.api.report.LoginReportResponse;
import io.fusionauth.domain.api.report.MonthlyActiveUserReportResponse;
import io.fusionauth.domain.api.report.RegistrationReportResponse;
import io.fusionauth.domain.api.report.TotalsReportResponse;
import io.fusionauth.domain.api.twoFactor.SecretResponse;
import io.fusionauth.domain.api.twoFactor.TwoFactorLoginRequest;
import io.fusionauth.domain.api.twoFactor.TwoFactorSendRequest;
import io.fusionauth.domain.api.twoFactor.TwoFactorStartRequest;
import io.fusionauth.domain.api.twoFactor.TwoFactorStartResponse;
import io.fusionauth.domain.api.twoFactor.TwoFactorStatusResponse;
import io.fusionauth.domain.api.twoFactor.TwoFactorStatusRequest;
import io.fusionauth.domain.api.user.ActionRequest;
import io.fusionauth.domain.api.user.ActionResponse;
import io.fusionauth.domain.api.user.ChangePasswordRequest;
import io.fusionauth.domain.api.user.ChangePasswordResponse;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordResponse;
import io.fusionauth.domain.api.user.ImportRequest;
import io.fusionauth.domain.api.user.RecentLoginResponse;
import io.fusionauth.domain.api.user.RefreshTokenImportRequest;
import io.fusionauth.domain.api.user.RegistrationDeleteRequest;
import io.fusionauth.domain.api.user.RegistrationRequest;
import io.fusionauth.domain.api.user.RegistrationResponse;
import io.fusionauth.domain.api.user.SearchRequest;
import io.fusionauth.domain.api.user.SearchResponse;
import io.fusionauth.domain.api.user.VerifyEmailRequest;
import io.fusionauth.domain.api.user.VerifyEmailResponse;
import io.fusionauth.domain.api.user.VerifyRegistrationRequest;
import io.fusionauth.domain.api.user.VerifyRegistrationResponse;
import io.fusionauth.domain.oauth2.AccessToken;
import io.fusionauth.domain.oauth2.DeviceApprovalResponse;
import io.fusionauth.domain.oauth2.IntrospectResponse;
import io.fusionauth.domain.oauth2.JWKSResponse;
import io.fusionauth.domain.oauth2.OAuthError;
import io.fusionauth.domain.oauth2.UserinfoResponse;
import io.fusionauth.domain.provider.IdentityProviderType;

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
                                                                    .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
                                                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                                                    .configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
                                                                    .configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true)
                                                                    .registerModule(new JacksonModule())
                                                                    .registerModule(new FusionAuthJacksonModule());

  private final String apiKey;

  private final String baseURL;

  private final ObjectMapper customMapper;

  private final String tenantId;

  public int connectTimeout;

  public int readTimeout;

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
    this(apiKey, baseURL, connectTimeout, readTimeout, tenantId, null);
  }

  public FusionAuthClient(String apiKey, String baseURL, int connectTimeout, int readTimeout, String tenantId, ObjectMapper objectMapper) {
    this.apiKey = apiKey;
    this.baseURL = baseURL;
    this.connectTimeout = connectTimeout;
    this.readTimeout = readTimeout;
    this.tenantId = tenantId;
    this.customMapper = objectMapper;
  }

  /**
  * Creates a new copy of this client with the provided tenant Id. When more than one tenant is configured in FusionAuth
  * use this method to set the tenant Id prior to making API calls.
  * <p>
  * When only one tenant is configured, or you have not configured tenants, setting the tenant is not necessary.
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

  /**
  * Creates a new copy of this client with the object mapper. This will take the place of the default FusionAuth object mapper when serializing
  * and deserializing objects to and from JSON for the request and response bodies.
  *
  * @param objectMapper The object mapper
  * @return the new FusionAuthClient
  */
  public FusionAuthClient setObjectMapper(ObjectMapper objectMapper) {
    return new FusionAuthClient(apiKey, baseURL, connectTimeout, readTimeout, tenantId, objectMapper);
  }

  /**
   * Takes an action on a user. The user being actioned is called the "actionee" and the user taking the action is called the
   * "actioner". Both user ids are required in the request object.
   *
   * @param request The action request that includes all the information about the action being taken including
   *     the Id of the action, any options and the duration (if applicable).
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> actionUser(ActionRequest request) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Activates the FusionAuth Reactor using a license Id and optionally a license text (for air-gapped deployments)
   *
   * @param request An optional request that contains the license text to activate Reactor (useful for air-gap deployments of FusionAuth).
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> activateReactor(ReactorRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/reactor")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Adds a user to an existing family. The family Id must be specified.
   *
   * @param familyId The Id of the family.
   * @param request The request object that contains all the information used to determine which user to add to the family.
   * @return The ClientResponse object.
   */
  public ClientResponse<FamilyResponse, Errors> addUserToFamily(UUID familyId, FamilyRequest request) {
    return start(FamilyResponse.class, Errors.class)
        .uri("/api/user/family")
        .urlSegment(familyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Approve a device grant.
   *
   * @param client_id (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate.
   * @param client_secret (Optional) The client secret. This value will be required if client authentication is enabled.
   * @param token The access token used to identify the user.
   * @param user_code The end-user verification code.
   * @return The ClientResponse object.
   */
  public ClientResponse<DeviceApprovalResponse, Errors> approveDevice(String client_id, String client_secret, String token, String user_code) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("token", Arrays.asList(token));
    parameters.put("user_code", Arrays.asList(user_code));
    return start(DeviceApprovalResponse.class, Errors.class)
        .uri("/oauth2/device/approve")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Cancels the user action.
   *
   * @param actionId The action Id of the action to cancel.
   * @param request The action request that contains the information about the cancellation.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> cancelAction(UUID actionId, ActionRequest request) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlSegment(actionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Changes a user's password using the change password Id. This usually occurs after an email has been sent to the user
   * and they clicked on a link to reset their password.
   * <p>
   * As of version 1.32.2, prefer sending the changePasswordId in the request body. To do this, omit the first parameter, and set
   * the value in the request body.
   *
   * @param changePasswordId The change password Id used to find the user. This value is generated by FusionAuth once the change password workflow has been initiated.
   * @param request The change password request that contains all the information used to change the password.
   * @return The ClientResponse object.
   */
  public ClientResponse<ChangePasswordResponse, Errors> changePassword(String changePasswordId, ChangePasswordRequest request) {
    return startAnonymous(ChangePasswordResponse.class, Errors.class)
        .uri("/api/user/change-password")
        .urlSegment(changePasswordId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Changes a user's password using their access token (JWT) instead of the changePasswordId
   * A common use case for this method will be if you want to allow the user to change their own password.
   * <p>
   * Remember to send refreshToken in the request body if you want to get a new refresh token when login using the returned oneTimePassword.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @param request The change password request that contains all the information used to change the password.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to changePasswordUsingJWT, use that method instead.
   */
  @Deprecated
  public ClientResponse<ChangePasswordResponse, Errors> changePasswordByJWT(String encodedJWT, ChangePasswordRequest request) {
    return startAnonymous(ChangePasswordResponse.class, Errors.class)
        .uri("/api/user/change-password")
        .authorization("Bearer " + encodedJWT)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Changes a user's password using their identity (loginId and password). Using a loginId instead of the changePasswordId
   * bypasses the email verification and allows a password to be changed directly without first calling the #forgotPassword
   * method.
   *
   * @param request The change password request that contains all the information used to change the password.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> changePasswordByIdentity(ChangePasswordRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Changes a user's password using their access token (JWT) instead of the changePasswordId
   * A common use case for this method will be if you want to allow the user to change their own password.
   * <p>
   * Remember to send refreshToken in the request body if you want to get a new refresh token when login using the returned oneTimePassword.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @param request The change password request that contains all the information used to change the password.
   * @return The ClientResponse object.
   */
  public ClientResponse<ChangePasswordResponse, Errors> changePasswordUsingJWT(String encodedJWT, ChangePasswordRequest request) {
    return startAnonymous(ChangePasswordResponse.class, Errors.class)
        .uri("/api/user/change-password")
        .authorization("Bearer " + encodedJWT)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Token Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Token by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param changePasswordId The change password Id used to find the user. This value is generated by FusionAuth once the change password workflow has been initiated.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingId(String changePasswordId) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .urlSegment(changePasswordId)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Token Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Token by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param changePasswordId The change password Id used to find the user. This value is generated by FusionAuth once the change password workflow has been initiated.
   * @param ipAddress (Optional) IP address of the user changing their password. This is used for MFA risk assessment.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingIdAndIPAddress(String changePasswordId, String ipAddress) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .urlSegment(changePasswordId)
        .urlParameter("ipAddress", ipAddress)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Token Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Token by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingJWT(String encodedJWT) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .authorization("Bearer " + encodedJWT)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Token Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Token by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @param ipAddress (Optional) IP address of the user changing their password. This is used for MFA risk assessment.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingJWTAndIPAddress(String encodedJWT, String ipAddress) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .authorization("Bearer " + encodedJWT)
        .urlParameter("ipAddress", ipAddress)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Request Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Request Id by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param loginId The loginId (email or username) of the User that you intend to change the password for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingLoginId(String loginId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .urlParameter("loginId", loginId)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Request Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Request Id by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param loginId The loginId (email or username) of the User that you intend to change the password for.
   * @param ipAddress (Optional) IP address of the user changing their password. This is used for MFA risk assessment.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingLoginIdAndIPAddress(String loginId, String ipAddress) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .urlParameter("loginId", loginId)
        .urlParameter("ipAddress", ipAddress)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Request Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Request Id by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param loginId The loginId of the User that you intend to change the password for.
   * @param loginIdTypes The identity types that FusionAuth will compare the loginId to.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingLoginIdAndLoginIdTypes(String loginId, List<String> loginIdTypes) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .urlParameter("loginId", loginId)
        .urlParameter("loginIdTypes", loginIdTypes)
        .get()
        .go();
  }

  /**
   * Check to see if the user must obtain a Trust Request Id in order to complete a change password request.
   * When a user has enabled Two-Factor authentication, before you are allowed to use the Change Password API to change
   * your password, you must obtain a Trust Request Id by completing a Two-Factor Step-Up authentication.
   * <p>
   * An HTTP status code of 400 with a general error code of [TrustTokenRequired] indicates that a Trust Token is required to make a POST request to this API.
   *
   * @param loginId The loginId of the User that you intend to change the password for.
   * @param loginIdTypes The identity types that FusionAuth will compare the loginId to.
   * @param ipAddress (Optional) IP address of the user changing their password. This is used for MFA risk assessment.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> checkChangePasswordUsingLoginIdAndLoginIdTypesAndIPAddress(String loginId, List<String> loginIdTypes, String ipAddress) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/change-password")
        .urlParameter("loginId", loginId)
        .urlParameter("loginIdTypes", loginIdTypes)
        .urlParameter("ipAddress", ipAddress)
        .get()
        .go();
  }

  /**
   * Make a Client Credentials grant request to obtain an access token.
   *
   * @param client_id (Optional) The client identifier. The client Id is the Id of the FusionAuth Entity in which you are attempting to authenticate.
   *     This parameter is optional when Basic Authorization is used to authenticate this request.
   * @param client_secret (Optional) The client secret used to authenticate this request.
   *     This parameter is optional when Basic Authorization is used to authenticate this request.
   * @param scope (Optional) This parameter is used to indicate which target entity you are requesting access. To request access to an entity, use the format target-entity:&lt;target-entity-id&gt;:&lt;roles&gt;. Roles are an optional comma separated list.
   * @return The ClientResponse object.
   */
  public ClientResponse<AccessToken, OAuthError> clientCredentialsGrant(String client_id, String client_secret, String scope) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("grant_type", Arrays.asList("client_credentials"));
    parameters.put("scope", Arrays.asList(scope));
    return startAnonymous(AccessToken.class, OAuthError.class)
        .uri("/oauth2/token")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Adds a comment to the user's account.
   *
   * @param request The request object that contains all the information used to create the user comment.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserCommentResponse, Errors> commentOnUser(UserCommentRequest request) {
    return start(UserCommentResponse.class, Errors.class)
        .uri("/api/user/comment")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Completes verification of an identity using verification codes from the Verify Start API.
   *
   * @param request The identity verify complete request that contains all the information used to verify the identity.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyCompleteResponse, Errors> completeVerifyIdentity(VerifyCompleteRequest request) {
    return start(VerifyCompleteResponse.class, Errors.class)
        .uri("/api/identity/verify/complete")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Complete a WebAuthn authentication ceremony by validating the signature against the previously generated challenge without logging the user in
   *
   * @param request An object containing data necessary for completing the authentication ceremony
   * @return The ClientResponse object.
   */
  public ClientResponse<WebAuthnAssertResponse, Errors> completeWebAuthnAssertion(WebAuthnLoginRequest request) {
    return startAnonymous(WebAuthnAssertResponse.class, Errors.class)
        .uri("/api/webauthn/assert")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Complete a WebAuthn authentication ceremony by validating the signature against the previously generated challenge and then login the user in
   *
   * @param request An object containing data necessary for completing the authentication ceremony
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> completeWebAuthnLogin(WebAuthnLoginRequest request) {
    return startAnonymous(LoginResponse.class, Errors.class)
        .uri("/api/webauthn/login")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Complete a WebAuthn registration ceremony by validating the client request and saving the new credential
   *
   * @param request An object containing data necessary for completing the registration ceremony
   * @return The ClientResponse object.
   */
  public ClientResponse<WebAuthnRegisterCompleteResponse, Errors> completeWebAuthnRegistration(WebAuthnRegisterCompleteRequest request) {
    return start(WebAuthnRegisterCompleteResponse.class, Errors.class)
        .uri("/api/webauthn/register/complete")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an API key. You can optionally specify a unique Id for the key, if not provided one will be generated.
   * an API key can only be created with equal or lesser authority. An API key cannot create another API key unless it is granted 
   * to that API key.
   * <p>
   * If an API key is locked to a tenant, it can only create API Keys for that same tenant.
   *
   * @param keyId (Optional) The unique Id of the API key. If not provided a secure random Id will be generated.
   * @param request The request object that contains all the information needed to create the APIKey.
   * @return The ClientResponse object.
   */
  public ClientResponse<APIKeyResponse, Errors> createAPIKey(UUID keyId, APIKeyRequest request) {
    return start(APIKeyResponse.class, Errors.class)
        .uri("/api/api-key")
        .urlSegment(keyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an application. You can optionally specify an Id for the application, if not provided one will be generated.
   *
   * @param applicationId (Optional) The Id to use for the application. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the application.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> createApplication(UUID applicationId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a new role for an application. You must specify the Id of the application you are creating the role for.
   * You can optionally specify an Id for the role inside the ApplicationRole object itself, if not provided one will be generated.
   *
   * @param applicationId The Id of the application to create the role on.
   * @param roleId (Optional) The Id of the role. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the application role.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> createApplicationRole(UUID applicationId, UUID roleId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("role")
        .urlSegment(roleId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an audit log with the message and user name (usually an email). Audit logs should be written anytime you
   * make changes to the FusionAuth database. When using the FusionAuth App web interface, any changes are automatically
   * written to the audit log. However, if you are accessing the API, you must write the audit logs yourself.
   *
   * @param request The request object that contains all the information used to create the audit log entry.
   * @return The ClientResponse object.
   */
  public ClientResponse<AuditLogResponse, Errors> createAuditLog(AuditLogRequest request) {
    return start(AuditLogResponse.class, Errors.class)
        .uri("/api/system/audit-log")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a connector.  You can optionally specify an Id for the connector, if not provided one will be generated.
   *
   * @param connectorId (Optional) The Id for the connector. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the connector.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConnectorResponse, Errors> createConnector(UUID connectorId, ConnectorRequest request) {
    return start(ConnectorResponse.class, Errors.class)
        .uri("/api/connector")
        .urlSegment(connectorId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a user consent type. You can optionally specify an Id for the consent type, if not provided one will be generated.
   *
   * @param consentId (Optional) The Id for the consent. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the consent.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConsentResponse, Errors> createConsent(UUID consentId, ConsentRequest request) {
    return start(ConsentResponse.class, Errors.class)
        .uri("/api/consent")
        .urlSegment(consentId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an email template. You can optionally specify an Id for the template, if not provided one will be generated.
   *
   * @param emailTemplateId (Optional) The Id for the template. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the email template.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Errors> createEmailTemplate(UUID emailTemplateId, EmailTemplateRequest request) {
    return start(EmailTemplateResponse.class, Errors.class)
        .uri("/api/email/template")
        .urlSegment(emailTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an Entity. You can optionally specify an Id for the Entity. If not provided one will be generated.
   *
   * @param entityId (Optional) The Id for the Entity. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the Entity.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityResponse, Errors> createEntity(UUID entityId, EntityRequest request) {
    return start(EntityResponse.class, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a Entity Type. You can optionally specify an Id for the Entity Type, if not provided one will be generated.
   *
   * @param entityTypeId (Optional) The Id for the Entity Type. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the Entity Type.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> createEntityType(UUID entityTypeId, EntityTypeRequest request) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a new permission for an entity type. You must specify the Id of the entity type you are creating the permission for.
   * You can optionally specify an Id for the permission inside the EntityTypePermission object itself, if not provided one will be generated.
   *
   * @param entityTypeId The Id of the entity type to create the permission on.
   * @param permissionId (Optional) The Id of the permission. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the permission.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> createEntityTypePermission(UUID entityTypeId, UUID permissionId, EntityTypeRequest request) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .urlSegment("permission")
        .urlSegment(permissionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a family with the user Id in the request as the owner and sole member of the family. You can optionally specify an Id for the
   * family, if not provided one will be generated.
   *
   * @param familyId (Optional) The Id for the family. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the family.
   * @return The ClientResponse object.
   */
  public ClientResponse<FamilyResponse, Errors> createFamily(UUID familyId, FamilyRequest request) {
    return start(FamilyResponse.class, Errors.class)
        .uri("/api/user/family")
        .urlSegment(familyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a form.  You can optionally specify an Id for the form, if not provided one will be generated.
   *
   * @param formId (Optional) The Id for the form. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the form.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormResponse, Errors> createForm(UUID formId, FormRequest request) {
    return start(FormResponse.class, Errors.class)
        .uri("/api/form")
        .urlSegment(formId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a form field.  You can optionally specify an Id for the form, if not provided one will be generated.
   *
   * @param fieldId (Optional) The Id for the form field. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the form field.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormFieldResponse, Errors> createFormField(UUID fieldId, FormFieldRequest request) {
    return start(FormFieldResponse.class, Errors.class)
        .uri("/api/form/field")
        .urlSegment(fieldId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a group. You can optionally specify an Id for the group, if not provided one will be generated.
   *
   * @param groupId (Optional) The Id for the group. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the group.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> createGroup(UUID groupId, GroupRequest request) {
    return start(GroupResponse.class, Errors.class)
        .uri("/api/group")
        .urlSegment(groupId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a member in a group.
   *
   * @param request The request object that contains all the information used to create the group member(s).
   * @return The ClientResponse object.
   */
  public ClientResponse<MemberResponse, Errors> createGroupMembers(MemberRequest request) {
    return start(MemberResponse.class, Errors.class)
        .uri("/api/group/member")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an IP Access Control List. You can optionally specify an Id on this create request, if one is not provided one will be generated.
   *
   * @param accessControlListId (Optional) The Id for the IP Access Control List. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the IP Access Control List.
   * @return The ClientResponse object.
   */
  public ClientResponse<IPAccessControlListResponse, Errors> createIPAccessControlList(UUID accessControlListId, IPAccessControlListRequest request) {
    return start(IPAccessControlListResponse.class, Errors.class)
        .uri("/api/ip-acl")
        .urlSegment(accessControlListId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an identity provider. You can optionally specify an Id for the identity provider, if not provided one will be generated.
   *
   * @param identityProviderId (Optional) The Id of the identity provider. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the identity provider.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> createIdentityProvider(UUID identityProviderId, IdentityProviderRequest request) {
    return start(IdentityProviderResponse.class, Errors.class)
        .uri("/api/identity-provider")
        .urlSegment(identityProviderId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a Lambda. You can optionally specify an Id for the lambda, if not provided one will be generated.
   *
   * @param lambdaId (Optional) The Id for the lambda. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the lambda.
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaResponse, Errors> createLambda(UUID lambdaId, LambdaRequest request) {
    return start(LambdaResponse.class, Errors.class)
        .uri("/api/lambda")
        .urlSegment(lambdaId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates an message template. You can optionally specify an Id for the template, if not provided one will be generated.
   *
   * @param messageTemplateId (Optional) The Id for the template. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the message template.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessageTemplateResponse, Errors> createMessageTemplate(UUID messageTemplateId, MessageTemplateRequest request) {
    return start(MessageTemplateResponse.class, Errors.class)
        .uri("/api/message/template")
        .urlSegment(messageTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a messenger.  You can optionally specify an Id for the messenger, if not provided one will be generated.
   *
   * @param messengerId (Optional) The Id for the messenger. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the messenger.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessengerResponse, Errors> createMessenger(UUID messengerId, MessengerRequest request) {
    return start(MessengerResponse.class, Errors.class)
        .uri("/api/messenger")
        .urlSegment(messengerId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a new custom OAuth scope for an application. You must specify the Id of the application you are creating the scope for.
   * You can optionally specify an Id for the OAuth scope on the URL, if not provided one will be generated.
   *
   * @param applicationId The Id of the application to create the OAuth scope on.
   * @param scopeId (Optional) The Id of the OAuth scope. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the OAuth OAuth scope.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationOAuthScopeResponse, Errors> createOAuthScope(UUID applicationId, UUID scopeId, ApplicationOAuthScopeRequest request) {
    return start(ApplicationOAuthScopeResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("scope")
        .urlSegment(scopeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a tenant. You can optionally specify an Id for the tenant, if not provided one will be generated.
   *
   * @param tenantId (Optional) The Id for the tenant. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the tenant.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> createTenant(UUID tenantId, TenantRequest request) {
    return start(TenantResponse.class, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a Theme. You can optionally specify an Id for the theme, if not provided one will be generated.
   *
   * @param themeId (Optional) The Id for the theme. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the theme.
   * @return The ClientResponse object.
   */
  public ClientResponse<ThemeResponse, Errors> createTheme(UUID themeId, ThemeRequest request) {
    return start(ThemeResponse.class, Errors.class)
        .uri("/api/theme")
        .urlSegment(themeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a user. You can optionally specify an Id for the user, if not provided one will be generated.
   *
   * @param userId (Optional) The Id for the user. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> createUser(UUID userId, UserRequest request) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a user action. This action cannot be taken on a user until this call successfully returns. Anytime after
   * that the user action can be applied to any user.
   *
   * @param userActionId (Optional) The Id for the user action. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the user action.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> createUserAction(UUID userActionId, UserActionRequest request) {
    return start(UserActionResponse.class, Errors.class)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a user reason. This user action reason cannot be used when actioning a user until this call completes
   * successfully. Anytime after that the user action reason can be used.
   *
   * @param userActionReasonId (Optional) The Id for the user action reason. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the user action reason.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Errors> createUserActionReason(UUID userActionReasonId, UserActionReasonRequest request) {
    return start(UserActionReasonResponse.class, Errors.class)
        .uri("/api/user-action-reason")
        .urlSegment(userActionReasonId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a single User consent.
   *
   * @param userConsentId (Optional) The Id for the User consent. If not provided a secure random UUID will be generated.
   * @param request The request that contains the user consent information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserConsentResponse, Errors> createUserConsent(UUID userConsentId, UserConsentRequest request) {
    return start(UserConsentResponse.class, Errors.class)
        .uri("/api/user/consent")
        .urlSegment(userConsentId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Link an external user from a 3rd party identity provider to a FusionAuth user.
   *
   * @param request The request object that contains all the information used to link the FusionAuth user.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderLinkResponse, Errors> createUserLink(IdentityProviderLinkRequest request) {
    return start(IdentityProviderLinkResponse.class, Errors.class)
        .uri("/api/identity-provider/link")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Creates a webhook. You can optionally specify an Id for the webhook, if not provided one will be generated.
   *
   * @param webhookId (Optional) The Id for the webhook. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the webhook.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Errors> createWebhook(UUID webhookId, WebhookRequest request) {
    return start(WebhookResponse.class, Errors.class)
        .uri("/api/webhook")
        .urlSegment(webhookId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Deactivates the application with the given Id.
   *
   * @param applicationId The Id of the application to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateApplication(UUID applicationId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .delete()
        .go();
  }

  /**
   * Deactivates the FusionAuth Reactor.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> deactivateReactor() {
    return start(Void.TYPE, Void.TYPE)
        .uri("/api/reactor")
        .delete()
        .go();
  }

  /**
   * Deactivates the user with the given Id.
   *
   * @param userId The Id of the user to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateUser(UUID userId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .delete()
        .go();
  }

  /**
   * Deactivates the user action with the given Id.
   *
   * @param userActionId The Id of the user action to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateUserAction(UUID userActionId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .delete()
        .go();
  }

  /**
   * Deactivates the users with the given Ids.
   *
   * @param userIds The ids of the users to deactivate.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to deactivateUsersByIds, use that method instead.
   */
  @Deprecated
  public ClientResponse<UserDeleteResponse, Errors> deactivateUsers(Collection<UUID> userIds) {
    return start(UserDeleteResponse.class, Errors.class)
        .uri("/api/user/bulk")
        .urlParameter("userId", userIds)
        .urlParameter("dryRun", false)
        .urlParameter("hardDelete", false)
        .delete()
        .go();
  }

  /**
   * Deactivates the users with the given Ids.
   *
   * @param userIds The ids of the users to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserDeleteResponse, Errors> deactivateUsersByIds(Collection<UUID> userIds) {
    return start(UserDeleteResponse.class, Errors.class)
        .uri("/api/user/bulk")
        .urlParameter("userId", userIds)
        .urlParameter("dryRun", false)
        .urlParameter("hardDelete", false)
        .delete()
        .go();
  }

  /**
   * Deletes the API key for the given Id.
   *
   * @param keyId The Id of the authentication API key to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteAPIKey(UUID keyId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/api-key")
        .urlSegment(keyId)
        .delete()
        .go();
  }

  /**
   * Hard deletes an application. This is a dangerous operation and should not be used in most circumstances. This will
   * delete the application, any registrations for that application, metrics and reports for the application, all the
   * roles for the application, and any other data associated with the application. This operation could take a very
   * long time, depending on the amount of data in your database.
   *
   * @param applicationId The Id of the application to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteApplication(UUID applicationId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlParameter("hardDelete", true)
        .delete()
        .go();
  }

  /**
   * Hard deletes an application role. This is a dangerous operation and should not be used in most circumstances. This
   * permanently removes the given role from all users that had it.
   *
   * @param applicationId The Id of the application that the role belongs to.
   * @param roleId The Id of the role to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteApplicationRole(UUID applicationId, UUID roleId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("role")
        .urlSegment(roleId)
        .delete()
        .go();
  }

  /**
   * Deletes the connector for the given Id.
   *
   * @param connectorId The Id of the connector to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteConnector(UUID connectorId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/connector")
        .urlSegment(connectorId)
        .delete()
        .go();
  }

  /**
   * Deletes the consent for the given Id.
   *
   * @param consentId The Id of the consent to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteConsent(UUID consentId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/consent")
        .urlSegment(consentId)
        .delete()
        .go();
  }

  /**
   * Deletes the email template for the given Id.
   *
   * @param emailTemplateId The Id of the email template to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteEmailTemplate(UUID emailTemplateId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/email/template")
        .urlSegment(emailTemplateId)
        .delete()
        .go();
  }

  /**
   * Deletes the Entity for the given Id.
   *
   * @param entityId The Id of the Entity to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteEntity(UUID entityId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .delete()
        .go();
  }

  /**
   * Deletes an Entity Grant for the given User or Entity.
   *
   * @param entityId The Id of the Entity that the Entity Grant is being deleted for.
   * @param recipientEntityId (Optional) The Id of the Entity that the Entity Grant is for.
   * @param userId (Optional) The Id of the User that the Entity Grant is for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteEntityGrant(UUID entityId, UUID recipientEntityId, UUID userId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .urlSegment("grant")
        .urlParameter("recipientEntityId", recipientEntityId)
        .urlParameter("userId", userId)
        .delete()
        .go();
  }

  /**
   * Deletes the Entity Type for the given Id.
   *
   * @param entityTypeId The Id of the Entity Type to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteEntityType(UUID entityTypeId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .delete()
        .go();
  }

  /**
   * Hard deletes a permission. This is a dangerous operation and should not be used in most circumstances. This
   * permanently removes the given permission from all grants that had it.
   *
   * @param entityTypeId The Id of the entityType the the permission belongs to.
   * @param permissionId The Id of the permission to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteEntityTypePermission(UUID entityTypeId, UUID permissionId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .urlSegment("permission")
        .urlSegment(permissionId)
        .delete()
        .go();
  }

  /**
   * Deletes the form for the given Id.
   *
   * @param formId The Id of the form to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteForm(UUID formId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/form")
        .urlSegment(formId)
        .delete()
        .go();
  }

  /**
   * Deletes the form field for the given Id.
   *
   * @param fieldId The Id of the form field to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteFormField(UUID fieldId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/form/field")
        .urlSegment(fieldId)
        .delete()
        .go();
  }

  /**
   * Deletes the group for the given Id.
   *
   * @param groupId The Id of the group to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteGroup(UUID groupId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/group")
        .urlSegment(groupId)
        .delete()
        .go();
  }

  /**
   * Removes users as members of a group.
   *
   * @param request The member request that contains all the information used to remove members to the group.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteGroupMembers(MemberDeleteRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/group/member")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Deletes the IP Access Control List for the given Id.
   *
   * @param ipAccessControlListId The Id of the IP Access Control List to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteIPAccessControlList(UUID ipAccessControlListId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/ip-acl")
        .urlSegment(ipAccessControlListId)
        .delete()
        .go();
  }

  /**
   * Deletes the identity provider for the given Id.
   *
   * @param identityProviderId The Id of the identity provider to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteIdentityProvider(UUID identityProviderId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/identity-provider")
        .urlSegment(identityProviderId)
        .delete()
        .go();
  }

  /**
   * Deletes the key for the given Id.
   *
   * @param keyId The Id of the key to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteKey(UUID keyId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/key")
        .urlSegment(keyId)
        .delete()
        .go();
  }

  /**
   * Deletes the lambda for the given Id.
   *
   * @param lambdaId The Id of the lambda to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteLambda(UUID lambdaId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/lambda")
        .urlSegment(lambdaId)
        .delete()
        .go();
  }

  /**
   * Deletes the message template for the given Id.
   *
   * @param messageTemplateId The Id of the message template to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteMessageTemplate(UUID messageTemplateId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/message/template")
        .urlSegment(messageTemplateId)
        .delete()
        .go();
  }

  /**
   * Deletes the messenger for the given Id.
   *
   * @param messengerId The Id of the messenger to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteMessenger(UUID messengerId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/messenger")
        .urlSegment(messengerId)
        .delete()
        .go();
  }

  /**
   * Hard deletes a custom OAuth scope.
   * OAuth workflows that are still requesting the deleted OAuth scope may fail depending on the application's unknown scope policy.
   *
   * @param applicationId The Id of the application that the OAuth scope belongs to.
   * @param scopeId The Id of the OAuth scope to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteOAuthScope(UUID applicationId, UUID scopeId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("scope")
        .urlSegment(scopeId)
        .delete()
        .go();
  }

  /**
   * Deletes the user registration for the given user and application.
   *
   * @param userId The Id of the user whose registration is being deleted.
   * @param applicationId The Id of the application to remove the registration for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteRegistration(UUID userId, UUID applicationId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/registration")
        .urlSegment(userId)
        .urlSegment(applicationId)
        .delete()
        .go();
  }

  /**
   * Deletes the user registration for the given user and application along with the given JSON body that contains the event information.
   *
   * @param userId The Id of the user whose registration is being deleted.
   * @param applicationId The Id of the application to remove the registration for.
   * @param request The request body that contains the event information.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteRegistrationWithRequest(UUID userId, UUID applicationId, RegistrationDeleteRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/registration")
        .urlSegment(userId)
        .urlSegment(applicationId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Deletes the tenant based on the given Id on the URL. This permanently deletes all information, metrics, reports and data associated
   * with the tenant and everything under the tenant (applications, users, etc).
   *
   * @param tenantId The Id of the tenant to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteTenant(UUID tenantId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .delete()
        .go();
  }

  /**
   * Deletes the tenant for the given Id asynchronously.
   * This method is helpful if you do not want to wait for the delete operation to complete.
   *
   * @param tenantId The Id of the tenant to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteTenantAsync(UUID tenantId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .urlParameter("async", true)
        .delete()
        .go();
  }

  /**
   * Deletes the tenant based on the given request (sent to the API as JSON). This permanently deletes all information, metrics, reports and data associated
   * with the tenant and everything under the tenant (applications, users, etc).
   *
   * @param tenantId The Id of the tenant to delete.
   * @param request The request object that contains all the information used to delete the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteTenantWithRequest(UUID tenantId, TenantDeleteRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Deletes the theme for the given Id.
   *
   * @param themeId The Id of the theme to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteTheme(UUID themeId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/theme")
        .urlSegment(themeId)
        .delete()
        .go();
  }

  /**
   * Deletes the user for the given Id. This permanently deletes all information, metrics, reports and data associated
   * with the user.
   *
   * @param userId The Id of the user to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUser(UUID userId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .urlParameter("hardDelete", true)
        .delete()
        .go();
  }

  /**
   * Deletes the user action for the given Id. This permanently deletes the user action and also any history and logs of
   * the action being applied to any users.
   *
   * @param userActionId The Id of the user action to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUserAction(UUID userActionId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .urlParameter("hardDelete", true)
        .delete()
        .go();
  }

  /**
   * Deletes the user action reason for the given Id.
   *
   * @param userActionReasonId The Id of the user action reason to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUserActionReason(UUID userActionReasonId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user-action-reason")
        .urlSegment(userActionReasonId)
        .delete()
        .go();
  }

  /**
   * Remove an existing link that has been made from a 3rd party identity provider to a FusionAuth user.
   *
   * @param identityProviderId The unique Id of the identity provider.
   * @param identityProviderUserId The unique Id of the user in the 3rd party identity provider to unlink.
   * @param userId The unique Id of the FusionAuth user to unlink.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderLinkResponse, Errors> deleteUserLink(UUID identityProviderId, String identityProviderUserId, UUID userId) {
    return start(IdentityProviderLinkResponse.class, Errors.class)
        .uri("/api/identity-provider/link")
        .urlParameter("identityProviderId", identityProviderId)
        .urlParameter("identityProviderUserId", identityProviderUserId)
        .urlParameter("userId", userId)
        .delete()
        .go();
  }

  /**
   * Deletes the user based on the given request (sent to the API as JSON). This permanently deletes all information, metrics, reports and data associated
   * with the user.
   *
   * @param userId The Id of the user to delete (required).
   * @param request The request object that contains all the information used to delete the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUserWithRequest(UUID userId, UserDeleteSingleRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Deletes the users with the given Ids, or users matching the provided JSON query or queryString.
   * The order of preference is Ids, query and then queryString, it is recommended to only provide one of the three for the request.
   * <p>
   * This method can be used to deactivate or permanently delete (hard-delete) users based upon the hardDelete boolean in the request body.
   * Using the dryRun parameter you may also request the result of the action without actually deleting or deactivating any users.
   *
   * @param request The UserDeleteRequest.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to deleteUsersByQuery, use that method instead.
   */
  @Deprecated
  public ClientResponse<UserDeleteResponse, Errors> deleteUsers(UserDeleteRequest request) {
    return start(UserDeleteResponse.class, Errors.class)
        .uri("/api/user/bulk")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Deletes the users with the given Ids, or users matching the provided JSON query or queryString.
   * The order of preference is Ids, query and then queryString, it is recommended to only provide one of the three for the request.
   * <p>
   * This method can be used to deactivate or permanently delete (hard-delete) users based upon the hardDelete boolean in the request body.
   * Using the dryRun parameter you may also request the result of the action without actually deleting or deactivating any users.
   *
   * @param request The UserDeleteRequest.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserDeleteResponse, Errors> deleteUsersByQuery(UserDeleteRequest request) {
    return start(UserDeleteResponse.class, Errors.class)
        .uri("/api/user/bulk")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Deletes the WebAuthn credential for the given Id.
   *
   * @param id The Id of the WebAuthn credential to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteWebAuthnCredential(UUID id) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/webauthn")
        .urlSegment(id)
        .delete()
        .go();
  }

  /**
   * Deletes the webhook for the given Id.
   *
   * @param webhookId The Id of the webhook to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteWebhook(UUID webhookId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/webhook")
        .urlSegment(webhookId)
        .delete()
        .go();
  }

  /**
   * Disable two-factor authentication for a user.
   *
   * @param userId The Id of the User for which you're disabling two-factor authentication.
   * @param methodId The two-factor method identifier you wish to disable
   * @param code The two-factor code used verify the the caller knows the two-factor secret.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> disableTwoFactor(UUID userId, String methodId, String code) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/two-factor")
        .urlSegment(userId)
        .urlParameter("methodId", methodId)
        .urlParameter("code", code)
        .delete()
        .go();
  }

  /**
   * Disable two-factor authentication for a user using a JSON body rather than URL parameters.
   *
   * @param userId The Id of the User for which you're disabling two-factor authentication.
   * @param request The request information that contains the code and methodId along with any event information.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> disableTwoFactorWithRequest(UUID userId, TwoFactorDisableRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/two-factor")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Enable two-factor authentication for a user.
   *
   * @param userId The Id of the user to enable two-factor authentication.
   * @param request The two-factor enable request information.
   * @return The ClientResponse object.
   */
  public ClientResponse<TwoFactorResponse, Errors> enableTwoFactor(UUID userId, TwoFactorRequest request) {
    return start(TwoFactorResponse.class, Errors.class)
        .uri("/api/user/two-factor")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Exchanges an OAuth authorization code for an access token.
   * Makes a request to the Token endpoint to exchange the authorization code returned from the Authorize endpoint for an access token.
   *
   * @param code The authorization code returned on the /oauth2/authorize response.
   * @param client_id (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate.
   *     This parameter is optional when Basic Authorization is used to authenticate this request.
   * @param client_secret (Optional) The client secret. This value will be required if client authentication is enabled.
   * @param redirect_uri The URI to redirect to upon a successful request.
   * @return The ClientResponse object.
   */
  public ClientResponse<AccessToken, OAuthError> exchangeOAuthCodeForAccessToken(String code, String client_id, String client_secret, String redirect_uri) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("code", Arrays.asList(code));
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("grant_type", Arrays.asList("authorization_code"));
    parameters.put("redirect_uri", Arrays.asList(redirect_uri));
    return startAnonymous(AccessToken.class, OAuthError.class)
        .uri("/oauth2/token")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Exchanges an OAuth authorization code and code_verifier for an access token.
   * Makes a request to the Token endpoint to exchange the authorization code returned from the Authorize endpoint and a code_verifier for an access token.
   *
   * @param code The authorization code returned on the /oauth2/authorize response.
   * @param client_id (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate. This parameter is optional when the Authorization header is provided.
   *     This parameter is optional when Basic Authorization is used to authenticate this request.
   * @param client_secret (Optional) The client secret. This value may optionally be provided in the request body instead of the Authorization header.
   * @param redirect_uri The URI to redirect to upon a successful request.
   * @param code_verifier The random string generated previously. Will be compared with the code_challenge sent previously, which allows the OAuth provider to authenticate your app.
   * @return The ClientResponse object.
   */
  public ClientResponse<AccessToken, OAuthError> exchangeOAuthCodeForAccessTokenUsingPKCE(String code, String client_id, String client_secret, String redirect_uri, String code_verifier) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("code", Arrays.asList(code));
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("grant_type", Arrays.asList("authorization_code"));
    parameters.put("redirect_uri", Arrays.asList(redirect_uri));
    parameters.put("code_verifier", Arrays.asList(code_verifier));
    return startAnonymous(AccessToken.class, OAuthError.class)
        .uri("/oauth2/token")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Exchange a Refresh Token for an Access Token.
   * If you will be using the Refresh Token Grant, you will make a request to the Token endpoint to exchange the user’s refresh token for an access token.
   *
   * @param refresh_token The refresh token that you would like to use to exchange for an access token.
   * @param client_id (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate. This parameter is optional when the Authorization header is provided.
   *     This parameter is optional when Basic Authorization is used to authenticate this request.
   * @param client_secret (Optional) The client secret. This value may optionally be provided in the request body instead of the Authorization header.
   * @param scope (Optional) This parameter is optional and if omitted, the same scope requested during the authorization request will be used. If provided the scopes must match those requested during the initial authorization request.
   * @param user_code (Optional) The end-user verification code. This code is required if using this endpoint to approve the Device Authorization.
   * @return The ClientResponse object.
   */
  public ClientResponse<AccessToken, OAuthError> exchangeRefreshTokenForAccessToken(String refresh_token, String client_id, String client_secret, String scope, String user_code) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("refresh_token", Arrays.asList(refresh_token));
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("grant_type", Arrays.asList("refresh_token"));
    parameters.put("scope", Arrays.asList(scope));
    parameters.put("user_code", Arrays.asList(user_code));
    return startAnonymous(AccessToken.class, OAuthError.class)
        .uri("/oauth2/token")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Exchange a refresh token for a new JWT.
   *
   * @param request The refresh request.
   * @return The ClientResponse object.
   */
  public ClientResponse<JWTRefreshResponse, Errors> exchangeRefreshTokenForJWT(RefreshRequest request) {
    return startAnonymous(JWTRefreshResponse.class, Errors.class)
        .uri("/api/jwt/refresh")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Exchange User Credentials for a Token.
   * If you will be using the Resource Owner Password Credential Grant, you will make a request to the Token endpoint to exchange the user’s email and password for an access token.
   *
   * @param username The login identifier of the user. The login identifier can be either the email or the username.
   * @param password The user’s password.
   * @param client_id (Optional) The unique client identifier. The client Id is the Id of the FusionAuth Application in which you are attempting to authenticate. This parameter is optional when the Authorization header is provided.
   *     This parameter is optional when Basic Authorization is used to authenticate this request.
   * @param client_secret (Optional) The client secret. This value may optionally be provided in the request body instead of the Authorization header.
   * @param scope (Optional) This parameter is optional and if omitted, the same scope requested during the authorization request will be used. If provided the scopes must match those requested during the initial authorization request.
   * @param user_code (Optional) The end-user verification code. This code is required if using this endpoint to approve the Device Authorization.
   * @return The ClientResponse object.
   */
  public ClientResponse<AccessToken, OAuthError> exchangeUserCredentialsForAccessToken(String username, String password, String client_id, String client_secret, String scope, String user_code) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("username", Arrays.asList(username));
    parameters.put("password", Arrays.asList(password));
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("grant_type", Arrays.asList("password"));
    parameters.put("scope", Arrays.asList(scope));
    parameters.put("user_code", Arrays.asList(user_code));
    return startAnonymous(AccessToken.class, OAuthError.class)
        .uri("/oauth2/token")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Begins the forgot password sequence, which kicks off an email to the user so that they can reset their password.
   *
   * @param request The request that contains the information about the user so that they can be emailed.
   * @return The ClientResponse object.
   */
  public ClientResponse<ForgotPasswordResponse, Errors> forgotPassword(ForgotPasswordRequest request) {
    return start(ForgotPasswordResponse.class, Errors.class)
        .uri("/api/user/forgot-password")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Generate a new Email Verification Id to be used with the Verify Email API. This API will not attempt to send an
   * email to the User. This API may be used to collect the verificationId for use with a third party system.
   *
   * @param email The email address of the user that needs a new verification email.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyEmailResponse, Void> generateEmailVerificationId(String email) {
    return start(VerifyEmailResponse.class, Void.TYPE)
        .uri("/api/user/verify-email")
        .urlParameter("email", email)
        .urlParameter("sendVerifyEmail", false)
        .put()
        .go();
  }

  /**
   * Generate a new RSA or EC key pair or an HMAC secret.
   *
   * @param keyId (Optional) The Id for the key. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the key.
   * @return The ClientResponse object.
   */
  public ClientResponse<KeyResponse, Errors> generateKey(UUID keyId, KeyRequest request) {
    return start(KeyResponse.class, Errors.class)
        .uri("/api/key/generate")
        .urlSegment(keyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Generate a new Application Registration Verification Id to be used with the Verify Registration API. This API will not attempt to send an
   * email to the User. This API may be used to collect the verificationId for use with a third party system.
   *
   * @param email The email address of the user that needs a new verification email.
   * @param applicationId The Id of the application to be verified.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyRegistrationResponse, Void> generateRegistrationVerificationId(String email, UUID applicationId) {
    return start(VerifyRegistrationResponse.class, Void.TYPE)
        .uri("/api/user/verify-registration")
        .urlParameter("email", email)
        .urlParameter("sendVerifyPasswordEmail", false)
        .urlParameter("applicationId", applicationId)
        .put()
        .go();
  }

  /**
   * Generate two-factor recovery codes for a user. Generating two-factor recovery codes will invalidate any existing recovery codes. 
   *
   * @param userId The Id of the user to generate new Two Factor recovery codes.
   * @return The ClientResponse object.
   */
  public ClientResponse<TwoFactorRecoveryCodeResponse, Errors> generateTwoFactorRecoveryCodes(UUID userId) {
    return start(TwoFactorRecoveryCodeResponse.class, Errors.class)
        .uri("/api/user/two-factor/recovery-code")
        .urlSegment(userId)
        .post()
        .go();
  }

  /**
   * Generate a Two Factor secret that can be used to enable Two Factor authentication for a User. The response will contain
   * both the secret and a Base32 encoded form of the secret which can be shown to a User when using a 2 Step Authentication
   * application such as Google Authenticator.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<SecretResponse, Void> generateTwoFactorSecret() {
    return start(SecretResponse.class, Void.TYPE)
        .uri("/api/two-factor/secret")
        .get()
        .go();
  }

  /**
   * Generate a Two Factor secret that can be used to enable Two Factor authentication for a User. The response will contain
   * both the secret and a Base32 encoded form of the secret which can be shown to a User when using a 2 Step Authentication
   * application such as Google Authenticator.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<SecretResponse, Void> generateTwoFactorSecretUsingJWT(String encodedJWT) {
    return startAnonymous(SecretResponse.class, Void.TYPE)
        .uri("/api/two-factor/secret")
        .authorization("Bearer " + encodedJWT)
        .get()
        .go();
  }

  /**
   * Handles login via third-parties including Social login, external OAuth and OpenID Connect, and other
   * login systems.
   *
   * @param request The third-party login request that contains information from the third-party login
   *     providers that FusionAuth uses to reconcile the user's account.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> identityProviderLogin(IdentityProviderLoginRequest request) {
    return startAnonymous(LoginResponse.class, Errors.class)
        .uri("/api/identity-provider/login")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Import an existing RSA or EC key pair or an HMAC secret.
   *
   * @param keyId (Optional) The Id for the key. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all the information used to create the key.
   * @return The ClientResponse object.
   */
  public ClientResponse<KeyResponse, Errors> importKey(UUID keyId, KeyRequest request) {
    return start(KeyResponse.class, Errors.class)
        .uri("/api/key/import")
        .urlSegment(keyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Bulk imports refresh tokens. This request performs minimal validation and runs batch inserts of refresh tokens with the
   * expectation that each token represents a user that already exists and is registered for the corresponding FusionAuth
   * Application. This is done to increases the insert performance.
   * <p>
   * Therefore, if you encounter an error due to a database key violation, the response will likely offer a generic
   * explanation. If you encounter an error, you may optionally enable additional validation to receive a JSON response
   * body with specific validation errors. This will slow the request down but will allow you to identify the cause of
   * the failure. See the validateDbConstraints request parameter.
   *
   * @param request The request that contains all the information about all the refresh tokens to import.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> importRefreshTokens(RefreshTokenImportRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/refresh-token/import")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Bulk imports users. This request performs minimal validation and runs batch inserts of users with the expectation
   * that each user does not yet exist and each registration corresponds to an existing FusionAuth Application. This is done to
   * increases the insert performance.
   * <p>
   * Therefore, if you encounter an error due to a database key violation, the response will likely offer
   * a generic explanation. If you encounter an error, you may optionally enable additional validation to receive a JSON response
   * body with specific validation errors. This will slow the request down but will allow you to identify the cause of the failure. See
   * the validateDbConstraints request parameter.
   *
   * @param request The request that contains all the information about all the users to import.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> importUsers(ImportRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/import")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Import a WebAuthn credential
   *
   * @param request An object containing data necessary for importing the credential
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> importWebAuthnCredential(WebAuthnCredentialImportRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/webauthn/import")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Inspect an access token issued as the result of the User based grant such as the Authorization Code Grant, Implicit Grant, the User Credentials Grant or the Refresh Grant.
   *
   * @param client_id The unique client identifier. The client Id is the Id of the FusionAuth Application for which this token was generated.
   * @param token The access token returned by this OAuth provider as the result of a successful client credentials grant.
   * @return The ClientResponse object.
   */
  public ClientResponse<IntrospectResponse, OAuthError> introspectAccessToken(String client_id, String token) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("token", Arrays.asList(token));
    return startAnonymous(IntrospectResponse.class, OAuthError.class)
        .uri("/oauth2/introspect")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Inspect an access token issued as the result of the Client Credentials Grant.
   *
   * @param token The access token returned by this OAuth provider as the result of a successful client credentials grant.
   * @return The ClientResponse object.
   */
  public ClientResponse<IntrospectResponse, OAuthError> introspectClientCredentialsAccessToken(String token) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("token", Arrays.asList(token));
    return startAnonymous(IntrospectResponse.class, OAuthError.class)
        .uri("/oauth2/introspect")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .post()
        .go();
  }

  /**
   * Issue a new access token (JWT) for the requested Application after ensuring the provided JWT is valid. A valid
   * access token is properly signed and not expired.
   * <p>
   * This API may be used in an SSO configuration to issue new tokens for another application after the user has
   * obtained a valid token from authentication.
   *
   * @param applicationId The Application Id for which you are requesting a new access token be issued.
   * @param encodedJWT The encoded JWT (access token).
   * @param refreshToken (Optional) An existing refresh token used to request a refresh token in addition to a JWT in the response.
   *     <p>The target application represented by the applicationId request parameter must have refresh
   *     tokens enabled in order to receive a refresh token in the response.</p>
   * @return The ClientResponse object.
   */
  public ClientResponse<IssueResponse, Errors> issueJWT(UUID applicationId, String encodedJWT, String refreshToken) {
    return startAnonymous(IssueResponse.class, Errors.class)
        .uri("/api/jwt/issue")
        .authorization("Bearer " + encodedJWT)
        .urlParameter("applicationId", applicationId)
        .urlParameter("refreshToken", refreshToken)
        .get()
        .go();
  }

  /**
   * Authenticates a user to FusionAuth. 
   * <p>
   * This API optionally requires an API key. See <code>Application.loginConfiguration.requireAuthentication</code>.
   *
   * @param request The login request that contains the user credentials used to log them in.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> login(LoginRequest request) {
    return start(LoginResponse.class, Errors.class)
        .uri("/api/login")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Sends a ping to FusionAuth indicating that the user was automatically logged into an application. When using
   * FusionAuth's SSO or your own, you should call this if the user is already logged in centrally, but accesses an
   * application where they no longer have a session. This helps correctly track login counts, times and helps with
   * reporting.
   *
   * @param userId The Id of the user that was logged in.
   * @param applicationId The Id of the application that they logged into.
   * @param callerIPAddress (Optional) The IP address of the end-user that is logging in. If a null value is provided
   *     the IP address will be that of the client or last proxy that sent the request.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> loginPing(UUID userId, UUID applicationId, String callerIPAddress) {
    return start(LoginResponse.class, Errors.class)
        .uri("/api/login")
        .urlSegment(userId)
        .urlSegment(applicationId)
        .urlParameter("ipAddress", callerIPAddress)
        .put()
        .go();
  }

  /**
   * Sends a ping to FusionAuth indicating that the user was automatically logged into an application. When using
   * FusionAuth's SSO or your own, you should call this if the user is already logged in centrally, but accesses an
   * application where they no longer have a session. This helps correctly track login counts, times and helps with
   * reporting.
   *
   * @param request The login request that contains the user credentials used to log them in.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> loginPingWithRequest(LoginPingRequest request) {
    return start(LoginResponse.class, Errors.class)
        .uri("/api/login")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * The Logout API is intended to be used to remove the refresh token and access token cookies if they exist on the
   * client and revoke the refresh token stored. This API does nothing if the request does not contain an access
   * token or refresh token cookies.
   *
   * @param global When this value is set to true all the refresh tokens issued to the owner of the
   *     provided token will be revoked.
   * @param refreshToken (Optional) The refresh_token as a request parameter instead of coming in via a cookie.
   *     If provided this takes precedence over the cookie.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> logout(boolean global, String refreshToken) {
    return startAnonymous(Void.TYPE, Void.TYPE)
        .uri("/api/logout")
        .urlParameter("global", global)
        .urlParameter("refreshToken", refreshToken)
        .post()
        .go();
  }

  /**
   * The Logout API is intended to be used to remove the refresh token and access token cookies if they exist on the
   * client and revoke the refresh token stored. This API takes the refresh token in the JSON body.
   *
   * @param request The request object that contains all the information used to logout the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> logoutWithRequest(LogoutRequest request) {
    return startAnonymous(Void.TYPE, Void.TYPE)
        .uri("/api/logout")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves the identity provider for the given domain. A 200 response code indicates the domain is managed
   * by a registered identity provider. A 404 indicates the domain is not managed.
   *
   * @param domain The domain or email address to lookup.
   * @return The ClientResponse object.
   */
  public ClientResponse<LookupResponse, Void> lookupIdentityProvider(String domain) {
    return start(LookupResponse.class, Void.TYPE)
        .uri("/api/identity-provider/lookup")
        .urlParameter("domain", domain)
        .get()
        .go();
  }

  /**
   * Modifies a temporal user action by changing the expiration of the action and optionally adding a comment to the
   * action.
   *
   * @param actionId The Id of the action to modify. This is technically the user action log Id.
   * @param request The request that contains all the information about the modification.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> modifyAction(UUID actionId, ActionRequest request) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlSegment(actionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Complete a login request using a passwordless code
   *
   * @param request The passwordless login request that contains all the information used to complete login.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> passwordlessLogin(PasswordlessLoginRequest request) {
    return startAnonymous(LoginResponse.class, Errors.class)
        .uri("/api/passwordless/login")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Updates an API key with the given Id.
   *
   * @param keyId The Id of the API key. If not provided a secure random api key will be generated.
   * @param request The request object that contains all the information needed to create the API key.
   * @return The ClientResponse object.
   */
  public ClientResponse<APIKeyResponse, Errors> patchAPIKey(UUID keyId, Map<String, Object> request) {
    return start(APIKeyResponse.class, Errors.class)
        .uri("/api/api-key")
        .urlSegment(keyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the application with the given Id.
   *
   * @param applicationId The Id of the application to update.
   * @param request The request that contains just the new application information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> patchApplication(UUID applicationId, Map<String, Object> request) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the application role with the given Id for the application.
   *
   * @param applicationId The Id of the application that the role belongs to.
   * @param roleId The Id of the role to update.
   * @param request The request that contains just the new role information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> patchApplicationRole(UUID applicationId, UUID roleId, Map<String, Object> request) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("role")
        .urlSegment(roleId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the connector with the given Id.
   *
   * @param connectorId The Id of the connector to update.
   * @param request The request that contains just the new connector information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConnectorResponse, Errors> patchConnector(UUID connectorId, Map<String, Object> request) {
    return start(ConnectorResponse.class, Errors.class)
        .uri("/api/connector")
        .urlSegment(connectorId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the consent with the given Id.
   *
   * @param consentId The Id of the consent to update.
   * @param request The request that contains just the new consent information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConsentResponse, Errors> patchConsent(UUID consentId, Map<String, Object> request) {
    return start(ConsentResponse.class, Errors.class)
        .uri("/api/consent")
        .urlSegment(consentId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the email template with the given Id.
   *
   * @param emailTemplateId The Id of the email template to update.
   * @param request The request that contains just the new email template information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Errors> patchEmailTemplate(UUID emailTemplateId, Map<String, Object> request) {
    return start(EmailTemplateResponse.class, Errors.class)
        .uri("/api/email/template")
        .urlSegment(emailTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the Entity with the given Id.
   *
   * @param entityId The Id of the Entity Type to update.
   * @param request The request that contains just the new Entity information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityResponse, Errors> patchEntity(UUID entityId, Map<String, Object> request) {
    return start(EntityResponse.class, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the Entity Type with the given Id.
   *
   * @param entityTypeId The Id of the Entity Type to update.
   * @param request The request that contains just the new Entity Type information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> patchEntityType(UUID entityTypeId, Map<String, Object> request) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Patches the permission with the given Id for the entity type.
   *
   * @param entityTypeId The Id of the entityType that the permission belongs to.
   * @param permissionId The Id of the permission to patch.
   * @param request The request that contains the new permission information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> patchEntityTypePermission(UUID entityTypeId, UUID permissionId, Map<String, Object> request) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .urlSegment("permission")
        .urlSegment(permissionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Patches the form with the given Id.
   *
   * @param formId The Id of the form to patch.
   * @param request The request object that contains the new form information.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormResponse, Errors> patchForm(UUID formId, Map<String, Object> request) {
    return start(FormResponse.class, Errors.class)
        .uri("/api/form")
        .urlSegment(formId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Patches the form field with the given Id.
   *
   * @param fieldId The Id of the form field to patch.
   * @param request The request object that contains the new form field information.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormFieldResponse, Errors> patchFormField(UUID fieldId, Map<String, Object> request) {
    return start(FormFieldResponse.class, Errors.class)
        .uri("/api/form/field")
        .urlSegment(fieldId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the group with the given Id.
   *
   * @param groupId The Id of the group to update.
   * @param request The request that contains just the new group information.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> patchGroup(UUID groupId, Map<String, Object> request) {
    return start(GroupResponse.class, Errors.class)
        .uri("/api/group")
        .urlSegment(groupId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Update the IP Access Control List with the given Id.
   *
   * @param accessControlListId The Id of the IP Access Control List to patch.
   * @param request The request that contains the new IP Access Control List information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IPAccessControlListResponse, Errors> patchIPAccessControlList(UUID accessControlListId, Map<String, Object> request) {
    return start(IPAccessControlListResponse.class, Errors.class)
        .uri("/api/ip-acl")
        .urlSegment(accessControlListId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the identity provider with the given Id.
   *
   * @param identityProviderId The Id of the identity provider to update.
   * @param request The request object that contains just the updated identity provider information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> patchIdentityProvider(UUID identityProviderId, Map<String, Object> request) {
    return start(IdentityProviderResponse.class, Errors.class)
        .uri("/api/identity-provider")
        .urlSegment(identityProviderId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the available integrations.
   *
   * @param request The request that contains just the new integration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IntegrationResponse, Errors> patchIntegrations(Map<String, Object> request) {
    return start(IntegrationResponse.class, Errors.class)
        .uri("/api/integration")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the lambda with the given Id.
   *
   * @param lambdaId The Id of the lambda to update.
   * @param request The request that contains just the new lambda information.
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaResponse, Errors> patchLambda(UUID lambdaId, Map<String, Object> request) {
    return start(LambdaResponse.class, Errors.class)
        .uri("/api/lambda")
        .urlSegment(lambdaId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the message template with the given Id.
   *
   * @param messageTemplateId The Id of the message template to update.
   * @param request The request that contains just the new message template information.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessageTemplateResponse, Errors> patchMessageTemplate(UUID messageTemplateId, Map<String, Object> request) {
    return start(MessageTemplateResponse.class, Errors.class)
        .uri("/api/message/template")
        .urlSegment(messageTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the messenger with the given Id.
   *
   * @param messengerId The Id of the messenger to update.
   * @param request The request that contains just the new messenger information.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessengerResponse, Errors> patchMessenger(UUID messengerId, Map<String, Object> request) {
    return start(MessengerResponse.class, Errors.class)
        .uri("/api/messenger")
        .urlSegment(messengerId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the custom OAuth scope with the given Id for the application.
   *
   * @param applicationId The Id of the application that the OAuth scope belongs to.
   * @param scopeId The Id of the OAuth scope to update.
   * @param request The request that contains just the new OAuth scope information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationOAuthScopeResponse, Errors> patchOAuthScope(UUID applicationId, UUID scopeId, Map<String, Object> request) {
    return start(ApplicationOAuthScopeResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("scope")
        .urlSegment(scopeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the registration for the user with the given Id and the application defined in the request.
   *
   * @param userId The Id of the user whose registration is going to be updated.
   * @param request The request that contains just the new registration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> patchRegistration(UUID userId, Map<String, Object> request) {
    return start(RegistrationResponse.class, Errors.class)
        .uri("/api/user/registration")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the system configuration.
   *
   * @param request The request that contains just the new system configuration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<SystemConfigurationResponse, Errors> patchSystemConfiguration(Map<String, Object> request) {
    return start(SystemConfigurationResponse.class, Errors.class)
        .uri("/api/system-configuration")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the tenant with the given Id.
   *
   * @param tenantId The Id of the tenant to update.
   * @param request The request that contains just the new tenant information.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> patchTenant(UUID tenantId, Map<String, Object> request) {
    return start(TenantResponse.class, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the theme with the given Id.
   *
   * @param themeId The Id of the theme to update.
   * @param request The request that contains just the new theme information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ThemeResponse, Errors> patchTheme(UUID themeId, Map<String, Object> request) {
    return start(ThemeResponse.class, Errors.class)
        .uri("/api/theme")
        .urlSegment(themeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the user with the given Id.
   *
   * @param userId The Id of the user to update.
   * @param request The request that contains just the new user information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> patchUser(UUID userId, Map<String, Object> request) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the user action with the given Id.
   *
   * @param userActionId The Id of the user action to update.
   * @param request The request that contains just the new user action information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> patchUserAction(UUID userActionId, Map<String, Object> request) {
    return start(UserActionResponse.class, Errors.class)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, the user action reason with the given Id.
   *
   * @param userActionReasonId The Id of the user action reason to update.
   * @param request The request that contains just the new user action reason information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Errors> patchUserActionReason(UUID userActionReasonId, Map<String, Object> request) {
    return start(UserActionReasonResponse.class, Errors.class)
        .uri("/api/user-action-reason")
        .urlSegment(userActionReasonId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Updates, via PATCH, a single User consent by Id.
   *
   * @param userConsentId The User Consent Id
   * @param request The request that contains just the new user consent information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserConsentResponse, Errors> patchUserConsent(UUID userConsentId, Map<String, Object> request) {
    return start(UserConsentResponse.class, Errors.class)
        .uri("/api/user/consent")
        .urlSegment(userConsentId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Patches the webhook with the given Id.
   *
   * @param webhookId The Id of the webhook to update.
   * @param request The request that contains the new webhook information.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Errors> patchWebhook(UUID webhookId, Map<String, Object> request) {
    return start(WebhookResponse.class, Errors.class)
        .uri("/api/webhook")
        .urlSegment(webhookId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .patch()
        .go();
  }

  /**
   * Reactivates the application with the given Id.
   *
   * @param applicationId The Id of the application to reactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> reactivateApplication(UUID applicationId) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlParameter("reactivate", true)
        .put()
        .go();
  }

  /**
   * Reactivates the user with the given Id.
   *
   * @param userId The Id of the user to reactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> reactivateUser(UUID userId) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .urlParameter("reactivate", true)
        .put()
        .go();
  }

  /**
   * Reactivates the user action with the given Id.
   *
   * @param userActionId The Id of the user action to reactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> reactivateUserAction(UUID userActionId) {
    return start(UserActionResponse.class, Errors.class)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .urlParameter("reactivate", true)
        .put()
        .go();
  }

  /**
   * Reconcile a User to FusionAuth using JWT issued from another Identity Provider.
   *
   * @param request The reconcile request that contains the data to reconcile the User.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> reconcileJWT(IdentityProviderLoginRequest request) {
    return startAnonymous(LoginResponse.class, Errors.class)
        .uri("/api/jwt/reconcile")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Request a refresh of the Entity search index. This API is not generally necessary and the search index will become consistent in a
   * reasonable amount of time. There may be scenarios where you may wish to manually request an index refresh. One example may be 
   * if you are using the Search API or Delete Tenant API immediately following a Entity Create etc, you may wish to request a refresh to
   *  ensure the index immediately current before making a query request to the search index.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> refreshEntitySearchIndex() {
    return start(Void.TYPE, Void.TYPE)
        .uri("/api/entity/search")
        .put()
        .go();
  }

  /**
   * Request a refresh of the User search index. This API is not generally necessary and the search index will become consistent in a
   * reasonable amount of time. There may be scenarios where you may wish to manually request an index refresh. One example may be 
   * if you are using the Search API or Delete Tenant API immediately following a User Create etc, you may wish to request a refresh to
   *  ensure the index immediately current before making a query request to the search index.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> refreshUserSearchIndex() {
    return start(Void.TYPE, Void.TYPE)
        .uri("/api/user/search")
        .put()
        .go();
  }

  /**
   * Regenerates any keys that are used by the FusionAuth Reactor.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> regenerateReactorKeys() {
    return start(Void.TYPE, Void.TYPE)
        .uri("/api/reactor")
        .put()
        .go();
  }

  /**
   * Registers a user for an application. If you provide the User and the UserRegistration object on this request, it
   * will create the user as well as register them for the application. This is called a Full Registration. However, if
   * you only provide the UserRegistration object, then the user must already exist and they will be registered for the
   * application. The user Id can also be provided and it will either be used to look up an existing user or it will be
   * used for the newly created User.
   *
   * @param userId (Optional) The Id of the user being registered for the application and optionally created.
   * @param request The request that optionally contains the User and must contain the UserRegistration.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> register(UUID userId, RegistrationRequest request) {
    return start(RegistrationResponse.class, Errors.class)
        .uri("/api/user/registration")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Requests Elasticsearch to delete and rebuild the index for FusionAuth users or entities. Be very careful when running this request as it will 
   * increase the CPU and I/O load on your database until the operation completes. Generally speaking you do not ever need to run this operation unless 
   * instructed by FusionAuth support, or if you are migrating a database another system and you are not brining along the Elasticsearch index. 
   * <p>
   * You have been warned.
   *
   * @param request The request that contains the index name.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> reindex(ReindexRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/system/reindex")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Removes a user from the family with the given Id.
   *
   * @param familyId The Id of the family to remove the user from.
   * @param userId The Id of the user to remove from the family.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> removeUserFromFamily(UUID familyId, UUID userId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/family")
        .urlSegment(familyId)
        .urlSegment(userId)
        .delete()
        .go();
  }

  /**
   * Re-sends the verification email to the user.
   *
   * @param email The email address of the user that needs a new verification email.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyEmailResponse, Errors> resendEmailVerification(String email) {
    return start(VerifyEmailResponse.class, Errors.class)
        .uri("/api/user/verify-email")
        .urlParameter("email", email)
        .put()
        .go();
  }

  /**
   * Re-sends the verification email to the user. If the Application has configured a specific email template this will be used
   * instead of the tenant configuration.
   *
   * @param applicationId The unique Application Id to used to resolve an application specific email template.
   * @param email The email address of the user that needs a new verification email.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyEmailResponse, Errors> resendEmailVerificationWithApplicationTemplate(UUID applicationId, String email) {
    return start(VerifyEmailResponse.class, Errors.class)
        .uri("/api/user/verify-email")
        .urlParameter("applicationId", applicationId)
        .urlParameter("email", email)
        .put()
        .go();
  }

  /**
   * Re-sends the application registration verification email to the user.
   *
   * @param email The email address of the user that needs a new verification email.
   * @param applicationId The Id of the application to be verified.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyRegistrationResponse, Errors> resendRegistrationVerification(String email, UUID applicationId) {
    return start(VerifyRegistrationResponse.class, Errors.class)
        .uri("/api/user/verify-registration")
        .urlParameter("email", email)
        .urlParameter("applicationId", applicationId)
        .put()
        .go();
  }

  /**
   * Retrieves an authentication API key for the given Id.
   *
   * @param keyId The Id of the API key to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<APIKeyResponse, Errors> retrieveAPIKey(UUID keyId) {
    return start(APIKeyResponse.class, Errors.class)
        .uri("/api/api-key")
        .urlSegment(keyId)
        .get()
        .go();
  }

  /**
   * Retrieves a single action log (the log of a user action that was taken on a user previously) for the given Id.
   *
   * @param actionId The Id of the action to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveAction(UUID actionId) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlSegment(actionId)
        .get()
        .go();
  }

  /**
   * Retrieves all the actions for the user with the given Id. This will return all time based actions that are active,
   * and inactive as well as non-time based actions.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveActions(UUID userId) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves all the actions for the user with the given Id that are currently preventing the User from logging in.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveActionsPreventingLogin(UUID userId) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlParameter("userId", userId)
        .urlParameter("preventingLogin", true)
        .get()
        .go();
  }

  /**
   * Retrieves all the actions for the user with the given Id that are currently active.
   * An active action means one that is time based and has not been canceled, and has not ended.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveActiveActions(UUID userId) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlParameter("userId", userId)
        .urlParameter("active", true)
        .get()
        .go();
  }

  /**
   * Retrieves the application for the given Id or all the applications if the Id is null.
   *
   * @param applicationId (Optional) The application Id.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Void> retrieveApplication(UUID applicationId) {
    return start(ApplicationResponse.class, Void.TYPE)
        .uri("/api/application")
        .urlSegment(applicationId)
        .get()
        .go();
  }

  /**
   * Retrieves all the applications.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Void> retrieveApplications() {
    return start(ApplicationResponse.class, Void.TYPE)
        .uri("/api/application")
        .get()
        .go();
  }

  /**
   * Retrieves a single audit log for the given Id.
   *
   * @param auditLogId The Id of the audit log to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<AuditLogResponse, Errors> retrieveAuditLog(Integer auditLogId) {
    return start(AuditLogResponse.class, Errors.class)
        .uri("/api/system/audit-log")
        .urlSegment(auditLogId)
        .get()
        .go();
  }

  /**
   * Retrieves the connector with the given Id.
   *
   * @param connectorId The Id of the connector.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConnectorResponse, Void> retrieveConnector(UUID connectorId) {
    return start(ConnectorResponse.class, Void.TYPE)
        .uri("/api/connector")
        .urlSegment(connectorId)
        .get()
        .go();
  }

  /**
   * Retrieves all the connectors.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ConnectorResponse, Void> retrieveConnectors() {
    return start(ConnectorResponse.class, Void.TYPE)
        .uri("/api/connector")
        .get()
        .go();
  }

  /**
   * Retrieves the Consent for the given Id.
   *
   * @param consentId The Id of the consent.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConsentResponse, Void> retrieveConsent(UUID consentId) {
    return start(ConsentResponse.class, Void.TYPE)
        .uri("/api/consent")
        .urlSegment(consentId)
        .get()
        .go();
  }

  /**
   * Retrieves all the consent.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ConsentResponse, Void> retrieveConsents() {
    return start(ConsentResponse.class, Void.TYPE)
        .uri("/api/consent")
        .get()
        .go();
  }

  /**
   * Retrieves the daily active user report between the two instants. If you specify an application Id, it will only
   * return the daily active counts for that application.
   *
   * @param applicationId (Optional) The application Id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<DailyActiveUserReportResponse, Errors> retrieveDailyActiveReport(UUID applicationId, long start, long end) {
    return start(DailyActiveUserReportResponse.class, Errors.class)
        .uri("/api/report/daily-active-user")
        .urlParameter("applicationId", applicationId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .get()
        .go();
  }

  /**
   * Retrieves the email template for the given Id. If you don't specify the Id, this will return all the email templates.
   *
   * @param emailTemplateId (Optional) The Id of the email template.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Void> retrieveEmailTemplate(UUID emailTemplateId) {
    return start(EmailTemplateResponse.class, Void.TYPE)
        .uri("/api/email/template")
        .urlSegment(emailTemplateId)
        .get()
        .go();
  }

  /**
   * Creates a preview of the email template provided in the request. This allows you to preview an email template that
   * hasn't been saved to the database yet. The entire email template does not need to be provided on the request. This
   * will create the preview based on whatever is given.
   *
   * @param request The request that contains the email template and optionally a locale to render it in.
   * @return The ClientResponse object.
   */
  public ClientResponse<PreviewResponse, Errors> retrieveEmailTemplatePreview(PreviewRequest request) {
    return start(PreviewResponse.class, Errors.class)
        .uri("/api/email/template/preview")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves all the email templates.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Void> retrieveEmailTemplates() {
    return start(EmailTemplateResponse.class, Void.TYPE)
        .uri("/api/email/template")
        .get()
        .go();
  }

  /**
   * Retrieves the Entity for the given Id.
   *
   * @param entityId The Id of the Entity.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityResponse, Errors> retrieveEntity(UUID entityId) {
    return start(EntityResponse.class, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .get()
        .go();
  }

  /**
   * Retrieves an Entity Grant for the given Entity and User/Entity.
   *
   * @param entityId The Id of the Entity.
   * @param recipientEntityId (Optional) The Id of the Entity that the Entity Grant is for.
   * @param userId (Optional) The Id of the User that the Entity Grant is for.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityGrantResponse, Errors> retrieveEntityGrant(UUID entityId, UUID recipientEntityId, UUID userId) {
    return start(EntityGrantResponse.class, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .urlSegment("grant")
        .urlParameter("recipientEntityId", recipientEntityId)
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves the Entity Type for the given Id.
   *
   * @param entityTypeId The Id of the Entity Type.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> retrieveEntityType(UUID entityTypeId) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .get()
        .go();
  }

  /**
   * Retrieves all the Entity Types.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> retrieveEntityTypes() {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .get()
        .go();
  }

  /**
   * Retrieves a single event log for the given Id.
   *
   * @param eventLogId The Id of the event log to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<EventLogResponse, Errors> retrieveEventLog(Integer eventLogId) {
    return start(EventLogResponse.class, Errors.class)
        .uri("/api/system/event-log")
        .urlSegment(eventLogId)
        .get()
        .go();
  }

  /**
   * Retrieves all the families that a user belongs to.
   *
   * @param userId The User's id
   * @return The ClientResponse object.
   */
  public ClientResponse<FamilyResponse, Void> retrieveFamilies(UUID userId) {
    return start(FamilyResponse.class, Void.TYPE)
        .uri("/api/user/family")
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves all the members of a family by the unique Family Id.
   *
   * @param familyId The unique Id of the Family.
   * @return The ClientResponse object.
   */
  public ClientResponse<FamilyResponse, Void> retrieveFamilyMembersByFamilyId(UUID familyId) {
    return start(FamilyResponse.class, Void.TYPE)
        .uri("/api/user/family")
        .urlSegment(familyId)
        .get()
        .go();
  }

  /**
   * Retrieves the form with the given Id.
   *
   * @param formId The Id of the form.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormResponse, Void> retrieveForm(UUID formId) {
    return start(FormResponse.class, Void.TYPE)
        .uri("/api/form")
        .urlSegment(formId)
        .get()
        .go();
  }

  /**
   * Retrieves the form field with the given Id.
   *
   * @param fieldId The Id of the form field.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormFieldResponse, Void> retrieveFormField(UUID fieldId) {
    return start(FormFieldResponse.class, Void.TYPE)
        .uri("/api/form/field")
        .urlSegment(fieldId)
        .get()
        .go();
  }

  /**
   * Retrieves all the forms fields
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<FormFieldResponse, Void> retrieveFormFields() {
    return start(FormFieldResponse.class, Void.TYPE)
        .uri("/api/form/field")
        .get()
        .go();
  }

  /**
   * Retrieves all the forms.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<FormResponse, Void> retrieveForms() {
    return start(FormResponse.class, Void.TYPE)
        .uri("/api/form")
        .get()
        .go();
  }

  /**
   * Retrieves the group for the given Id.
   *
   * @param groupId The Id of the group.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> retrieveGroup(UUID groupId) {
    return start(GroupResponse.class, Errors.class)
        .uri("/api/group")
        .urlSegment(groupId)
        .get()
        .go();
  }

  /**
   * Retrieves all the groups.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Void> retrieveGroups() {
    return start(GroupResponse.class, Void.TYPE)
        .uri("/api/group")
        .get()
        .go();
  }

  /**
   * Retrieves the IP Access Control List with the given Id.
   *
   * @param ipAccessControlListId The Id of the IP Access Control List.
   * @return The ClientResponse object.
   */
  public ClientResponse<IPAccessControlListResponse, Void> retrieveIPAccessControlList(UUID ipAccessControlListId) {
    return start(IPAccessControlListResponse.class, Void.TYPE)
        .uri("/api/ip-acl")
        .urlSegment(ipAccessControlListId)
        .get()
        .go();
  }

  /**
   * Retrieves the identity provider for the given Id or all the identity providers if the Id is null.
   *
   * @param identityProviderId The identity provider Id.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> retrieveIdentityProvider(UUID identityProviderId) {
    return start(IdentityProviderResponse.class, Errors.class)
        .uri("/api/identity-provider")
        .urlSegment(identityProviderId)
        .get()
        .go();
  }

  /**
   * Retrieves one or more identity provider for the given type. For types such as Google, Facebook, Twitter and LinkedIn, only a single 
   * identity provider can exist. For types such as OpenID Connect and SAMLv2 more than one identity provider can be configured so this request 
   * may return multiple identity providers.
   *
   * @param type The type of the identity provider.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> retrieveIdentityProviderByType(IdentityProviderType type) {
    return start(IdentityProviderResponse.class, Errors.class)
        .uri("/api/identity-provider")
        .urlParameter("type", type)
        .get()
        .go();
  }

  /**
   * Retrieves all the identity providers.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Void> retrieveIdentityProviders() {
    return start(IdentityProviderResponse.class, Void.TYPE)
        .uri("/api/identity-provider")
        .get()
        .go();
  }

  /**
   * Retrieves all the actions for the user with the given Id that are currently inactive.
   * An inactive action means one that is time based and has been canceled or has expired, or is not time based.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveInactiveActions(UUID userId) {
    return start(ActionResponse.class, Errors.class)
        .uri("/api/user/action")
        .urlParameter("userId", userId)
        .urlParameter("active", false)
        .get()
        .go();
  }

  /**
   * Retrieves all the applications that are currently inactive.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Void> retrieveInactiveApplications() {
    return start(ApplicationResponse.class, Void.TYPE)
        .uri("/api/application")
        .urlParameter("inactive", true)
        .get()
        .go();
  }

  /**
   * Retrieves all the user actions that are currently inactive.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Void> retrieveInactiveUserActions() {
    return start(UserActionResponse.class, Void.TYPE)
        .uri("/api/user-action")
        .urlParameter("inactive", true)
        .get()
        .go();
  }

  /**
   * Retrieves the available integrations.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<IntegrationResponse, Void> retrieveIntegration() {
    return start(IntegrationResponse.class, Void.TYPE)
        .uri("/api/integration")
        .get()
        .go();
  }

  /**
   * Retrieves the Public Key configured for verifying JSON Web Tokens (JWT) by the key Id (kid).
   *
   * @param keyId The Id of the public key (kid).
   * @return The ClientResponse object.
   */
  public ClientResponse<PublicKeyResponse, Void> retrieveJWTPublicKey(String keyId) {
    return startAnonymous(PublicKeyResponse.class, Void.TYPE)
        .uri("/api/jwt/public-key")
        .urlParameter("kid", keyId)
        .get()
        .go();
  }

  /**
   * Retrieves the Public Key configured for verifying the JSON Web Tokens (JWT) issued by the Login API by the Application Id.
   *
   * @param applicationId The Id of the Application for which this key is used.
   * @return The ClientResponse object.
   */
  public ClientResponse<PublicKeyResponse, Void> retrieveJWTPublicKeyByApplicationId(String applicationId) {
    return startAnonymous(PublicKeyResponse.class, Void.TYPE)
        .uri("/api/jwt/public-key")
        .urlParameter("applicationId", applicationId)
        .get()
        .go();
  }

  /**
   * Retrieves all Public Keys configured for verifying JSON Web Tokens (JWT).
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<PublicKeyResponse, Void> retrieveJWTPublicKeys() {
    return startAnonymous(PublicKeyResponse.class, Void.TYPE)
        .uri("/api/jwt/public-key")
        .get()
        .go();
  }

  /**
   * Returns public keys used by FusionAuth to cryptographically verify JWTs using the JSON Web Key format.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<JWKSResponse, Void> retrieveJsonWebKeySet() {
    return startAnonymous(JWKSResponse.class, Void.TYPE)
        .uri("/.well-known/jwks.json")
        .get()
        .go();
  }

  /**
   * Retrieves the key for the given Id.
   *
   * @param keyId The Id of the key.
   * @return The ClientResponse object.
   */
  public ClientResponse<KeyResponse, Errors> retrieveKey(UUID keyId) {
    return start(KeyResponse.class, Errors.class)
        .uri("/api/key")
        .urlSegment(keyId)
        .get()
        .go();
  }

  /**
   * Retrieves all the keys.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<KeyResponse, Void> retrieveKeys() {
    return start(KeyResponse.class, Void.TYPE)
        .uri("/api/key")
        .get()
        .go();
  }

  /**
   * Retrieves the lambda for the given Id.
   *
   * @param lambdaId The Id of the lambda.
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaResponse, Errors> retrieveLambda(UUID lambdaId) {
    return start(LambdaResponse.class, Errors.class)
        .uri("/api/lambda")
        .urlSegment(lambdaId)
        .get()
        .go();
  }

  /**
   * Retrieves all the lambdas.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaResponse, Void> retrieveLambdas() {
    return start(LambdaResponse.class, Void.TYPE)
        .uri("/api/lambda")
        .get()
        .go();
  }

  /**
   * Retrieves all the lambdas for the provided type.
   *
   * @param type The type of the lambda to return.
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaResponse, Void> retrieveLambdasByType(LambdaType type) {
    return start(LambdaResponse.class, Void.TYPE)
        .uri("/api/lambda")
        .urlParameter("type", type)
        .get()
        .go();
  }

  /**
   * Retrieves the login report between the two instants. If you specify an application Id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application Id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveLoginReport(UUID applicationId, long start, long end) {
    return start(LoginReportResponse.class, Errors.class)
        .uri("/api/report/login")
        .urlParameter("applicationId", applicationId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .get()
        .go();
  }

  /**
   * Retrieves the message template for the given Id. If you don't specify the Id, this will return all the message templates.
   *
   * @param messageTemplateId (Optional) The Id of the message template.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessageTemplateResponse, Void> retrieveMessageTemplate(UUID messageTemplateId) {
    return start(MessageTemplateResponse.class, Void.TYPE)
        .uri("/api/message/template")
        .urlSegment(messageTemplateId)
        .get()
        .go();
  }

  /**
   * Creates a preview of the message template provided in the request, normalized to a given locale.
   *
   * @param request The request that contains the email template and optionally a locale to render it in.
   * @return The ClientResponse object.
   */
  public ClientResponse<PreviewMessageTemplateResponse, Errors> retrieveMessageTemplatePreview(PreviewMessageTemplateRequest request) {
    return start(PreviewMessageTemplateResponse.class, Errors.class)
        .uri("/api/message/template/preview")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves all the message templates.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<MessageTemplateResponse, Void> retrieveMessageTemplates() {
    return start(MessageTemplateResponse.class, Void.TYPE)
        .uri("/api/message/template")
        .get()
        .go();
  }

  /**
   * Retrieves the messenger with the given Id.
   *
   * @param messengerId The Id of the messenger.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessengerResponse, Void> retrieveMessenger(UUID messengerId) {
    return start(MessengerResponse.class, Void.TYPE)
        .uri("/api/messenger")
        .urlSegment(messengerId)
        .get()
        .go();
  }

  /**
   * Retrieves all the messengers.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<MessengerResponse, Void> retrieveMessengers() {
    return start(MessengerResponse.class, Void.TYPE)
        .uri("/api/messenger")
        .get()
        .go();
  }

  /**
   * Retrieves the monthly active user report between the two instants. If you specify an application Id, it will only
   * return the monthly active counts for that application.
   *
   * @param applicationId (Optional) The application Id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<MonthlyActiveUserReportResponse, Errors> retrieveMonthlyActiveReport(UUID applicationId, long start, long end) {
    return start(MonthlyActiveUserReportResponse.class, Errors.class)
        .uri("/api/report/monthly-active-user")
        .urlParameter("applicationId", applicationId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .get()
        .go();
  }

  /**
   * Retrieves a custom OAuth scope.
   *
   * @param applicationId The Id of the application that the OAuth scope belongs to.
   * @param scopeId The Id of the OAuth scope to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationOAuthScopeResponse, Errors> retrieveOAuthScope(UUID applicationId, UUID scopeId) {
    return start(ApplicationOAuthScopeResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("scope")
        .urlSegment(scopeId)
        .get()
        .go();
  }

  /**
   * Retrieves the Oauth2 configuration for the application for the given Application Id.
   *
   * @param applicationId The Id of the Application to retrieve OAuth configuration.
   * @return The ClientResponse object.
   */
  public ClientResponse<OAuthConfigurationResponse, Errors> retrieveOauthConfiguration(UUID applicationId) {
    return start(OAuthConfigurationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("oauth-configuration")
        .get()
        .go();
  }

  /**
   * Returns the well known OpenID Configuration JSON document
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<OpenIdConfiguration, Void> retrieveOpenIdConfiguration() {
    return startAnonymous(OpenIdConfiguration.class, Void.TYPE)
        .uri("/.well-known/openid-configuration")
        .get()
        .go();
  }

  /**
   * Retrieves the password validation rules for a specific tenant. This method requires a tenantId to be provided 
   * through the use of a Tenant scoped API key or an HTTP header X-FusionAuth-TenantId to specify the Tenant Id.
   * <p>
   * This API does not require an API key.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<PasswordValidationRulesResponse, Void> retrievePasswordValidationRules() {
    return startAnonymous(PasswordValidationRulesResponse.class, Void.TYPE)
        .uri("/api/tenant/password-validation-rules")
        .get()
        .go();
  }

  /**
   * Retrieves the password validation rules for a specific tenant.
   * <p>
   * This API does not require an API key.
   *
   * @param tenantId The Id of the tenant.
   * @return The ClientResponse object.
   */
  public ClientResponse<PasswordValidationRulesResponse, Void> retrievePasswordValidationRulesWithTenantId(UUID tenantId) {
    return startAnonymous(PasswordValidationRulesResponse.class, Void.TYPE)
        .uri("/api/tenant/password-validation-rules")
        .urlSegment(tenantId)
        .get()
        .go();
  }

  /**
   * Retrieves all the children for the given parent email address.
   *
   * @param parentEmail The email of the parent.
   * @return The ClientResponse object.
   */
  public ClientResponse<PendingResponse, Errors> retrievePendingChildren(String parentEmail) {
    return start(PendingResponse.class, Errors.class)
        .uri("/api/user/family/pending")
        .urlParameter("parentEmail", parentEmail)
        .get()
        .go();
  }

  /**
   * Retrieve a pending identity provider link. This is useful to validate a pending link and retrieve meta-data about the identity provider link.
   *
   * @param pendingLinkId The pending link Id.
   * @param userId The optional userId. When provided additional meta-data will be provided to identify how many links if any the user already has.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderPendingLinkResponse, Errors> retrievePendingLink(String pendingLinkId, UUID userId) {
    return start(IdentityProviderPendingLinkResponse.class, Errors.class)
        .uri("/api/identity-provider/link/pending")
        .urlSegment(pendingLinkId)
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves the FusionAuth Reactor metrics.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ReactorMetricsResponse, Void> retrieveReactorMetrics() {
    return start(ReactorMetricsResponse.class, Void.TYPE)
        .uri("/api/reactor/metrics")
        .get()
        .go();
  }

  /**
   * Retrieves the FusionAuth Reactor status.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ReactorResponse, Void> retrieveReactorStatus() {
    return start(ReactorResponse.class, Void.TYPE)
        .uri("/api/reactor")
        .get()
        .go();
  }

  /**
   * Retrieves the last number of login records.
   *
   * @param offset The initial record. e.g. 0 is the last login, 100 will be the 100th most recent login.
   * @param limit (Optional, defaults to 10) The number of records to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<RecentLoginResponse, Errors> retrieveRecentLogins(int offset, Integer limit) {
    return start(RecentLoginResponse.class, Errors.class)
        .uri("/api/user/recent-login")
        .urlParameter("offset", offset)
        .urlParameter("limit", limit)
        .get()
        .go();
  }

  /**
   * Retrieves a single refresh token by unique Id. This is not the same thing as the string value of the refresh token. If you have that, you already have what you need.
   *
   * @param tokenId The Id of the token.
   * @return The ClientResponse object.
   */
  public ClientResponse<RefreshTokenResponse, Errors> retrieveRefreshTokenById(UUID tokenId) {
    return start(RefreshTokenResponse.class, Errors.class)
        .uri("/api/jwt/refresh")
        .urlSegment(tokenId)
        .get()
        .go();
  }

  /**
   * Retrieves the refresh tokens that belong to the user with the given Id.
   *
   * @param userId The Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<RefreshTokenResponse, Errors> retrieveRefreshTokens(UUID userId) {
    return start(RefreshTokenResponse.class, Errors.class)
        .uri("/api/jwt/refresh")
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves the user registration for the user with the given Id and the given application Id.
   *
   * @param userId The Id of the user.
   * @param applicationId The Id of the application.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> retrieveRegistration(UUID userId, UUID applicationId) {
    return start(RegistrationResponse.class, Errors.class)
        .uri("/api/user/registration")
        .urlSegment(userId)
        .urlSegment(applicationId)
        .get()
        .go();
  }

  /**
   * Retrieves the registration report between the two instants. If you specify an application Id, it will only return
   * the registration counts for that application.
   *
   * @param applicationId (Optional) The application Id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationReportResponse, Errors> retrieveRegistrationReport(UUID applicationId, long start, long end) {
    return start(RegistrationReportResponse.class, Errors.class)
        .uri("/api/report/registration")
        .urlParameter("applicationId", applicationId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .get()
        .go();
  }

  /**
   * Retrieve the status of a re-index process. A status code of 200 indicates the re-index is in progress, a status code of  
   * 404 indicates no re-index is in progress.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> retrieveReindexStatus() {
    return start(Void.TYPE, Errors.class)
        .uri("/api/system/reindex")
        .get()
        .go();
  }

  /**
   * Retrieves the system configuration.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<SystemConfigurationResponse, Void> retrieveSystemConfiguration() {
    return start(SystemConfigurationResponse.class, Void.TYPE)
        .uri("/api/system-configuration")
        .get()
        .go();
  }

  /**
   * Retrieves the FusionAuth system health. This API will return 200 if the system is healthy, and 500 if the system is un-healthy.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> retrieveSystemHealth() {
    return startAnonymous(Void.TYPE, Void.TYPE)
        .uri("/api/health")
        .get()
        .go();
  }

  /**
   * Retrieves the FusionAuth system status. This request is anonymous and does not require an API key. When an API key is not provided the response will contain a single value in the JSON response indicating the current health check.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<StatusResponse, Void> retrieveSystemStatus() {
    return startAnonymous(StatusResponse.class, Void.TYPE)
        .uri("/api/status")
        .get()
        .go();
  }

  /**
   * Retrieves the FusionAuth system status using an API key. Using an API key will cause the response to include the product version, health checks and various runtime metrics.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<StatusResponse, Void> retrieveSystemStatusUsingAPIKey() {
    return start(StatusResponse.class, Void.TYPE)
        .uri("/api/status")
        .get()
        .go();
  }

  /**
   * Retrieves the tenant for the given Id.
   *
   * @param tenantId The Id of the tenant.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> retrieveTenant(UUID tenantId) {
    return start(TenantResponse.class, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .get()
        .go();
  }

  /**
   * Retrieves all the tenants.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Void> retrieveTenants() {
    return start(TenantResponse.class, Void.TYPE)
        .uri("/api/tenant")
        .get()
        .go();
  }

  /**
   * Retrieves the theme for the given Id.
   *
   * @param themeId The Id of the theme.
   * @return The ClientResponse object.
   */
  public ClientResponse<ThemeResponse, Errors> retrieveTheme(UUID themeId) {
    return start(ThemeResponse.class, Errors.class)
        .uri("/api/theme")
        .urlSegment(themeId)
        .get()
        .go();
  }

  /**
   * Retrieves all the themes.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ThemeResponse, Void> retrieveThemes() {
    return start(ThemeResponse.class, Void.TYPE)
        .uri("/api/theme")
        .get()
        .go();
  }

  /**
   * Retrieves the totals report. This contains all the total counts for each application and the global registration
   * count.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<TotalsReportResponse, Void> retrieveTotalReport() {
    return start(TotalsReportResponse.class, Void.TYPE)
        .uri("/api/report/totals")
        .get()
        .go();
  }

  /**
   * Retrieves the totals report. This allows excluding applicationTotals from the report. An empty list will include the applicationTotals.
   *
   * @param excludes List of fields to exclude in the response. Currently only allows applicationTotals.
   * @return The ClientResponse object.
   */
  public ClientResponse<TotalsReportResponse, Void> retrieveTotalReportWithExcludes(List<String> excludes) {
    return start(TotalsReportResponse.class, Void.TYPE)
        .uri("/api/report/totals")
        .urlParameter("excludes", excludes)
        .get()
        .go();
  }

  /**
   * Retrieve two-factor recovery codes for a user.
   *
   * @param userId The Id of the user to retrieve Two Factor recovery codes.
   * @return The ClientResponse object.
   */
  public ClientResponse<TwoFactorRecoveryCodeResponse, Errors> retrieveTwoFactorRecoveryCodes(UUID userId) {
    return start(TwoFactorRecoveryCodeResponse.class, Errors.class)
        .uri("/api/user/two-factor/recovery-code")
        .urlSegment(userId)
        .get()
        .go();
  }

  /**
   * Retrieve a user's two-factor status.
   * <p>
   * This can be used to see if a user will need to complete a two-factor challenge to complete a login,
   * and optionally identify the state of the two-factor trust across various applications.
   *
   * @param userId The user Id to retrieve the Two-Factor status.
   * @param applicationId The optional applicationId to verify.
   * @param twoFactorTrustId The optional two-factor trust Id to verify.
   * @return The ClientResponse object.
   */
  public ClientResponse<TwoFactorStatusResponse, Errors> retrieveTwoFactorStatus(UUID userId, UUID applicationId, String twoFactorTrustId) {
    return start(TwoFactorStatusResponse.class, Errors.class)
        .uri("/api/two-factor/status")
        .urlParameter("userId", userId)
        .urlParameter("applicationId", applicationId)
        .urlSegment(twoFactorTrustId)
        .get()
        .go();
  }

  /**
   * Retrieve a user's two-factor status.
   * <p>
   * This can be used to see if a user will need to complete a two-factor challenge to complete a login,
   * and optionally identify the state of the two-factor trust across various applications. This operation
   * provides more payload options than retrieveTwoFactorStatus.
   *
   * @param request The request object that contains all the information used to check the status.
   * @return The ClientResponse object.
   */
  public ClientResponse<TwoFactorStatusResponse, Errors> retrieveTwoFactorStatusUsing(TwoFactorStatusRequest request) {
    return start(TwoFactorStatusResponse.class, Errors.class)
        .uri("/api/two-factor/status")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves the user for the given Id.
   *
   * @param userId The Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUser(UUID userId) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .get()
        .go();
  }

  /**
   * Retrieves the user action for the given Id. If you pass in null for the Id, this will return all the user
   * actions.
   *
   * @param userActionId (Optional) The Id of the user action.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Void> retrieveUserAction(UUID userActionId) {
    return start(UserActionResponse.class, Void.TYPE)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .get()
        .go();
  }

  /**
   * Retrieves the user action reason for the given Id. If you pass in null for the Id, this will return all the user
   * action reasons.
   *
   * @param userActionReasonId (Optional) The Id of the user action reason.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Void> retrieveUserActionReason(UUID userActionReasonId) {
    return start(UserActionReasonResponse.class, Void.TYPE)
        .uri("/api/user-action-reason")
        .urlSegment(userActionReasonId)
        .get()
        .go();
  }

  /**
   * Retrieves all the user action reasons.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Void> retrieveUserActionReasons() {
    return start(UserActionReasonResponse.class, Void.TYPE)
        .uri("/api/user-action-reason")
        .get()
        .go();
  }

  /**
   * Retrieves all the user actions.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Void> retrieveUserActions() {
    return start(UserActionResponse.class, Void.TYPE)
        .uri("/api/user-action")
        .get()
        .go();
  }

  /**
   * Retrieves the user by a change password Id. The intended use of this API is to retrieve a user after the forgot
   * password workflow has been initiated and you may not know the user's email or username.
   *
   * @param changePasswordId The unique change password Id that was sent via email or returned by the Forgot Password API.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByChangePasswordId(String changePasswordId) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlParameter("changePasswordId", changePasswordId)
        .get()
        .go();
  }

  /**
   * Retrieves the user for the given email.
   *
   * @param email The email of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByEmail(String email) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlParameter("email", email)
        .get()
        .go();
  }

  /**
   * Retrieves the user for the loginId. The loginId can be either the username or the email.
   *
   * @param loginId The email or username of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByLoginId(String loginId) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlParameter("loginId", loginId)
        .get()
        .go();
  }

  /**
   * Retrieves the user for the loginId, using specific loginIdTypes.
   *
   * @param loginId The email or username of the user.
   * @param loginIdTypes The identity types that FusionAuth will compare the loginId to.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByLoginIdWithLoginIdTypes(String loginId, List<String> loginIdTypes) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlParameter("loginId", loginId)
        .urlParameter("loginIdTypes", loginIdTypes)
        .get()
        .go();
  }

  /**
   * Retrieves the user for the given username.
   *
   * @param username The username of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByUsername(String username) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlParameter("username", username)
        .get()
        .go();
  }

  /**
   * Retrieves the user by a verificationId. The intended use of this API is to retrieve a user after the forgot
   * password workflow has been initiated and you may not know the user's email or username.
   *
   * @param verificationId The unique verification Id that has been set on the user object.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByVerificationId(String verificationId) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlParameter("verificationId", verificationId)
        .get()
        .go();
  }

  /**
   * Retrieve a user_code that is part of an in-progress Device Authorization Grant.
   * <p>
   * This API is useful if you want to build your own login workflow to complete a device grant.
   *
   * @param client_id The client Id.
   * @param client_secret The client Id.
   * @param user_code The end-user verification code.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> retrieveUserCode(String client_id, String client_secret, String user_code) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("client_id", Arrays.asList(client_id));
    parameters.put("client_secret", Arrays.asList(client_secret));
    parameters.put("user_code", Arrays.asList(user_code));
    return startAnonymous(Void.TYPE, Void.TYPE)
        .uri("/oauth2/device/user-code")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .get()
        .go();
  }

  /**
   * Retrieve a user_code that is part of an in-progress Device Authorization Grant.
   * <p>
   * This API is useful if you want to build your own login workflow to complete a device grant.
   * <p>
   * This request will require an API key.
   *
   * @param user_code The end-user verification code.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> retrieveUserCodeUsingAPIKey(String user_code) {
    Map<String, List<String>> parameters = new HashMap<>();
    parameters.put("user_code", Arrays.asList(user_code));
    return startAnonymous(Void.TYPE, Void.TYPE)
        .uri("/oauth2/device/user-code")
        .bodyHandler(new FormDataBodyHandler(parameters))
        .get()
        .go();
  }

  /**
   * Retrieves all the comments for the user with the given Id.
   *
   * @param userId The Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserCommentResponse, Errors> retrieveUserComments(UUID userId) {
    return start(UserCommentResponse.class, Errors.class)
        .uri("/api/user/comment")
        .urlSegment(userId)
        .get()
        .go();
  }

  /**
   * Retrieve a single User consent by Id.
   *
   * @param userConsentId The User consent Id
   * @return The ClientResponse object.
   */
  public ClientResponse<UserConsentResponse, Void> retrieveUserConsent(UUID userConsentId) {
    return start(UserConsentResponse.class, Void.TYPE)
        .uri("/api/user/consent")
        .urlSegment(userConsentId)
        .get()
        .go();
  }

  /**
   * Retrieves all the consents for a User.
   *
   * @param userId The User's Id
   * @return The ClientResponse object.
   */
  public ClientResponse<UserConsentResponse, Void> retrieveUserConsents(UUID userId) {
    return start(UserConsentResponse.class, Void.TYPE)
        .uri("/api/user/consent")
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Call the UserInfo endpoint to retrieve User Claims from the access token issued by FusionAuth.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<UserinfoResponse, OAuthError> retrieveUserInfoFromAccessToken(String encodedJWT) {
    return startAnonymous(UserinfoResponse.class, OAuthError.class)
        .uri("/oauth2/userinfo")
        .authorization("Bearer " + encodedJWT)
        .get()
        .go();
  }

  /**
   * Retrieve a single Identity Provider user (link).
   *
   * @param identityProviderId The unique Id of the identity provider.
   * @param identityProviderUserId The unique Id of the user in the 3rd party identity provider.
   * @param userId The unique Id of the FusionAuth user.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderLinkResponse, Errors> retrieveUserLink(UUID identityProviderId, String identityProviderUserId, UUID userId) {
    return start(IdentityProviderLinkResponse.class, Errors.class)
        .uri("/api/identity-provider/link")
        .urlParameter("identityProviderId", identityProviderId)
        .urlParameter("identityProviderUserId", identityProviderUserId)
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieve all Identity Provider users (links) for the user. Specify the optional identityProviderId to retrieve links for a particular IdP.
   *
   * @param identityProviderId (Optional) The unique Id of the identity provider. Specify this value to reduce the links returned to those for a particular IdP.
   * @param userId The unique Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderLinkResponse, Errors> retrieveUserLinksByUserId(UUID identityProviderId, UUID userId) {
    return start(IdentityProviderLinkResponse.class, Errors.class)
        .uri("/api/identity-provider/link")
        .urlParameter("identityProviderId", identityProviderId)
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves the login report between the two instants for a particular user by Id. If you specify an application Id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application Id.
   * @param userId The userId Id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveUserLoginReport(UUID applicationId, UUID userId, long start, long end) {
    return start(LoginReportResponse.class, Errors.class)
        .uri("/api/report/login")
        .urlParameter("applicationId", applicationId)
        .urlParameter("userId", userId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .get()
        .go();
  }

  /**
   * Retrieves the login report between the two instants for a particular user by login Id. If you specify an application Id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application Id.
   * @param loginId The userId Id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveUserLoginReportByLoginId(UUID applicationId, String loginId, long start, long end) {
    return start(LoginReportResponse.class, Errors.class)
        .uri("/api/report/login")
        .urlParameter("applicationId", applicationId)
        .urlParameter("loginId", loginId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .get()
        .go();
  }

  /**
   * Retrieves the login report between the two instants for a particular user by login Id, using specific loginIdTypes. If you specify an application id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param loginId The userId id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @param loginIdTypes The identity types that FusionAuth will compare the loginId to.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveUserLoginReportByLoginIdAndLoginIdTypes(UUID applicationId, String loginId, long start, long end, List<String> loginIdTypes) {
    return start(LoginReportResponse.class, Errors.class)
        .uri("/api/report/login")
        .urlParameter("applicationId", applicationId)
        .urlParameter("loginId", loginId)
        .urlParameter("start", start)
        .urlParameter("end", end)
        .urlParameter("loginIdTypes", loginIdTypes)
        .get()
        .go();
  }

  /**
   * Retrieves the last number of login records for a user.
   *
   * @param userId The Id of the user.
   * @param offset The initial record. e.g. 0 is the last login, 100 will be the 100th most recent login.
   * @param limit (Optional, defaults to 10) The number of records to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<RecentLoginResponse, Errors> retrieveUserRecentLogins(UUID userId, int offset, Integer limit) {
    return start(RecentLoginResponse.class, Errors.class)
        .uri("/api/user/recent-login")
        .urlParameter("userId", userId)
        .urlParameter("offset", offset)
        .urlParameter("limit", limit)
        .get()
        .go();
  }

  /**
   * Retrieves the user for the given Id. This method does not use an API key, instead it uses a JSON Web Token (JWT) for authentication.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserUsingJWT(String encodedJWT) {
    return startAnonymous(UserResponse.class, Errors.class)
        .uri("/api/user")
        .authorization("Bearer " + encodedJWT)
        .get()
        .go();
  }

  /**
   * Retrieves the FusionAuth version string.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<VersionResponse, Errors> retrieveVersion() {
    return start(VersionResponse.class, Errors.class)
        .uri("/api/system/version")
        .get()
        .go();
  }

  /**
   * Retrieves the WebAuthn credential for the given Id.
   *
   * @param id The Id of the WebAuthn credential.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebAuthnCredentialResponse, Errors> retrieveWebAuthnCredential(UUID id) {
    return start(WebAuthnCredentialResponse.class, Errors.class)
        .uri("/api/webauthn")
        .urlSegment(id)
        .get()
        .go();
  }

  /**
   * Retrieves all WebAuthn credentials for the given user.
   *
   * @param userId The user's ID.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebAuthnCredentialResponse, Errors> retrieveWebAuthnCredentialsForUser(UUID userId) {
    return start(WebAuthnCredentialResponse.class, Errors.class)
        .uri("/api/webauthn")
        .urlParameter("userId", userId)
        .get()
        .go();
  }

  /**
   * Retrieves the webhook for the given Id. If you pass in null for the Id, this will return all the webhooks.
   *
   * @param webhookId (Optional) The Id of the webhook.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Void> retrieveWebhook(UUID webhookId) {
    return start(WebhookResponse.class, Void.TYPE)
        .uri("/api/webhook")
        .urlSegment(webhookId)
        .get()
        .go();
  }

  /**
   * Retrieves a single webhook attempt log for the given Id.
   *
   * @param webhookAttemptLogId The Id of the webhook attempt log to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookAttemptLogResponse, Errors> retrieveWebhookAttemptLog(UUID webhookAttemptLogId) {
    return start(WebhookAttemptLogResponse.class, Errors.class)
        .uri("/api/system/webhook-attempt-log")
        .urlSegment(webhookAttemptLogId)
        .get()
        .go();
  }

  /**
   * Retrieves a single webhook event log for the given Id.
   *
   * @param webhookEventLogId The Id of the webhook event log to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookEventLogResponse, Errors> retrieveWebhookEventLog(UUID webhookEventLogId) {
    return start(WebhookEventLogResponse.class, Errors.class)
        .uri("/api/system/webhook-event-log")
        .urlSegment(webhookEventLogId)
        .get()
        .go();
  }

  /**
   * Retrieves all the webhooks.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Void> retrieveWebhooks() {
    return start(WebhookResponse.class, Void.TYPE)
        .uri("/api/webhook")
        .get()
        .go();
  }

  /**
   * Revokes refresh tokens.
   * <p>
   * Usage examples:
   *   - Delete a single refresh token, pass in only the token.
   *       revokeRefreshToken(token)
   * <p>
   *   - Delete all refresh tokens for a user, pass in only the userId.
   *       revokeRefreshToken(null, userId)
   * <p>
   *   - Delete all refresh tokens for a user for a specific application, pass in both the userId and the applicationId.
   *       revokeRefreshToken(null, userId, applicationId)
   * <p>
   *   - Delete all refresh tokens for an application
   *       revokeRefreshToken(null, null, applicationId)
   * <p>
   * Note: <code>null</code> may be handled differently depending upon the programming language.
   * <p>
   * See also: (method names may vary by language... but you'll figure it out)
   * <p>
   *  - revokeRefreshTokenById
   *  - revokeRefreshTokenByToken
   *  - revokeRefreshTokensByUserId
   *  - revokeRefreshTokensByApplicationId
   *  - revokeRefreshTokensByUserIdForApplication
   *
   * @param token (Optional) The refresh token to delete.
   * @param userId (Optional) The user Id whose tokens to delete.
   * @param applicationId (Optional) The application Id of the tokens to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshToken(String token, UUID userId, UUID applicationId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .urlParameter("token", token)
        .urlParameter("userId", userId)
        .urlParameter("applicationId", applicationId)
        .delete()
        .go();
  }

  /**
   * Revokes a single refresh token by the unique Id. The unique Id is not sensitive as it cannot be used to obtain another JWT.
   *
   * @param tokenId The unique Id of the token to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshTokenById(UUID tokenId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .urlSegment(tokenId)
        .delete()
        .go();
  }

  /**
   * Revokes a single refresh token by using the actual refresh token value. This refresh token value is sensitive, so  be careful with this API request.
   *
   * @param token The refresh token to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshTokenByToken(String token) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .urlParameter("token", token)
        .delete()
        .go();
  }

  /**
   * Revoke all refresh tokens that belong to an application by applicationId.
   *
   * @param applicationId The unique Id of the application that you want to delete all refresh tokens for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshTokensByApplicationId(UUID applicationId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .urlParameter("applicationId", applicationId)
        .delete()
        .go();
  }

  /**
   * Revoke all refresh tokens that belong to a user by user Id.
   *
   * @param userId The unique Id of the user that you want to delete all refresh tokens for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshTokensByUserId(UUID userId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .urlParameter("userId", userId)
        .delete()
        .go();
  }

  /**
   * Revoke all refresh tokens that belong to a user by user Id for a specific application by applicationId.
   *
   * @param userId The unique Id of the user that you want to delete all refresh tokens for.
   * @param applicationId The unique Id of the application that you want to delete refresh tokens for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshTokensByUserIdForApplication(UUID userId, UUID applicationId) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .urlParameter("userId", userId)
        .urlParameter("applicationId", applicationId)
        .delete()
        .go();
  }

  /**
   * Revokes refresh tokens using the information in the JSON body. The handling for this method is the same as the revokeRefreshToken method
   * and is based on the information you provide in the RefreshDeleteRequest object. See that method for additional information.
   *
   * @param request The request information used to revoke the refresh tokens.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshTokensWithRequest(RefreshTokenRevokeRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/jwt/refresh")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .delete()
        .go();
  }

  /**
   * Revokes a single User consent by Id.
   *
   * @param userConsentId The User Consent Id
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> revokeUserConsent(UUID userConsentId) {
    return start(Void.TYPE, Void.TYPE)
        .uri("/api/user/consent")
        .urlSegment(userConsentId)
        .delete()
        .go();
  }

  /**
   * Searches applications with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationSearchResponse, Errors> searchApplications(ApplicationSearchRequest request) {
    return start(ApplicationSearchResponse.class, Errors.class)
        .uri("/api/application/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches the audit logs with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<AuditLogSearchResponse, Errors> searchAuditLogs(AuditLogSearchRequest request) {
    return start(AuditLogSearchResponse.class, Errors.class)
        .uri("/api/system/audit-log/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches consents with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConsentSearchResponse, Errors> searchConsents(ConsentSearchRequest request) {
    return start(ConsentSearchResponse.class, Errors.class)
        .uri("/api/consent/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches email templates with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateSearchResponse, Errors> searchEmailTemplates(EmailTemplateSearchRequest request) {
    return start(EmailTemplateSearchResponse.class, Errors.class)
        .uri("/api/email/template/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches entities with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntitySearchResponse, Errors> searchEntities(EntitySearchRequest request) {
    return start(EntitySearchResponse.class, Errors.class)
        .uri("/api/entity/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves the entities for the given Ids. If any Id is invalid, it is ignored.
   *
   * @param ids The entity ids to search for.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntitySearchResponse, Errors> searchEntitiesByIds(Collection<UUID> ids) {
    return start(EntitySearchResponse.class, Errors.class)
        .uri("/api/entity/search")
        .urlParameter("ids", ids)
        .get()
        .go();
  }

  /**
   * Searches Entity Grants with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityGrantSearchResponse, Errors> searchEntityGrants(EntityGrantSearchRequest request) {
    return start(EntityGrantSearchResponse.class, Errors.class)
        .uri("/api/entity/grant/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches the entity types with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeSearchResponse, Errors> searchEntityTypes(EntityTypeSearchRequest request) {
    return start(EntityTypeSearchResponse.class, Errors.class)
        .uri("/api/entity/type/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches the event logs with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EventLogSearchResponse, Errors> searchEventLogs(EventLogSearchRequest request) {
    return start(EventLogSearchResponse.class, Errors.class)
        .uri("/api/system/event-log/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches group members with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupMemberSearchResponse, Errors> searchGroupMembers(GroupMemberSearchRequest request) {
    return start(GroupMemberSearchResponse.class, Errors.class)
        .uri("/api/group/member/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches groups with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupSearchResponse, Errors> searchGroups(GroupSearchRequest request) {
    return start(GroupSearchResponse.class, Errors.class)
        .uri("/api/group/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches the IP Access Control Lists with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IPAccessControlListSearchResponse, Errors> searchIPAccessControlLists(IPAccessControlListSearchRequest request) {
    return start(IPAccessControlListSearchResponse.class, Errors.class)
        .uri("/api/ip-acl/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches identity providers with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderSearchResponse, Errors> searchIdentityProviders(IdentityProviderSearchRequest request) {
    return start(IdentityProviderSearchResponse.class, Errors.class)
        .uri("/api/identity-provider/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches keys with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<KeySearchResponse, Errors> searchKeys(KeySearchRequest request) {
    return start(KeySearchResponse.class, Errors.class)
        .uri("/api/key/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches lambdas with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaSearchResponse, Errors> searchLambdas(LambdaSearchRequest request) {
    return start(LambdaSearchResponse.class, Errors.class)
        .uri("/api/lambda/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches the login records with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginRecordSearchResponse, Errors> searchLoginRecords(LoginRecordSearchRequest request) {
    return start(LoginRecordSearchResponse.class, Errors.class)
        .uri("/api/system/login-record/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches tenants with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantSearchResponse, Errors> searchTenants(TenantSearchRequest request) {
    return start(TenantSearchResponse.class, Errors.class)
        .uri("/api/tenant/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches themes with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ThemeSearchResponse, Errors> searchThemes(ThemeSearchRequest request) {
    return start(ThemeSearchResponse.class, Errors.class)
        .uri("/api/theme/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches user comments with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserCommentSearchResponse, Errors> searchUserComments(UserCommentSearchRequest request) {
    return start(UserCommentSearchResponse.class, Errors.class)
        .uri("/api/user/comment/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves the users for the given Ids. If any Id is invalid, it is ignored.
   *
   * @param ids The user ids to search for.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to searchUsersByIds, use that method instead.
   */
  @Deprecated
  public ClientResponse<SearchResponse, Errors> searchUsers(Collection<UUID> ids) {
    return start(SearchResponse.class, Errors.class)
        .uri("/api/user/search")
        .urlParameter("ids", ids)
        .get()
        .go();
  }

  /**
   * Retrieves the users for the given Ids. If any Id is invalid, it is ignored.
   *
   * @param ids The user Ids to search for.
   * @return The ClientResponse object.
   */
  public ClientResponse<SearchResponse, Errors> searchUsersByIds(Collection<UUID> ids) {
    return start(SearchResponse.class, Errors.class)
        .uri("/api/user/search")
        .urlParameter("ids", ids)
        .get()
        .go();
  }

  /**
   * Retrieves the users for the given search criteria and pagination.
   *
   * @param request The search criteria and pagination constraints. Fields used: ids, query, queryString, numberOfResults, orderBy, startRow,
   *     and sortFields.
   * @return The ClientResponse object.
   */
  public ClientResponse<SearchResponse, Errors> searchUsersByQuery(SearchRequest request) {
    return start(SearchResponse.class, Errors.class)
        .uri("/api/user/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Retrieves the users for the given search criteria and pagination.
   *
   * @param request The search criteria and pagination constraints. Fields used: ids, query, queryString, numberOfResults, orderBy, startRow,
   *     and sortFields.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to searchUsersByQuery, use that method instead.
   */
  @Deprecated
  public ClientResponse<SearchResponse, Errors> searchUsersByQueryString(SearchRequest request) {
    return start(SearchResponse.class, Errors.class)
        .uri("/api/user/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches the webhook event logs with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookEventLogSearchResponse, Errors> searchWebhookEventLogs(WebhookEventLogSearchRequest request) {
    return start(WebhookEventLogSearchResponse.class, Errors.class)
        .uri("/api/system/webhook-event-log/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Searches webhooks with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookSearchResponse, Errors> searchWebhooks(WebhookSearchRequest request) {
    return start(WebhookSearchResponse.class, Errors.class)
        .uri("/api/webhook/search")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Send an email using an email template Id. You can optionally provide <code>requestData</code> to access key value
   * pairs in the email template.
   *
   * @param emailTemplateId The Id for the template.
   * @param request The send email request that contains all the information used to send the email.
   * @return The ClientResponse object.
   */
  public ClientResponse<SendResponse, Errors> sendEmail(UUID emailTemplateId, SendRequest request) {
    return start(SendResponse.class, Errors.class)
        .uri("/api/email/send")
        .urlSegment(emailTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Sends out an email to a parent that they need to register and create a family or need to log in and add a child to their existing family.
   *
   * @param request The request object that contains the parent email.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendFamilyRequestEmail(FamilyEmailRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/family/request")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Send a passwordless authentication code in an email to complete login.
   *
   * @param request The passwordless send request that contains all the information used to send an email containing a code.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendPasswordlessCode(PasswordlessSendRequest request) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/passwordless/send")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Send a Two Factor authentication code to assist in setting up Two Factor authentication or disabling.
   *
   * @param request The request object that contains all the information used to send the code.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to sendTwoFactorCodeForEnableDisable, use that method instead.
   */
  @Deprecated
  public ClientResponse<Void, Errors> sendTwoFactorCode(TwoFactorSendRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/two-factor/send")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Send a Two Factor authentication code to assist in setting up Two Factor authentication or disabling.
   *
   * @param request The request object that contains all the information used to send the code.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendTwoFactorCodeForEnableDisable(TwoFactorSendRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/two-factor/send")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Send a Two Factor authentication code to allow the completion of Two Factor authentication.
   *
   * @param twoFactorId The Id returned by the Login API necessary to complete Two Factor authentication.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to sendTwoFactorCodeForLoginUsingMethod, use that method instead.
   */
  @Deprecated
  public ClientResponse<Void, Errors> sendTwoFactorCodeForLogin(String twoFactorId) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/two-factor/send")
        .urlSegment(twoFactorId)
        .post()
        .go();
  }

  /**
   * Send a Two Factor authentication code to allow the completion of Two Factor authentication.
   *
   * @param twoFactorId The Id returned by the Login API necessary to complete Two Factor authentication.
   * @param request The Two Factor send request that contains all the information used to send the Two Factor code to the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendTwoFactorCodeForLoginUsingMethod(String twoFactorId, TwoFactorSendRequest request) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/two-factor/send")
        .urlSegment(twoFactorId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Send a verification code using the appropriate transport for the identity type being verified.
   *
   * @param request The identity verify send request that contains all the information used send the code.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendVerifyIdentity(VerifySendRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/identity/verify/send")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Begins a login request for a 3rd party login that requires user interaction such as HYPR.
   *
   * @param request The third-party login request that contains information from the third-party login
   *     providers that FusionAuth uses to reconcile the user's account.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderStartLoginResponse, Errors> startIdentityProviderLogin(IdentityProviderStartLoginRequest request) {
    return start(IdentityProviderStartLoginResponse.class, Errors.class)
        .uri("/api/identity-provider/start")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Start a passwordless login request by generating a passwordless code. This code can be sent to the User using the Send
   * Passwordless Code API or using a mechanism outside of FusionAuth. The passwordless login is completed by using the Passwordless Login API with this code.
   *
   * @param request The passwordless start request that contains all the information used to begin the passwordless login request.
   * @return The ClientResponse object.
   */
  public ClientResponse<PasswordlessStartResponse, Errors> startPasswordlessLogin(PasswordlessStartRequest request) {
    return start(PasswordlessStartResponse.class, Errors.class)
        .uri("/api/passwordless/start")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Start a Two-Factor login request by generating a two-factor identifier. This code can then be sent to the Two Factor Send 
   * API (/api/two-factor/send)in order to send a one-time use code to a user. You can also use one-time use code returned 
   * to send the code out-of-band. The Two-Factor login is completed by making a request to the Two-Factor Login 
   * API (/api/two-factor/login). with the two-factor identifier and the one-time use code.
   * <p>
   * This API is intended to allow you to begin a Two-Factor login outside a normal login that originated from the Login API (/api/login).
   *
   * @param request The Two-Factor start request that contains all the information used to begin the Two-Factor login request.
   * @return The ClientResponse object.
   */
  public ClientResponse<TwoFactorStartResponse, Errors> startTwoFactorLogin(TwoFactorStartRequest request) {
    return start(TwoFactorStartResponse.class, Errors.class)
        .uri("/api/two-factor/start")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Start a verification of an identity by generating a code. This code can be sent to the User using the Verify Send API
   * Verification Code API or using a mechanism outside of FusionAuth. The verification is completed by using the Verify Complete API with this code.
   *
   * @param request The identity verify start request that contains all the information used to begin the request.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyStartResponse, Errors> startVerifyIdentity(VerifyStartRequest request) {
    return start(VerifyStartResponse.class, Errors.class)
        .uri("/api/identity/verify/start")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Start a WebAuthn authentication ceremony by generating a new challenge for the user
   *
   * @param request An object containing data necessary for starting the authentication ceremony
   * @return The ClientResponse object.
   */
  public ClientResponse<WebAuthnStartResponse, Errors> startWebAuthnLogin(WebAuthnStartRequest request) {
    return start(WebAuthnStartResponse.class, Errors.class)
        .uri("/api/webauthn/start")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Start a WebAuthn registration ceremony by generating a new challenge for the user
   *
   * @param request An object containing data necessary for starting the registration ceremony
   * @return The ClientResponse object.
   */
  public ClientResponse<WebAuthnRegisterStartResponse, Errors> startWebAuthnRegistration(WebAuthnRegisterStartRequest request) {
    return start(WebAuthnRegisterStartResponse.class, Errors.class)
        .uri("/api/webauthn/register/start")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Complete login using a 2FA challenge
   *
   * @param request The login request that contains the user credentials used to log them in.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> twoFactorLogin(TwoFactorLoginRequest request) {
    return startAnonymous(LoginResponse.class, Errors.class)
        .uri("/api/two-factor/login")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Updates an API key with the given Id.
   *
   * @param keyId The Id of the API key to update.
   * @param request The request that contains all the new API key information.
   * @return The ClientResponse object.
   */
  public ClientResponse<APIKeyResponse, Errors> updateAPIKey(UUID keyId, APIKeyRequest request) {
    return start(APIKeyResponse.class, Errors.class)
        .uri("/api/api-key")
        .urlSegment(keyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the application with the given Id.
   *
   * @param applicationId The Id of the application to update.
   * @param request The request that contains all the new application information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> updateApplication(UUID applicationId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the application role with the given Id for the application.
   *
   * @param applicationId The Id of the application that the role belongs to.
   * @param roleId The Id of the role to update.
   * @param request The request that contains all the new role information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> updateApplicationRole(UUID applicationId, UUID roleId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("role")
        .urlSegment(roleId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the connector with the given Id.
   *
   * @param connectorId The Id of the connector to update.
   * @param request The request object that contains all the new connector information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConnectorResponse, Errors> updateConnector(UUID connectorId, ConnectorRequest request) {
    return start(ConnectorResponse.class, Errors.class)
        .uri("/api/connector")
        .urlSegment(connectorId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the consent with the given Id.
   *
   * @param consentId The Id of the consent to update.
   * @param request The request that contains all the new consent information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ConsentResponse, Errors> updateConsent(UUID consentId, ConsentRequest request) {
    return start(ConsentResponse.class, Errors.class)
        .uri("/api/consent")
        .urlSegment(consentId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the email template with the given Id.
   *
   * @param emailTemplateId The Id of the email template to update.
   * @param request The request that contains all the new email template information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Errors> updateEmailTemplate(UUID emailTemplateId, EmailTemplateRequest request) {
    return start(EmailTemplateResponse.class, Errors.class)
        .uri("/api/email/template")
        .urlSegment(emailTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the Entity with the given Id.
   *
   * @param entityId The Id of the Entity to update.
   * @param request The request that contains all the new Entity information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityResponse, Errors> updateEntity(UUID entityId, EntityRequest request) {
    return start(EntityResponse.class, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the Entity Type with the given Id.
   *
   * @param entityTypeId The Id of the Entity Type to update.
   * @param request The request that contains all the new Entity Type information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> updateEntityType(UUID entityTypeId, EntityTypeRequest request) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the permission with the given Id for the entity type.
   *
   * @param entityTypeId The Id of the entityType that the permission belongs to.
   * @param permissionId The Id of the permission to update.
   * @param request The request that contains all the new permission information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EntityTypeResponse, Errors> updateEntityTypePermission(UUID entityTypeId, UUID permissionId, EntityTypeRequest request) {
    return start(EntityTypeResponse.class, Errors.class)
        .uri("/api/entity/type")
        .urlSegment(entityTypeId)
        .urlSegment("permission")
        .urlSegment(permissionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates a family with a given Id.
   *
   * @param familyId The Id of the family to update.
   * @param request The request object that contains all the new family information.
   * @return The ClientResponse object.
   */
  public ClientResponse<FamilyResponse, Errors> updateFamily(UUID familyId, FamilyRequest request) {
    return start(FamilyResponse.class, Errors.class)
        .uri("/api/user/family")
        .urlSegment(familyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the form with the given Id.
   *
   * @param formId The Id of the form to update.
   * @param request The request object that contains all the new form information.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormResponse, Errors> updateForm(UUID formId, FormRequest request) {
    return start(FormResponse.class, Errors.class)
        .uri("/api/form")
        .urlSegment(formId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the form field with the given Id.
   *
   * @param fieldId The Id of the form field to update.
   * @param request The request object that contains all the new form field information.
   * @return The ClientResponse object.
   */
  public ClientResponse<FormFieldResponse, Errors> updateFormField(UUID fieldId, FormFieldRequest request) {
    return start(FormFieldResponse.class, Errors.class)
        .uri("/api/form/field")
        .urlSegment(fieldId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the group with the given Id.
   *
   * @param groupId The Id of the group to update.
   * @param request The request that contains all the new group information.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> updateGroup(UUID groupId, GroupRequest request) {
    return start(GroupResponse.class, Errors.class)
        .uri("/api/group")
        .urlSegment(groupId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Creates a member in a group.
   *
   * @param request The request object that contains all the information used to create the group member(s).
   * @return The ClientResponse object.
   */
  public ClientResponse<MemberResponse, Errors> updateGroupMembers(MemberRequest request) {
    return start(MemberResponse.class, Errors.class)
        .uri("/api/group/member")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the IP Access Control List with the given Id.
   *
   * @param accessControlListId The Id of the IP Access Control List to update.
   * @param request The request that contains all the new IP Access Control List information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IPAccessControlListResponse, Errors> updateIPAccessControlList(UUID accessControlListId, IPAccessControlListRequest request) {
    return start(IPAccessControlListResponse.class, Errors.class)
        .uri("/api/ip-acl")
        .urlSegment(accessControlListId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the identity provider with the given Id.
   *
   * @param identityProviderId The Id of the identity provider to update.
   * @param request The request object that contains the updated identity provider.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> updateIdentityProvider(UUID identityProviderId, IdentityProviderRequest request) {
    return start(IdentityProviderResponse.class, Errors.class)
        .uri("/api/identity-provider")
        .urlSegment(identityProviderId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the available integrations.
   *
   * @param request The request that contains all the new integration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IntegrationResponse, Errors> updateIntegrations(IntegrationRequest request) {
    return start(IntegrationResponse.class, Errors.class)
        .uri("/api/integration")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the key with the given Id.
   *
   * @param keyId The Id of the key to update.
   * @param request The request that contains all the new key information.
   * @return The ClientResponse object.
   */
  public ClientResponse<KeyResponse, Errors> updateKey(UUID keyId, KeyRequest request) {
    return start(KeyResponse.class, Errors.class)
        .uri("/api/key")
        .urlSegment(keyId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the lambda with the given Id.
   *
   * @param lambdaId The Id of the lambda to update.
   * @param request The request that contains all the new lambda information.
   * @return The ClientResponse object.
   */
  public ClientResponse<LambdaResponse, Errors> updateLambda(UUID lambdaId, LambdaRequest request) {
    return start(LambdaResponse.class, Errors.class)
        .uri("/api/lambda")
        .urlSegment(lambdaId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the message template with the given Id.
   *
   * @param messageTemplateId The Id of the message template to update.
   * @param request The request that contains all the new message template information.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessageTemplateResponse, Errors> updateMessageTemplate(UUID messageTemplateId, MessageTemplateRequest request) {
    return start(MessageTemplateResponse.class, Errors.class)
        .uri("/api/message/template")
        .urlSegment(messageTemplateId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the messenger with the given Id.
   *
   * @param messengerId The Id of the messenger to update.
   * @param request The request object that contains all the new messenger information.
   * @return The ClientResponse object.
   */
  public ClientResponse<MessengerResponse, Errors> updateMessenger(UUID messengerId, MessengerRequest request) {
    return start(MessengerResponse.class, Errors.class)
        .uri("/api/messenger")
        .urlSegment(messengerId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the OAuth scope with the given Id for the application.
   *
   * @param applicationId The Id of the application that the OAuth scope belongs to.
   * @param scopeId The Id of the OAuth scope to update.
   * @param request The request that contains all the new OAuth scope information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationOAuthScopeResponse, Errors> updateOAuthScope(UUID applicationId, UUID scopeId, ApplicationOAuthScopeRequest request) {
    return start(ApplicationOAuthScopeResponse.class, Errors.class)
        .uri("/api/application")
        .urlSegment(applicationId)
        .urlSegment("scope")
        .urlSegment(scopeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the registration for the user with the given Id and the application defined in the request.
   *
   * @param userId The Id of the user whose registration is going to be updated.
   * @param request The request that contains all the new registration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> updateRegistration(UUID userId, RegistrationRequest request) {
    return start(RegistrationResponse.class, Errors.class)
        .uri("/api/user/registration")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the system configuration.
   *
   * @param request The request that contains all the new system configuration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<SystemConfigurationResponse, Errors> updateSystemConfiguration(SystemConfigurationRequest request) {
    return start(SystemConfigurationResponse.class, Errors.class)
        .uri("/api/system-configuration")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the tenant with the given Id.
   *
   * @param tenantId The Id of the tenant to update.
   * @param request The request that contains all the new tenant information.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> updateTenant(UUID tenantId, TenantRequest request) {
    return start(TenantResponse.class, Errors.class)
        .uri("/api/tenant")
        .urlSegment(tenantId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the theme with the given Id.
   *
   * @param themeId The Id of the theme to update.
   * @param request The request that contains all the new theme information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ThemeResponse, Errors> updateTheme(UUID themeId, ThemeRequest request) {
    return start(ThemeResponse.class, Errors.class)
        .uri("/api/theme")
        .urlSegment(themeId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the user with the given Id.
   *
   * @param userId The Id of the user to update.
   * @param request The request that contains all the new user information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> updateUser(UUID userId, UserRequest request) {
    return start(UserResponse.class, Errors.class)
        .uri("/api/user")
        .urlSegment(userId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the user action with the given Id.
   *
   * @param userActionId The Id of the user action to update.
   * @param request The request that contains all the new user action information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> updateUserAction(UUID userActionId, UserActionRequest request) {
    return start(UserActionResponse.class, Errors.class)
        .uri("/api/user-action")
        .urlSegment(userActionId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the user action reason with the given Id.
   *
   * @param userActionReasonId The Id of the user action reason to update.
   * @param request The request that contains all the new user action reason information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Errors> updateUserActionReason(UUID userActionReasonId, UserActionReasonRequest request) {
    return start(UserActionReasonResponse.class, Errors.class)
        .uri("/api/user-action-reason")
        .urlSegment(userActionReasonId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates a single User consent by Id.
   *
   * @param userConsentId The User Consent Id
   * @param request The request that contains the user consent information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserConsentResponse, Errors> updateUserConsent(UUID userConsentId, UserConsentRequest request) {
    return start(UserConsentResponse.class, Errors.class)
        .uri("/api/user/consent")
        .urlSegment(userConsentId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Updates the webhook with the given Id.
   *
   * @param webhookId The Id of the webhook to update.
   * @param request The request that contains all the new webhook information.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Errors> updateWebhook(UUID webhookId, WebhookRequest request) {
    return start(WebhookResponse.class, Errors.class)
        .uri("/api/webhook")
        .urlSegment(webhookId)
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .put()
        .go();
  }

  /**
   * Creates or updates an Entity Grant. This is when a User/Entity is granted permissions to an Entity.
   *
   * @param entityId The Id of the Entity that the User/Entity is being granted access to.
   * @param request The request object that contains all the information used to create the Entity Grant.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> upsertEntityGrant(UUID entityId, EntityGrantRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/entity")
        .urlSegment(entityId)
        .urlSegment("grant")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Validates the end-user provided user_code from the user-interaction of the Device Authorization Grant.
   * If you build your own activation form you should validate the user provided code prior to beginning the Authorization grant.
   *
   * @param user_code The end-user verification code.
   * @param client_id The client Id.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> validateDevice(String user_code, String client_id) {
    return startAnonymous(Void.TYPE, Void.TYPE)
        .uri("/oauth2/device/validate")
        .urlParameter("user_code", user_code)
        .urlParameter("client_id", client_id)
        .get()
        .go();
  }

  /**
   * Validates the provided JWT (encoded JWT string) to ensure the token is valid. A valid access token is properly
   * signed and not expired.
   * <p>
   * This API may be used to verify the JWT as well as decode the encoded JWT into human readable identity claims.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<ValidateResponse, Void> validateJWT(String encodedJWT) {
    return startAnonymous(ValidateResponse.class, Void.TYPE)
        .uri("/api/jwt/validate")
        .authorization("Bearer " + encodedJWT)
        .get()
        .go();
  }

  /**
   * It's a JWT vending machine!
   * <p>
   * Issue a new access token (JWT) with the provided claims in the request. This JWT is not scoped to a tenant or user, it is a free form 
   * token that will contain what claims you provide.
   * <p>
   * The iat, exp and jti claims will be added by FusionAuth, all other claims must be provided by the caller.
   * <p>
   * If a TTL is not provided in the request, the TTL will be retrieved from the default Tenant or the Tenant specified on the request either 
   * by way of the X-FusionAuth-TenantId request header, or a tenant scoped API key.
   *
   * @param request The request that contains all the claims for this JWT.
   * @return The ClientResponse object.
   */
  public ClientResponse<JWTVendResponse, Errors> vendJWT(JWTVendRequest request) {
    return start(JWTVendResponse.class, Errors.class)
        .uri("/api/jwt/vend")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Confirms a email verification. The Id given is usually from an email sent to the user.
   *
   * @param verificationId The email verification Id sent to the user.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to verifyEmailAddress and changed to take a JSON request body, use that method instead.
   */
  @Deprecated
  public ClientResponse<Void, Errors> verifyEmail(String verificationId) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/verify-email")
        .urlSegment(verificationId)
        .post()
        .go();
  }

  /**
   * Confirms a user's email address. 
   * <p>
   * The request body will contain the verificationId. You may also be required to send a one-time use code based upon your configuration. When 
   * the tenant is configured to gate a user until their email address is verified, this procedures requires two values instead of one. 
   * The verificationId is a high entropy value and the one-time use code is a low entropy value that is easily entered in a user interactive form. The 
   * two values together are able to confirm a user's email address and mark the user's email address as verified.
   *
   * @param request The request that contains the verificationId and optional one-time use code paired with the verificationId.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> verifyEmailAddress(VerifyEmailRequest request) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/verify-email")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Administratively verify a user's email address. Use this method to bypass email verification for the user.
   * <p>
   * The request body will contain the userId to be verified. An API key is required when sending the userId in the request body.
   *
   * @param request The request that contains the userId to verify.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> verifyEmailAddressByUserId(VerifyEmailRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/user/verify-email")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Administratively verify a user identity.
   *
   * @param request The identity verify request that contains information to verify the identity.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> verifyIdentity(VerifyRequest request) {
    return start(Void.TYPE, Errors.class)
        .uri("/api/identity/verify")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }

  /**
   * Confirms an application registration. The Id given is usually from an email sent to the user.
   *
   * @param verificationId The registration verification Id sent to the user.
   * @return The ClientResponse object.
   * @deprecated This method has been renamed to verifyUserRegistration and changed to take a JSON request body, use that method instead.
   */
  @Deprecated
  public ClientResponse<Void, Errors> verifyRegistration(String verificationId) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/verify-registration")
        .urlSegment(verificationId)
        .post()
        .go();
  }

  /**
   * Confirms a user's registration. 
   * <p>
   * The request body will contain the verificationId. You may also be required to send a one-time use code based upon your configuration. When 
   * the application is configured to gate a user until their registration is verified, this procedures requires two values instead of one. 
   * The verificationId is a high entropy value and the one-time use code is a low entropy value that is easily entered in a user interactive form. The 
   * two values together are able to confirm a user's registration and mark the user's registration as verified.
   *
   * @param request The request that contains the verificationId and optional one-time use code paired with the verificationId.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> verifyUserRegistration(VerifyRegistrationRequest request) {
    return startAnonymous(Void.TYPE, Errors.class)
        .uri("/api/user/verify-registration")
        .bodyHandler(new JSONBodyHandler(request, objectMapper()))
        .post()
        .go();
  }


  protected <T, U> RESTClient<T, U> start(Class<T> type, Class<U> errorType) {
    return startAnonymous(type, errorType).authorization(apiKey);
  }

  protected <T, U> RESTClient<T, U> startAnonymous(Class<T> type, Class<U> errorType) {
    RESTClient<T, U> client = new RESTClient<>(type, errorType)
        .successResponseHandler(type != Void.TYPE ? new JSONResponseHandler<>(type, objectMapper()) : null)
        .errorResponseHandler(errorType != Void.TYPE ? new JSONResponseHandler<>(errorType, objectMapper()) : null)
        .url(baseURL)
        .connectTimeout(connectTimeout)
        .readTimeout(readTimeout);

    if (tenantId != null) {
      client.header(TENANT_ID_HEADER, tenantId);
    }

    return client;
  }

  private ObjectMapper objectMapper() {
    return customMapper != null ? customMapper : objectMapper;
  }
}
