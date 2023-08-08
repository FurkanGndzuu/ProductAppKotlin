package com.frkn.productappkotlin.Interceptors

import android.util.Log
import com.frkn.productappkotlin.apiServices.TokenService
import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.utilty.HelperService
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token: Token? = null

        var request = chain.request()

        token = HelperService.getTokenFromSharedPreference()

        if (token != null) {
            Log.i("Token", "Token dolu bir şekilde geldi")

            request = request.newBuilder().addHeader("Authorization", "Bearer ${token.accessToken}")
                .build()
        }

        var response = chain.proceed(request)

        if (response.code == 401) {
            if (token != null) {
                var apiResponse = TokenService.getTokenWithRefreshToken(token.refreshToken)

                if (apiResponse.isSuccessfull) {
                    HelperService.saveTokenSharedPreference(apiResponse.response!!)

                    var newToken = apiResponse.response!!

                    request = request.newBuilder()
                        .addHeader("Authorization", "Bearer ${newToken.accessToken}")
                        .build()


                    response = chain.proceed(request)
                }

            }
        }

        return  response
    }
}

