package com.example.liverpool_challenge_brianviana.data.network

import com.example.liverpool_challenge_brianviana.ui.utils.MockResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import retrofit2.Response

class ProductServiceTest {

    /**
     * Prueba unitaria para verificar el método `getAllProductos` en `ProductService`.
     *
     * Descripción:
     * - Se utiliza MockK para crear un mock del cliente de la API (ProductApiClient), permitiendo simular su comportamiento.
     * - Configuramos el mock para que devuelva un objeto de respuesta mock (ResultResponse) cuando se invoque el método
     *   `getAllProducts` con cualquier conjunto de argumentos.
     * - Luego, llamamos al método `getAllProductos` de `ProductService` con argumentos arbitrarios.
     * - Finalmente, verificamos que el resultado retornado por el método sea igual a la lista de registros (RecordsResponse)
     *   contenidos en el objeto de respuesta mock (ResultResponse), asegurando que el método procesa correctamente la respuesta
     *   de la API.
     */

    @Test
    fun `test getAllProductos returns records when API responds`() = runBlocking {
        // Given
        val mockApiClient = mockk<ProductApiClient>()
        val mockResultResponse = MockResponse.createMockResultResponse()

        coEvery {
            mockApiClient.getAllProducts(
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Response.success(mockResultResponse)

        // When
        val productService = ProductService(mockApiClient)
        val result = productService.getAllProductos(1, "", "", false, 10, false)

        // Then
        assertEquals(mockResultResponse.plpResults?.records, result)
    }
}