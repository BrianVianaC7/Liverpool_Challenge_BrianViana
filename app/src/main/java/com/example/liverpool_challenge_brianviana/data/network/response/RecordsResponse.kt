package com.example.liverpool_challenge_brianviana.data.network.response

import com.google.gson.annotations.SerializedName

data class RecordsResponse(
    @SerializedName("productId") var productId: String? = null,
    @SerializedName("productDisplayName") var productDisplayName: String? = null,
    @SerializedName("productType") var productType: String? = null,
    @SerializedName("listPrice") var listPrice: Double? = null,
    @SerializedName("promoPrice") var promoPrice: Double? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("smImage") var smImage: String? = null,
    @SerializedName("lgImage") var lgImage: String? = null,
    @SerializedName("xlImage") var xlImage: String? = null,
    @SerializedName("variantsColor") var variantsColor: ArrayList<VariantsColorResponse> = arrayListOf()
)