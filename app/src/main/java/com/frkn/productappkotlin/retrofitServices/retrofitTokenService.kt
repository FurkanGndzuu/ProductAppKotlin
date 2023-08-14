package com.frkn.productappkotlin.retrofitServices


import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.models.introspect
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface retrofitTokenService {


    @POST("connect/token")
    @FormUrlEncoded
    suspend fun getTokenWithClientCredentials(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String
    ): Response<Token>

    @POST("connect/introspect")
    @FormUrlEncoded
    suspend fun checkToken(
        @Field("token") token: String,
        @Header("Authorization") authorization: String
    ): Response<introspect>


    @POST("connect/token")
    @FormUrlEncoded
    fun getTokenWithRefreshToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ) : Call<com.frkn.productappkotlin.models.Token>
}