package com.example.liverpool_challenge_brianviana.domain.model

import com.example.liverpool_challenge_brianviana.data.network.response.RecordsResponse
import com.example.liverpool_challenge_brianviana.data.network.response.VariantsColorResponse

data class RecordModel(
    var productId: String? = null,
    var productDisplayName: String? = null,
    var productType: String? = null,
    var listPrice: Double? = null,
    var promoPrice: Double? = null,
    var brand: String? = null,
    var category: String? = null,
    var smImage: String? = null,
    var lgImage: String? = null,
    var xlImage: String? = null,
    var variantsColor: ArrayList<VariantsColorResponse> = arrayListOf()
)

fun RecordsResponse.toDomain() = RecordModel(
    productId,
    productDisplayName,
    productType,
    listPrice,
    promoPrice,
    brand,
    category,
    smImage,
    lgImage,
    xlImage,
    variantsColor
)