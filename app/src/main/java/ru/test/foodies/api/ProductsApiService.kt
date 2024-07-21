package ru.test.foodies.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.foodies.dto.Product

interface ProductsApiService {
    @GET("Products.json")
    suspend fun getProducts(): Response<List<Product>>
}