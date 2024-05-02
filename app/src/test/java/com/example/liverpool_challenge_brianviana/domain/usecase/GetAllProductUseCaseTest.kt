package com.example.liverpool_challenge_brianviana.domain.usecase

import com.example.liverpool_challenge_brianviana.data.ProductsRepository
import com.example.liverpool_challenge_brianviana.domain.model.RecordModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class GetAllProductUseCaseTest{
    /**
     * Prueba unitaria para verificar el comportamiento de `GetAllProductUseCase`.
     *
     * Descripción:
     * - Utiliza MockK para crear un mock del repositorio de productos (ProductsRepository) para simular su comportamiento.
     * - Se configura el mock para que devuelva una lista simulada de registros cuando se invoca el método `getAllProducts`.
     * - Luego, se llama al caso de uso `GetAllProductUseCase` con argumentos arbitrarios.
     * - Finalmente, se verifica que el resultado retornado por el caso de uso sea igual a la lista de registros obtenida del mock.
     */


    @RelaxedMockK
    private lateinit var getAllProductUseCase: GetAllProductUseCase
    private lateinit var mockRepository: ProductsRepository

    @Before
    fun setUp() {
        mockRepository = mockk()
        getAllProductUseCase = GetAllProductUseCase(mockRepository)
    }

    /**
     * Prueba unitaria para verificar que `GetAllProductUseCase` retorna los registros correctamente.
     */
    @Test
    fun `test GetAllProductUseCase returns records correctly`() = runBlocking {
        // Given
        val mockRecords = listOf<RecordModel>() // Lista simulada de registros
        coEvery {
            mockRepository.getAllProducts(
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns mockRecords

        // When
        val result = getAllProductUseCase(1, "", 40)

        // Then
        assertEquals(mockRecords, result)
        coVerify(exactly = 1) {
            mockRepository.getAllProducts(
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }
}