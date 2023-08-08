package com.frkn.productappkotlin.Interceptors

import com.frkn.productappkotlin.Exceptions.OfflineException
import com.frkn.productappkotlin.utilty.LiveNetworkListener
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!LiveNetworkListener.isConnected()) throw  OfflineException("")
        return  chain.proceed(chain.request())
    }
}