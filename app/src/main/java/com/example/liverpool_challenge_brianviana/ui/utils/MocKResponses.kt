package com.example.liverpool_challenge_brianviana.ui.utils

import com.example.liverpool_challenge_brianviana.data.network.response.PlpResultsResponse
import com.example.liverpool_challenge_brianviana.data.network.response.RecordsResponse
import com.example.liverpool_challenge_brianviana.data.network.response.ResultResponse
import com.example.liverpool_challenge_brianviana.data.network.response.VariantsColorResponse

object MockResponse {

    fun createMockResultResponse(): ResultResponse {
        return ResultResponse(
            plpResults = createMockPlpResultsResponse()
        )
    }

    private fun createMockPlpResultsResponse(): PlpResultsResponse {
        return PlpResultsResponse(
            label = "Mock Label",
            records = arrayListOf(createMockRecordsResponse()),
            sortOptions = arrayListOf() // Optional: puedes agregar sortOptions mock si es necesario
        )
    }

    private fun createMockRecordsResponse(): RecordsResponse {
        return RecordsResponse(
            productId = "123",
            productDisplayName = "Mock Product",
            productType = "Mock Type",
            listPrice = 10.0,
            promoPrice = 8.0,
            brand = "Mock Brand",
            category = "Mock Category",
            smImage = "mock_sm_image_url",
            lgImage = "mock_lg_image_url",
            xlImage = "mock_xl_image_url",
            variantsColor = arrayListOf(createMockVariantsColorResponse())
        )
    }

    private fun createMockVariantsColorResponse(): VariantsColorResponse {
        return VariantsColorResponse(
            colorName = "Mock Color",
            colorHex = "#FFFFFF",
            colorImageURL = "mock_color_image_url",
            colorMainURL = "mock_color_main_url",
            skuId = "mock_sku_id"
        )
    }
}