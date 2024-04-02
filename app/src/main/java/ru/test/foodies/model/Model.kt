package ru.test.foodies.model

import ru.test.foodies.dto.Category
import ru.test.foodies.dto.Product
import ru.test.foodies.dto.Tag

data class Model(
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val error: Boolean = false,
    val loading: Boolean = false,
)
