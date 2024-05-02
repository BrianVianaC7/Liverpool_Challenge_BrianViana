package com.example.liverpool_challenge_brianviana.data

import com.example.liverpool_challenge_brianviana.data.network.ProductService
import com.example.liverpool_challenge_brianviana.data.network.response.RecordsResponse
import com.example.liverpool_challenge_brianviana.domain.model.toDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Prueba unitaria para verificar el comportamiento de `ProductsRepository`.
 *
 * Descripción:
 * - Se utiliza MockK para crear un mock del servicio de productos (ProductService), permitiendo simular su comportamiento.
 * - Se configura el mock para que devuelva una lista simulada de registros cuando se invoca el método `getAllProductos`.
 * - Luego, se llama al método `getAllProducts` del repositorio con argumentos arbitrarios.
 * - Finalmente, se verifica que el resultado retornado por el repositorio sea igual a la lista de registros mapeados al dominio.
 */
class ProductsRepositoryTest {

    /**
     * Prueba unitaria para verificar que `ProductsRepository` retorna los registros correctamente.
     */
    @Test
    fun `test getAllProducts returns records correctly`() = runBlocking {
        // Given
        val mockService = mockk<ProductService>()
        val mockRecordsResponse = listOf(RecordsResponse())
        coEvery {
            mockService.getAllProductos(
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns mockRecordsResponse

        val repository = ProductsRepository(mockService)

        // When
        val result = repository.getAllProducts(1, "", "", false, 10, false)

        // Then
        assertEquals(mockRecordsResponse.map { it.toDomain() }, result)
    }
}