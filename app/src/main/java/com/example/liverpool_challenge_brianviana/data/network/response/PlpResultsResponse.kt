package com.example.liverpool_challenge_brianviana.data.network.response

import com.google.gson.annotations.SerializedName

data class PlpResultsResponse(
    @SerializedName("label") var label: String? = null,
    @SerializedName("records") var records: ArrayList<RecordsResponse> = arrayListOf(),
    @SerializedName("sortOptions") var sortOptions: ArrayList<SortResponse> = arrayListOf()
)