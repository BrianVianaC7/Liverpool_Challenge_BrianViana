package com.example.liverpool_challenge_brianviana.data.network

import com.example.liverpool_challenge_brianviana.data.network.response.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiClient {

    @GET("plp/sf")
    suspend fun getAllProducts(
        @Query("page-number") pageNumber: Int,
        @Query("search-string") searchString: String,
        @Query("sort-option") sortOption: String,
        @Query("force-plp") forcePlp: Boolean,
        @Query("number-of-items-per-page") numberOfItemsPerPage: Int,
        @Query("cleanProductName") cleanProductName: Boolean
    ): Response<ResultResponse>
}