package ru.test.foodies.model

import ru.test.foodies.dto.Category

data class CategoriesModel(
    val categories: List<Category> = emptyList(),
)
