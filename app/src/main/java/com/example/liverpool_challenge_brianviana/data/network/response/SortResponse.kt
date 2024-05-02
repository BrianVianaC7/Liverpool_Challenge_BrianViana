package com.example.liverpool_challenge_brianviana.data.network.response

import com.google.gson.annotations.SerializedName

data class SortResponse(
    @SerializedName("sortBy") var sortBy: String? = null,
    @SerializedName("label") var label: String? = null
)