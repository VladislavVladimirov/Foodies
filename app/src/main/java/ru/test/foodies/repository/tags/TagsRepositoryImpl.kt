package ru.test.foodies.repository.tags

import ru.test.foodies.api.TagsApiService
import ru.test.foodies.dto.Tag
import ru.test.foodies.error.ApiError
import ru.test.foodies.error.NetworkError
import ru.test.foodies.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class TagsRepositoryImpl @Inject constructor(
    private val apiService: TagsApiService
) : TagsRepository {
    override suspend fun getTags(): List<Tag> {
        try {
            val response = apiService.getTags()
            if (!response.isSuccessful) throw ApiError(response.code(), response.message())
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }
}