package ru.test.foodies.model

import ru.test.foodies.dto.Product

data class ProductsModel(
    val products: List<Product> = emptyList(),
)
