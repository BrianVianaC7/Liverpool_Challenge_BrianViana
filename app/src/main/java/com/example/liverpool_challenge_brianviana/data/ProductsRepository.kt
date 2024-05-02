package com.example.liverpool_challenge_brianviana.data

import com.example.liverpool_challenge_brianviana.data.network.ProductService
import com.example.liverpool_challenge_brianviana.data.network.response.RecordsResponse
import com.example.liverpool_challenge_brianviana.domain.model.RecordModel
import com.example.liverpool_challenge_brianviana.domain.model.toDomain
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val api: ProductService
) {
    suspend fun getAllProducts(
        pageNumber: Int,
        searchString: String,
        sortOption: String,
        forcePlp: Boolean,
        numberOfItemsPerPage: Int,
        cleanProductName: Boolean
    ): List<RecordModel> {
        val response: List<RecordsResponse> = api.getAllProductos(
            pageNumber,
            searchString,
            sortOption,
            forcePlp,
            numberOfItemsPerPage,
            cleanProductName
        )
        return response.map { convert -> convert.toDomain() }
    }
}