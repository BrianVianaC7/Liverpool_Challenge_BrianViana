package com.example.liverpool_challenge_brianviana.data.network.response

import com.google.gson.annotations.SerializedName

data class VariantsColorResponse(

    @SerializedName("colorName") var colorName: String? = null,
    @SerializedName("colorHex") var colorHex: String? = null,
    @SerializedName("colorImageURL") var colorImageURL: String? = null,
    @SerializedName("colorMainURL") var colorMainURL: String? = null,
    @SerializedName("skuId") var skuId: String? = null

)