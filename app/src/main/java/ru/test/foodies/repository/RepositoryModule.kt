package ru.test.foodies.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.test.foodies.repository.categories.CategoriesRepository
import ru.test.foodies.repository.categories.CategoriesRepositoryImpl
import ru.test.foodies.repository.products.ProductsRepository
import ru.test.foodies.repository.products.ProductsRepositoryImpl
import ru.test.foodies.repository.tags.TagsRepository
import ru.test.foodies.repository.tags.TagsRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository
    @Singleton
    @Binds
    fun bindsCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
    @Singleton
    @Binds
    fun bindsTagsRepository(impl: TagsRepositoryImpl): TagsRepository
}