package com.frkn.productappkotlin.Interceptors

import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.ByteString
import java.nio.Buffer

class RequestBodyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(chain.request().body != null){

            var buffer =okio.Buffer()

            chain.request().body!!.writeTo(buffer)

            var bytes : ByteString = buffer.snapshot()

            var requestBody : RequestBody = RequestBody.create( chain.request().body!!.contentType() , bytes);
            }
        }
    }
}