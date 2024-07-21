package ru.test.foodies.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.test.foodies.dto.Product
import ru.test.foodies.repository.categories.CategoriesRepository
import ru.test.foodies.repository.products.ProductsRepository
import ru.test.foodies.repository.tags.TagsRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val categoriesRepository: CategoriesRepository,
    private val tagsRepository: TagsRepository,
) : ViewModel() {
    private val _products = MutableLiveData<ProductsModel>()
    val products: LiveData<ProductsModel>
        get() = _products
    private val _categories = MutableLiveData<CategoriesModel>()
    val categories: LiveData<CategoriesModel>
        get() = _categories
    private val _tags = MutableLiveData<TagsModel>()
    val tags: LiveData<TagsModel>
        get() = _tags
    private val _state = MutableLiveData<StateModel>()
    val state: LiveData<StateModel>
        get() = _state


    init {
        loadCategories()
        loadProducts()
        loadTags()
    }

    private fun loadCategories() = viewModelScope.launch {
        try {
            _state.value = StateModel(loading = true)
            val categories = categoriesRepository.getCategories()
            _categories.value = CategoriesModel(categories = categories)
        } catch (e: Exception) {
            _state.value = StateModel(error = true)
        }
    }

    private fun loadProducts() = viewModelScope.launch {
        try {
            _state.value = StateModel(loading = true)
            val products = productsRepository.getProducts()
            _products.value = ProductsModel(products = products)
        } catch (e: Exception) {
            _state.value = StateModel(error = true)
        }
    }

    private fun loadTags() = viewModelScope.launch {
        try {
            _state.value = StateModel(loading = true)
            val tags = tagsRepository.getTags()
            _tags.value = TagsModel(tags = tags)
        } catch (e: Exception) {
            _state.value = StateModel(error = true)
        }
    }

    fun addToCart(product: Product) {
        val updatedProducts = _products.value?.products.orEmpty().toMutableList()
        val existingProduct = updatedProducts.find { it.id == product.id }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(count = existingProduct.count + 1)
            updatedProducts[updatedProducts.indexOf(existingProduct)] = updatedProduct
        }
        _products.value = ProductsModel(products = updatedProducts)
    }

    fun updateProductCount(productId: Int, change: Int) {
        val updatedProducts = _products.value?.products.orEmpty().toMutableList()
        val existingProduct = updatedProducts.find { it.id == productId }
        if (existingProduct != null) {
            val updatedCount = existingProduct.count + change
            if (updatedCount >= 0) {
                val updatedProduct = existingProduct.copy(count = updatedCount)
                updatedProducts[updatedProducts.indexOf(existingProduct)] = updatedProduct
            }
        }
        _products.value = ProductsModel(products = updatedProducts)
    }
}



