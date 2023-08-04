package com.frkn.productappkotlin.apiServices


import android.content.Context
import android.provider.Settings.Global.getString
import com.frkn.productappkotlin.R
import com.frkn.productappkotlin.consts.apiConsts
import com.frkn.productappkotlin.models.ApiResponse
import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.models.introspect
import com.frkn.productappkotlin.retrofitServices.apiClient
import com.frkn.productappkotlin.retrofitServices.retrofitTokenService
import com.frkn.productappkotlin.utilty.globalApp
import com.google.gson.Gson

class TokenService {
    companion object {
        private var retrofitTokenService =
            apiClient.buildService(apiConsts.authBaseUrl, retrofitTokenService::class.java, false)


        suspend fun getTokenWithClientCredentials(): ApiResponse<Token> {

            var response = retrofitTokenService.getTokenWithClientCredentials(
                apiConsts.Client_Id_CC,
                apiConsts.Client_Secret_CC,
                apiConsts.clientCredentialGrantType
            )

            if (!response.isSuccessful) return ApiResponse(false)
            return ApiResponse(true, response.body() as Token)

        }

        fun getTokenWithRefreshToken(refreshToken: String): ApiResponse<Token> {
            var response = retrofitTokenService.getTokenWithRefreshToken(
                apiConsts.Client_Id_ROP,
                apiConsts.Client_Secret_ROP,
                apiConsts.refreshTokenCredentialGrantType,
                refreshToken
            ).execute()

            if(!response.isSuccessful)
                return ApiResponse(false)
            return ApiResponse(true , response.body() as Token)


        }


        suspend fun checkToken() : ApiResponse<Unit> {
            var prefenrences = globalApp.getContext().getSharedPreferences("apiToken" , Context.MODE_PRIVATE)
            var tokenString = prefenrences.getString("token" , null)

            var token = Gson().fromJson(tokenString , Token::class.java)

            if(token == null) return ApiResponse(false)

            var authorization = okhttp3.Credentials.basic(apiConsts.productusername , apiConsts.productpassword)

            var response = retrofitTokenService.checkToken(token.accessToken , authorization)

            if(!response.isSuccessful) return ApiResponse(false)

            var introspec = response.body() as introspect

            if(introspec.active == false) return  ApiResponse(false)

            return  ApiResponse(true)
        }
    }
}