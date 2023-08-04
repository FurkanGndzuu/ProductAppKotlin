package com.frkn.productappkotlin.retrofitServices

import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.models.userSignup
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface retrofitLoginService {

    @POST("api/users")
    suspend fun signUp(
        @Body userSignup: userSignup,
        @Header("Authorization") authorization: String
    ): Response<ResponseBody>

    @POST("connect/token")
    @FormUrlEncoded
    suspend fun signin(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("username") username: String,
        @Field("password") password: String
    ) : Response<Token?>
}