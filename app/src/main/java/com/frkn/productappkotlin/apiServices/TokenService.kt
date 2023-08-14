package com.frkn.productappkotlin.apiServices



import com.frkn.productappkotlin.consts.apiConsts
import com.frkn.productappkotlin.models.ApiResponse
import com.frkn.productappkotlin.models.Token
import com.frkn.productappkotlin.models.introspect
import com.frkn.productappkotlin.retrofitServices.apiClient
import com.frkn.productappkotlin.retrofitServices.retrofitTokenService
import com.frkn.productappkotlin.utilty.HelperService
import java.lang.Exception

class TokenService {
    companion object {
        private var retrofitTokenService =
            apiClient.buildService(apiConsts.authBaseUrl, retrofitTokenService::class.java, false)


        suspend fun getTokenWithClientCredentials(): ApiResponse<Token> {

            try {
                var response = retrofitTokenService.getTokenWithClientCredentials(
                    apiConsts.Client_Id_CC,
                    apiConsts.Client_Secret_CC,
                    apiConsts.clientCredentialGrantType
                )

                if (!response.isSuccessful) return ApiResponse(false)
                return ApiResponse(true, response.body() as Token)
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }
        }

        fun getTokenWithRefreshToken(refreshToken: String): ApiResponse<Token> {
            try {
                var response = retrofitTokenService.getTokenWithRefreshToken(
                    apiConsts.Client_Id_ROP,
                    apiConsts.Client_Secret_ROP,
                    apiConsts.refreshTokenCredentialGrantType,
                    refreshToken
                ).execute()

                if (!response.isSuccessful)
                    return ApiResponse(false)
                return ApiResponse(true, response.body() as Token)
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }

        }


        suspend fun checkToken(): ApiResponse<Unit> {
            try {
                var token = HelperService.getTokenFromSharedPreference()

                if (token == null) {
                    var newToken = Token(
                        "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVGMTdCOTdDMzRCMTA4RDYzQUE3NUZCMzUyNzBCMzQ4IiwidHlwIjoiYXQrand0In0.eyJuYmYiOjE2OTE1NjE2NTYsImV4cCI6MTY5Njc0NTY1NiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo1MDAxIiwiYXVkIjpbInBob3RvX3Jlc291cmNlIiwicHJvZHVjdF9yZXNvdXJjZSIsImh0dHA6Ly9sb2NhbGhvc3Q6NTAwMS9yZXNvdXJjZXMiXSwiY2xpZW50X2lkIjoiQ2xpZW50X1JPUCIsInN1YiI6IjhjNmEwYjEyLTBkZTItNGMwZi1iMTg5LWNlMjhlNWVmYjhhNSIsImF1dGhfdGltZSI6MTY5MTU2MTY1NSwiaWRwIjoibG9jYWwiLCJqdGkiOiIyNUM1OUE2OUU1ODdBNDIwRDUzRTdGM0NBNTI2NTk5MSIsImlhdCI6MTY5MTU2MTY1Niwic2NvcGUiOlsiZW1haWwiLCJJZGVudGl0eVNlcnZlckFwaSIsIm9wZW5pZCIsInBob3RvX2FwcF9mdWxsX3Blcm1pc3Npb24iLCJwcm9kdWN0X2FwcF9mdWxsX3Blcm1pc3Npb24iLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.FbNepqXA3ThSyWUdzoll0aFCbQpL9Xw-K_coqVoLq1Er-9rwHR_O3cKGJjYuuDTbtc69DSysXDnlgPoaYgHDmIDdxB0i_809Rq-04Z4KAjOI_v1-k3qQFMZLgmAdFfMIll95gjtz0AP7QvNMI0R6r0VUL-fHd7GVTooIfFcuoweqoG8OmA1_jf_zI2s6TX80u6InmT_5zD8BGmFDsD_DeKI5YskODASHvCnS4cOuThGKC8Se8OJWTeKbX0I4JbsmXMwCWQ9xiWoncyZV3qu5O8jZF2ODPRWlY6UjWDzh5n5BTK9wtDUyBUomxt3oljeHeKHOrD5ZzPZDkMDdEMdXAA",
                        5184000,
                        "A70D97FC0B22A013FEAA780D7135D7560433BA12A8B4015999FF167DE672F1EE"
                    )

                    token = newToken
                }

                if(token == null) return ApiResponse(false)


                var authorization =
                    okhttp3.Credentials.basic(apiConsts.productusername, apiConsts.productpassword)

                var response = retrofitTokenService.checkToken(token.accessToken, authorization)

                if (!response.isSuccessful) return ApiResponse(false)

                var introspec = response.body() as introspect

                if (!introspec.active) return ApiResponse(false)

                return ApiResponse(true)
            } catch (ex: Exception) {
                return HelperService.handleException(ex)
            }
        }
    }
}