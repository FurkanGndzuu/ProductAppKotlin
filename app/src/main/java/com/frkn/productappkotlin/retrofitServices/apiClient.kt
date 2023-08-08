package com.frkn.productappkotlin.retrofitServices

import com.frkn.productappkotlin.Interceptors.NetworkInterceptor
import com.frkn.productappkotlin.Interceptors.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class apiClient {

    companion object {

        fun <T> buildService(
            baseUrl: String,
            retrofitService: Class<T>,
            existInterceptor: Boolean
        ): T {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

            var clientBuilder = OkHttpClient.Builder().addInterceptor(logging).addInterceptor(NetworkInterceptor())

            if (existInterceptor) {
                clientBuilder.addInterceptor(TokenInterceptor())
            }

            return Retrofit.Builder().baseUrl(baseUrl).client(clientBuilder.build())
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build().create(retrofitService);

        }
    }
}