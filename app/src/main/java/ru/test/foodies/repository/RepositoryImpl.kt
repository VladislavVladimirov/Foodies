package ru.test.foodies.repository

import ru.test.foodies.api.ApiService
import ru.test.foodies.dto.Category
import ru.test.foodies.dto.Product
import ru.test.foodies.dto.Tag
import ru.test.foodies.error.ApiError
import ru.test.foodies.error.NetworkError
import ru.test.foodies.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

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

    override suspend fun getCategories(): List<Category> {
        try {
            val response = apiService.getCategories()
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

    override suspend fun getTags(): List<Tag> {
        try {
            val response = apiService.getTags()
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









