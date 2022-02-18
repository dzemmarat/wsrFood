package ru.example.wsrfood.data.models.remote

import ru.example.wsrfood.data.db.entity.VersionsEntity

data class Versions(
    val version: List<String>
) {
    fun toEntity() = VersionsEntity(
        version = version
    )
}
