package com.frkn.productappkotlin.models

import com.google.gson.annotations.SerializedName

data class ODataValue<T>(@SerializedName("value") var value : ArrayList<T>) {
}