package ru.example.wsrfood.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "versions")
data class VersionsEntity(
    val version: List<String>
) {
    @PrimaryKey
    var id = 0
}
