package com.frkn.productappkotlin.models

import com.google.gson.annotations.SerializedName

data class error(
    @SerializedName("Errors") var errors: ArrayList<String>,
    @SerializedName("Status") var status: Number,
    @SerializedName("IsShow") var isShow: Boolean
) {
}