package ru.test.foodies.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.foodies.dto.Category
import ru.test.foodies.dto.Product
import ru.test.foodies.dto.Tag

interface ApiService {
    @GET("Products.json")
    suspend fun getProducts(): Response<List<Product>>
    @GET("Tags.json")
    suspend fun getTags(): Response<List<Tag>>

    @GET("Categories.json")
    suspend fun getCategories(): Response<List<Category>>
}