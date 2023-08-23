package com.frkn.productappkotlin.models

import com.google.gson.annotations.SerializedName

data class category(
    @SerializedName("Id")
    var Id: Int ,
    @SerializedName("Name")
    var name : String) {
}