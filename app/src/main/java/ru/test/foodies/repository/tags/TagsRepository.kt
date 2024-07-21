package ru.test.foodies.repository.tags

import ru.test.foodies.dto.Tag

interface TagsRepository {
    suspend fun getTags(): List<Tag>
}