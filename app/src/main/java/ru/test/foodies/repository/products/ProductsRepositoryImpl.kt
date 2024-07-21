package ru.test.foodies.repository.products

import ru.test.foodies.api.ProductsApiService
import ru.test.foodies.dto.Product
import ru.test.foodies.error.ApiError
import ru.test.foodies.error.NetworkError
import ru.test.foodies.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ProductsApiService
) : ProductsRepository {

    override suspend fun getProducts(): List<Product> {
        try {
            val response = apiService.getProducts()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }

}









