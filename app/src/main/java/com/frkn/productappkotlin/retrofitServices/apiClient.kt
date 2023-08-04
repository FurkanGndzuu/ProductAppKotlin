package com.frkn.productappkotlin.retrofitServices

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class apiClient {

    companion object {

        fun <T> buildService(
            baseUrl: String,
            retrofitService: Class<T>,
            existInterceptor: Boolean
        ): T {

            var clientBuilder = OkHttpClient.Builder();

            if (existInterceptor) {
                TODO("ADD INTERCEPTOR")
            }

            return Retrofit.Builder().baseUrl(baseUrl).client(clientBuilder.build())
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build().create(retrofitService);

        }
    }
}