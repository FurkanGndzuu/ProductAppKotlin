package com.frkn.productappkotlin.apiServices

import android.net.Uri
import com.frkn.productappkotlin.consts.apiConsts
import com.frkn.productappkotlin.models.ApiResponse
import com.frkn.productappkotlin.models.photo
import com.frkn.productappkotlin.models.photoDelete
import com.frkn.productappkotlin.retrofitServices.apiClient
import com.frkn.productappkotlin.retrofitServices.retrofitPhotoService
import com.frkn.productappkotlin.utilty.HelperService
import com.frkn.productappkotlin.utilty.RealPathUtil
import com.frkn.productappkotlin.utilty.globalApp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class photoService {

    companion object{

        private var retrofitPhotoServiceInterceptor =
            apiClient.buildService(apiConsts.photoBaseUrl, retrofitPhotoService::class.java, true)


        suspend fun uploadPhoto(fileUri: Uri): ApiResponse<photo> {

            try {
                var path = RealPathUtil.getRealPath(globalApp.getContext(), fileUri)

                var file = File(path)


                var requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

                var body = MultipartBody.Part.createFormData("photo", file.name, requestFile)

                var response = retrofitPhotoServiceInterceptor.upload(body)


                if (!response.isSuccessful) return HelperService.handleApiError(response)


                return ApiResponse(true, response.body())
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }


        }


        suspend fun deletePhoto(photoDelete: photoDelete): ApiResponse<Unit> {


            try {

                var response = retrofitPhotoServiceInterceptor.delete(photoDelete)

                if (!response.isSuccessful) return HelperService.handleApiError(response)


                return ApiResponse(true)
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }

        }
    }
}