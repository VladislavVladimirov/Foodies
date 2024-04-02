package ru.test.foodies.repository

import ru.test.foodies.dto.Category
import ru.test.foodies.dto.Product
import ru.test.foodies.dto.Tag

interface Repository {
    suspend fun getProducts(): List<Product>
    suspend fun  getCategories(): List<Category>
    suspend fun getTags(): List<Tag>
}
