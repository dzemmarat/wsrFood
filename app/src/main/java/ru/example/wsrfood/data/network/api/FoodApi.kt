package ru.example.wsrfood.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.example.wsrfood.data.models.remote.DishResponse
import ru.example.wsrfood.data.models.remote.Versions

interface FoodApi {
    @GET("dishes/version")
    suspend fun getVersions() : Versions

    @GET("dishes")
    suspend fun getDishes(
        @Query("version") version: String
    ) : MutableList<DishResponse>
}