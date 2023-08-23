package com.frkn.productappkotlin.models

import com.google.gson.annotations.SerializedName

data class product( @SerializedName("Id") var Id:Int,
                    @SerializedName("Name") var Name:String,
                    @SerializedName("Price") var Price:Double,
                    @SerializedName("Description") var Description:String,
                    @SerializedName("PhotoPath") var PhotoPath:String,
                    @SerializedName("Category_Id") var CategoryId:Int) {
}