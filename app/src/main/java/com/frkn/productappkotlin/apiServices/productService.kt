package com.frkn.productappkotlin.apiServices

import com.frkn.productappkotlin.consts.apiConsts
import com.frkn.productappkotlin.models.ApiResponse
import com.frkn.productappkotlin.models.ODataValue
import com.frkn.productappkotlin.models.category
import com.frkn.productappkotlin.models.product
import com.frkn.productappkotlin.retrofitServices.apiClient
import com.frkn.productappkotlin.retrofitServices.retrofitProductService
import com.frkn.productappkotlin.utilty.HelperService

class productService {

    companion object{


        private var retrofitProductServiceInterceptor =
            apiClient.buildService(apiConsts.apiBaseUrl, retrofitProductService::class.java, true)


        suspend fun categoryList(): ApiResponse<ArrayList<category>> {
            try {
                var response = retrofitProductServiceInterceptor.categories();

                if (!response.isSuccessful) return HelperService.handleApiError(response)

                var oDataCategory = response.body() as ODataValue<category>
                return ApiResponse(true, oDataCategory.value)


            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }


        }





        suspend fun productList(page: Int): ApiResponse<ArrayList<product>> {
            try {
                var response = retrofitProductServiceInterceptor.products(page);

                if (!response.isSuccessful) return HelperService.handleApiError(response)

                var oDataProduct = response.body() as ODataValue<product>
                return ApiResponse(true, oDataProduct.value)


            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }


        }


        suspend fun getProductById(productId: Int): ApiResponse<product> {
            try {
                var response = retrofitProductServiceInterceptor.getProduct(productId)

                if (!response.isSuccessful) return HelperService.handleApiError(response)

                var oDataProduct = response.body() as ODataValue<product>
                return ApiResponse(true, oDataProduct.value[0])


            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }


        }


        suspend fun addProduct(product: product): ApiResponse<product> {
            try {
                var response = retrofitProductServiceInterceptor.addProduct(product)

                if (!response.isSuccessful) return HelperService.handleApiError(response)

                var product = response.body() as product
                return ApiResponse(true,product )


            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }

        }

        suspend fun updateProduct(product: product): ApiResponse<Unit> {
            try {
                var response = retrofitProductServiceInterceptor.updateProduct(product, product.Id)

                if (!response.isSuccessful) return HelperService.handleApiError(response)

                return ApiResponse(true)


            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }

        }


        suspend fun deleteProduct(productId: Int): ApiResponse<Unit> {
            try {
                var response = retrofitProductServiceInterceptor.deleteProduct(productId)

                if (!response.isSuccessful) return HelperService.handleApiError(response)

                return ApiResponse(true)


            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }

        }

    }
}