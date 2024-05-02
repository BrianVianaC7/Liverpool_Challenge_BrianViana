package com.example.liverpool_challenge_brianviana.data.network

import com.example.liverpool_challenge_brianviana.data.network.response.RecordsResponse
import com.example.liverpool_challenge_brianviana.data.network.response.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProductService @Inject constructor(
    private val apiClient: ProductApiClient
) {

    suspend fun getAllProductos(
        pageNumber: Int,
        searchString: String,
        sortOption: String,
        forcePlp: Boolean,
        numberOfItemsPerPage: Int,
        cleanProductName: Boolean
    ): List<RecordsResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<ResultResponse> = apiClient.getAllProducts(
                pageNumber,
                searchString,
                sortOption,
                forcePlp,
                numberOfItemsPerPage,
                cleanProductName
            )
            val result: ResultResponse? = response.body()
            result?.plpResults?.records ?: emptyList()
        }
    }

}