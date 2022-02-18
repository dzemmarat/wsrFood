package ru.example.wsrfood.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.example.wsrfood.data.db.dao.FoodDao
import ru.example.wsrfood.data.db.dao.TypesConverter
import ru.example.wsrfood.data.db.entity.DishEntity
import ru.example.wsrfood.data.db.entity.VersionsEntity

@Database(
    entities = [DishEntity::class, VersionsEntity::class],
    version = FoodDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(TypesConverter::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        const val DATABASE_NAME = "food-db"
        const val VERSION = 1
    }
}