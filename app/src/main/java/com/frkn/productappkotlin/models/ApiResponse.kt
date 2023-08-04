package com.frkn.productappkotlin.models

data class ApiResponse<T>(var isSuccessfull : Boolean ,var response: T? = null , var error : error? = null) {
}