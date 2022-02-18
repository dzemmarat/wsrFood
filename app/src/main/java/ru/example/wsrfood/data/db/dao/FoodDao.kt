package ru.example.wsrfood.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.example.wsrfood.data.db.entity.DishEntity
import ru.example.wsrfood.data.db.entity.VersionsEntity

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFood(dish: DishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setVersions(versions: VersionsEntity)

    @Query("SELECT * FROM versions")
    suspend fun getVersions(): VersionsEntity?
}