package com.frkn.productappkotlin.utilty

import android.content.Context
import com.frkn.productappkotlin.Exceptions.OfflineException
import com.frkn.productappkotlin.R
import com.frkn.productappkotlin.models.ApiResponse
import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.models.error
import com.google.gson.Gson
import retrofit2.Response
import java.lang.Exception

class HelperService {
    companion object {
        fun saveTokenSharedPreference(token: Token) {
            var preference =
                globalApp.getContext().getSharedPreferences("apiToken", Context.MODE_PRIVATE)

            var editor = preference.edit();

            editor.putString("token", Gson().toJson(token))
            editor.apply()
        }


        fun <T1, T2> handleApiError(response: Response<T1>): ApiResponse<T2> {
            var apiError: error? = null;

            if (response.errorBody() != null) {
                var errorBody = response.errorBody()!!.string()

                apiError = Gson().fromJson(errorBody, error::class.java)
            }


            return ApiResponse(false, null, apiError)
        }


        fun <T> handleException(ex: Exception): ApiResponse<T> {
            return when (ex) {

                is OfflineException -> {

                    val exmessage =
                        arrayListOf(globalApp.getContext().resources.getString(R.string.ex_no_exception))
                    var apiError = error(exmessage, 500, true)

                    ApiResponse(false, null, apiError)


                }

                else -> {
                    val exmessage =
                        arrayListOf(globalApp.getContext().resources.getString(R.string.ex_common_error))
                    var apiError = error(exmessage, 500, true)

                    ApiResponse(false, null, apiError)

                }
            }
        }


        fun getTokenFromSharedPreference(): Token? {
            return Gson().fromJson(
                globalApp.getContext().getSharedPreferences("apiToken", Context.MODE_PRIVATE)
                    .getString("token", null), Token::class.java
            )
        }
    }
}