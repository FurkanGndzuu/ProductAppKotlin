package com.frkn.productappkotlin.apiServices

import com.frkn.productappkotlin.consts.apiConsts
import com.frkn.productappkotlin.models.ApiResponse
import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.models.userLogin
import com.frkn.productappkotlin.models.userSignup
import com.frkn.productappkotlin.retrofitServices.apiClient
import com.frkn.productappkotlin.utilty.HelperService
import java.lang.Exception

class LoginService {

    companion object {
        private var retrofitLoginService =
            apiClient.buildService(
                apiConsts.authBaseUrl,
                com.frkn.productappkotlin.retrofitServices.retrofitLoginService::class.java,
                false
            )


        suspend fun signUp(userSignup: userSignup): ApiResponse<Unit> {
            try {
                var response = TokenService.getTokenWithClientCredentials();

                if (!response.isSuccessfull) return ApiResponse(false)

                var token = response.response!!

                var signupResponse =
                    retrofitLoginService.signUp(userSignup, "Bearer ${token.accessToken}")

                if (!signupResponse.isSuccessful) return HelperService.handleApiError(signupResponse)

                return ApiResponse(true)
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }
        }

        suspend fun signIn(userLogin: userLogin): ApiResponse<Unit> {
            try {

                var response = TokenService.getTokenWithClientCredentials()
                if (!response.isSuccessfull) return ApiResponse(false)

                var token = response.response!!

                var loginResponse = retrofitLoginService.signin(
                    apiConsts.Client_Id_ROP,
                    apiConsts.Client_Secret_ROP,
                    apiConsts.resourceOwnerPasswordCredentialGrantType,
                    userLogin.email,
                    userLogin.password
                )

                if (!loginResponse.isSuccessful) return HelperService.handleApiError(loginResponse)

                HelperService.saveTokenSharedPreference(loginResponse.body() as Token)

                return ApiResponse(true)
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }

        }
    }
}