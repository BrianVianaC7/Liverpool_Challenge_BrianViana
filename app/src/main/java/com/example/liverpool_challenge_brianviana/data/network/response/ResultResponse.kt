package com.example.liverpool_challenge_brianviana.data.network.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("plpResults") var plpResults: PlpResultsResponse? = PlpResultsResponse()
)