package com.frkn.productappkotlin.models

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("expires_in") var expire: Long,
    @SerializedName("refresh_token") var refreshToken: String?
) {
}