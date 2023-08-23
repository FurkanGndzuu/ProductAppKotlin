package com.frkn.productappkotlin.retrofitServices

import com.frkn.productappkotlin.models.ODataValue
import com.frkn.productappkotlin.models.category
import com.frkn.productappkotlin.models.product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface retrofitProductService {


    @GET("/odata/categories")
    suspend fun  categories(): Response<ODataValue<category>>


    @GET("/odata/products?\$expand=category(\$select=Name)&\$select=id,name,stock,price,photoPath&\$orderby=id desc")
    suspend fun products(@Query("\$skip") page: Int): Response<ODataValue<product>>


    @GET("/odata/products({productId})?\$expand=category")
    suspend fun getProduct(@Path("productId") productId: Int): Response<ODataValue<product>>

    @POST("/odata/products")
    suspend fun addProduct(@Body product: product): Response<product>


    @PUT("/odata/products({productId})")
    suspend fun updateProduct(
        @Body product: product,
        @Path("productId") productId: Int
    ): Response<Unit>


    @DELETE("/odata/products({productId})")
    suspend fun deleteProduct(@Path("productId") productId: Int): Response<Unit>

}