package ru.test.foodies.repository.products

import ru.test.foodies.dto.Product

interface ProductsRepository {
    suspend fun getProducts(): List<Product>


}
 