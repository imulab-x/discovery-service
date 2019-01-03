package io.imulab.astrea.service.discovery

import com.fasterxml.jackson.annotation.JsonProperty
import io.imulab.astrea.sdk.oidc.discovery.Discovery

data class DiscoveryJson(
    @JsonProperty("issuer")
    override val issuer: String = "",
    @JsonProperty("authorization_endpoint")
    override var authorizationEndpoint: String = "",
    @JsonProperty("token_endpoint")
    override var tokenEndpoint: String = "",
    @JsonProperty("userinfo_endpoint")
    override var userInfoEndpoint: String = "",
    @JsonProperty("jwks_uri")
    override var jwksUri: String = "",
    @JsonProperty("registration_endpoint")
    override var registrationEndpoint: String = "",
    @JsonProperty("scopes_supported")
    override var scopesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("response_types_supported")
    override var responseTypesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("response_modes_supported")
    override var responseModeSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("grant_types_supported")
    override var grantTypesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("acr_values_supported")
    override var acrValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("subject_types_supported")
    override var subjectTypesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("id_token_signing_alg_values_supported")
    override var idTokenSigningAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("id_token_encryption_alg_values_supported")
    override var idTokenEncryptionAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("id_token_encryption_enc_values_supported")
    override var idTokenEncryptionEncodingValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("userinfo_signing_alg_values_supported")
    override var userInfoSigningAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("userinfo_encryption_alg_values_supported")
    override var userInfoEncryptionAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("userinfo_encryption_enc_values_supported")
    override var userInfoEncryptionEncodingValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("request_object_signing_alg_values_supported")
    override var requestObjectSigningAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("request_object_encryption_alg_values_supported")
    override var requestObjectEncryptionAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("request_object_encryption_enc_values_supported")
    override var requestObjectEncryptionEncodingValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("token_endpoint_auth_methods_supported")
    override var tokenEndpointAuthenticationMethodsSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("token_endpoint_auth_signing_alg_values_supported")
    override var tokenEndpointAuthenticationSigningAlgorithmValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("display_values_supported")
    override var displayValuesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("claim_types_supported")
    override var claimTypesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("claims_supported")
    override var claimsSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("service_documentation")
    override var serviceDocumentation: String = "",
    @JsonProperty("claims_locales_supported")
    override var claimsLocalesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("ui_locales_supported")
    override var uiLocalesSupported: MutableList<String> = mutableListOf(),
    @JsonProperty("claims_parameter_supported")
    override var claimsParameterSupported: Boolean = false,
    @JsonProperty("request_parameter_supported")
    override var requestParameterSupported: Boolean = false,
    @JsonProperty("request_uri_parameter_supported")
    override var requestUriParameterSupported: Boolean = false,
    @JsonProperty("require_request_uri_registration")
    override var requireRequestUriRegistration: Boolean = false,
    @JsonProperty("op_policy_uri")
    override var opPolicyUri: String = "",
    @JsonProperty("op_tos_uri")
    var opTosUri: String = ""
) : Discovery