package ru.test.foodies.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.foodies.dto.Tag

interface TagsApiService {
    @GET("Tags.json")
    suspend fun getTags(): Response<List<Tag>>
}