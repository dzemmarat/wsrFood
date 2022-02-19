package ru.example.wsrfood.data.network.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.example.wsrfood.data.models.remote.*

interface FoodApi {
    @GET("dishes/version")
    suspend fun getVersions() : Versions

    @GET("dishes")
    suspend fun getDishes(
        @Query("version") version: String
    ) : MutableList<DishResponse>

    @POST("auth/register")
    suspend fun postRegister(
        @Body request: RegisterRequest
    ) : Call<TokenResponse>

    @POST("auth/login")
    fun postAuth(
        @Body request: AuthRequest
    ) : Call<TokenResponse>
}