package com.frkn.productappkotlin.retrofitServices

import com.frkn.productappkotlin.models.photo
import com.frkn.productappkotlin.models.photoDelete
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface retrofitPhotoService {

    @Multipart
    @POST("/api/photos")
    suspend fun  upload(@Part photo: MultipartBody.Part): Response<photo>

    @HTTP(method = "DELETE", path = "/api/photos",hasBody = true)
    suspend fun  delete(@Body photoDelete:photoDelete):Response<Unit>

}