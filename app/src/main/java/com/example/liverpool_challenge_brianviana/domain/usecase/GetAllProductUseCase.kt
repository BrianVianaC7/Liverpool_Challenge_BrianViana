package com.example.liverpool_challenge_brianviana.domain.usecase

import com.example.liverpool_challenge_brianviana.data.ProductsRepository
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(
        pageNumber: Int,
        sortOption: String,
        numberOfItemsPerPage: Int,
    ) = repository.getAllProducts(
        pageNumber,
        "",
        sortOption,
        false,
        numberOfItemsPerPage,
        false
    )
}