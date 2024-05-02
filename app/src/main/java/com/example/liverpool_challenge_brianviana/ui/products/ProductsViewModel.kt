package com.example.liverpool_challenge_brianviana.ui.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liverpool_challenge_brianviana.domain.model.RecordModel
import com.example.liverpool_challenge_brianviana.domain.usecase.GetAllProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<RecordModel>>(emptyList())
    val products: StateFlow<List<RecordModel>> get() = _products
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private var page = 1
    private val MAX_PAGES = 1
    private val PAGE_SIZE = 40

    fun getAllProducts(
        sortOption: String
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val productsFromApi = getAllProductUseCase(
                    page,
                    sortOption,
                    PAGE_SIZE,
                )
                _products.value = productsFromApi
            } catch (e: Exception) {
                _products.value = emptyList()
                _isLoading.value = false
                Log.e("Error", e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreProducts(
        sortOption: String,
    ) {
        viewModelScope.launch {
            try {
                if (page <= MAX_PAGES) {
                    val productsFromApi = getAllProductUseCase(
                        page,
                        sortOption,
                        PAGE_SIZE,
                    )
                    _products.value += productsFromApi
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}