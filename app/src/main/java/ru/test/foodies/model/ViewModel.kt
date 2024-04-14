package ru.test.foodies.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.test.foodies.dto.Product
import ru.test.foodies.repository.Repository
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _data = MutableLiveData<Model>()
    val data: LiveData<Model>
        get() = _data


    init {
        loadCategories()
        loadProducts()
        loadTags()
    }

    private fun loadCategories() = viewModelScope.launch {
        try {
            _data.value = Model(loading = true)
            val categories = repository.getCategories()
            _data.value = Model(categories = categories)
        } catch (e: Exception) {
            _data.value = Model(error = true)
        }
    }

    private fun loadProducts() = viewModelScope.launch {
        try {
            _data.value = Model(loading = true)
            val products = repository.getProducts()
            _data.value = Model(products = products)
        } catch (e: Exception) {
            _data.value = Model(error = true)
        }
    }

    private fun loadTags() = viewModelScope.launch {
        try {
            _data.value = Model(loading = true)
            val tags = repository.getTags()
            _data.value = Model(tags = tags)
        } catch (e: Exception) {
            _data.value = Model(error = true)
        }
    }

    fun addToCart(product: Product) {
        val updatedProducts = _data.value?.products.orEmpty().toMutableList()
        val existingProduct = updatedProducts.find { it.id == product.id }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(count = existingProduct.count + 1)
            updatedProducts[updatedProducts.indexOf(existingProduct)] = updatedProduct
        }
        _data.value = Model(products = updatedProducts)
    }

    fun updateProductCount(productId: Int, change: Int) {
        val updatedProducts = _data.value?.products.orEmpty().toMutableList()
        val existingProduct = updatedProducts.find { it.id == productId }
        if (existingProduct != null) {
            val updatedCount = existingProduct.count + change
            if (updatedCount >= 0) {
                val updatedProduct = existingProduct.copy(count = updatedCount)
                updatedProducts[updatedProducts.indexOf(existingProduct)] = updatedProduct
            }
        }
        _data.value = Model(products = updatedProducts)
    }
}



