package com.example.liverpool_challenge_brianviana.ui.products

import com.example.liverpool_challenge_brianviana.domain.model.RecordModel
import com.example.liverpool_challenge_brianviana.domain.usecase.GetAllProductUseCase
import com.example.liverpool_challenge_brianviana.ui.utils.SortOptions
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
class ProductsViewModelTest {

    /**
     * Prueba unitaria para verificar el comportamiento del `ProductsViewModel` al interactuar con el caso de uso
     * `GetAllProductUseCase`, que es responsable de obtener todos los productos.
     *
     * Objetivo:
     * - Garantizar que el `ProductsViewModel` maneje correctamente la obtención de productos, tanto en situaciones
     *   de éxito como de error, a través del caso de uso `GetAllProductUseCase`.
     *
     * Descripción:
     * - Se utilizan mocks con MockK para simular el comportamiento del caso de uso.
     * - Se verifica que el ViewModel procese correctamente las respuestas del caso de uso, devolviendo los productos
     *   esperados y manejando adecuadamente los errores.
     * - También se verifica que el ViewModel actualice correctamente su estado de carga (`isLoading`).
     */


    @RelaxedMockK
    private lateinit var getAllProductUseCase: GetAllProductUseCase
    private lateinit var productsViewModel: ProductsViewModel

    private val expectedProducts = listOf(RecordModel(), RecordModel())
    val sortOption = SortOptions.RELEVANCE.toString()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getAllProductUseCase = mockk()
        productsViewModel = ProductsViewModel(getAllProductUseCase)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getAllProducts success`() = runBlocking {
        // Given

        coEvery { getAllProductUseCase(any(), any(), any()) } returns expectedProducts

        // When
        productsViewModel.getAllProducts(sortOption)

        // Then
        assertEquals(expectedProducts, productsViewModel.products.first())
        assertEquals(false, productsViewModel.isLoading.first())

        coVerify(exactly = 1) { getAllProductUseCase(any(), any(), any()) }
    }

    @Test
    fun `test getAllProducts error`() = runBlocking {
        // Given
        coEvery { getAllProductUseCase(any(), any(), any()) } throws Exception("error")

        // When
        productsViewModel.getAllProducts(sortOption)

        // Then
        assertEquals(emptyList<RecordModel>(), productsViewModel.products.first())
        assertEquals(false, productsViewModel.isLoading.first())

        coVerify(exactly = 1) { getAllProductUseCase(any(), any(), any()) }
    }

    @Test
    fun `test loadMoreProducts success`() = runBlocking {
        // Given

        coEvery { getAllProductUseCase(any(), any(), any()) } returns expectedProducts

        // When
        productsViewModel.loadMoreProducts(sortOption)

        // Then
        assertEquals(expectedProducts, productsViewModel.products.first())
        assertEquals(false, productsViewModel.isLoading.first())

        // Verify that getAllProductUseCase was called once
        coVerify(exactly = 1) { getAllProductUseCase(any(), any(), any()) }
    }

    @Test
    fun `test loadMoreProducts error`() = runBlocking {
        // Given
        coEvery { getAllProductUseCase(any(), any(), any()) } throws Exception("error")

        // When
        productsViewModel.loadMoreProducts(sortOption)

        // Then
        assertEquals(emptyList<RecordModel>(), productsViewModel.products.first())
        assertEquals(false, productsViewModel.isLoading.first())
        coVerify(exactly = 1) { getAllProductUseCase(any(), any(), any()) }
    }
}