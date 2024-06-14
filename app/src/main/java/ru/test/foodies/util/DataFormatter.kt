package ru.test.foodies.util

import androidx.compose.runtime.MutableState
import ru.test.foodies.dto.Product
import ru.test.foodies.model.Model

object DataFormatter {
    fun filterProducts(
        products: List<Product>,
        isVegan: MutableState<Boolean>,
        isSpicy: MutableState<Boolean>,
        isDiscount: MutableState<Boolean>
    ): List<Product> {
        if (isDiscount.value) {
            return products.filter { product -> product.price_old != null }
        }
        if (isSpicy.value) {
            return products.filter { product -> product.tag_ids.contains(4) }
        }
        if (isVegan.value) {
            return products.filter { product -> product.tag_ids.contains(2) }
        }
        if (isDiscount.value && isVegan.value) {
            return products.filter { product -> product.price_old != null }
                .filter { product -> product.tag_ids.contains(2) }
        }
        if (isDiscount.value && isSpicy.value) {
            return products.filter { product -> product.price_old != null &&  product.tag_ids.contains(4) }
        }
        if (isSpicy.value && isVegan.value) {
            return products.filter { product -> product.tag_ids.contains(4) && product.tag_ids.contains(2) }
        }
        if (isDiscount.value && isSpicy.value && isVegan.value) {
            return products.filter { product -> product.price_old != null && product.tag_ids.contains(4) && product.tag_ids.contains(2)  }
        }
        return products
    }
}