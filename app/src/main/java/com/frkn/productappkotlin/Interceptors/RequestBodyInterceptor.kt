package com.frkn.productappkotlin.Interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.ByteString
import java.nio.Buffer

class RequestBodyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       var requestBody = chain.request().body

        if(requestBody != null) {



            val buffer : okio.Buffer = okio.Buffer()

            requestBody.writeTo(buffer)

            val bytes : ByteString = buffer.snapshot()


            requestBody = RequestBody.create(requestBody.contentType() , bytes)


           chain.request().newBuilder().post(requestBody).build()


            var response = chain.proceed(chain.request())

            return  response

        }

        else{
            var response = chain.proceed(chain.request())
            return  response
        }

    }
}