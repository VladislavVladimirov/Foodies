package ru.test.foodies.repository.categories

import ru.test.foodies.api.CategoriesApiService
import ru.test.foodies.dto.Category
import ru.test.foodies.error.ApiError
import ru.test.foodies.error.NetworkError
import ru.test.foodies.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: CategoriesApiService
): CategoriesRepository {
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
}