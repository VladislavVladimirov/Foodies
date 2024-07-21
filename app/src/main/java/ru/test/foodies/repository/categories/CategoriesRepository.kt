package ru.test.foodies.repository.categories

import ru.test.foodies.dto.Category

interface CategoriesRepository {
    suspend fun  getCategories(): List<Category>
}