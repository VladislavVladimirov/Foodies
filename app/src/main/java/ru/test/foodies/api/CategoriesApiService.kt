package ru.test.foodies.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.foodies.dto.Category

interface CategoriesApiService {
    @GET("Categories.json")
    suspend fun getCategories(): Response<List<Category>>
}