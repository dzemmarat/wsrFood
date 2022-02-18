package ru.example.wsrfood.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class DishEntity(
    val category: String,
    val dishId: String,
    val icon: String,
    val nameDish: String,
    val price: String,
    val version: String
) {
    @PrimaryKey
    var id: Int = 0
}