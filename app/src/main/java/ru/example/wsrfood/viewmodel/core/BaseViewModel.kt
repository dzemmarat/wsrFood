package ru.example.wsrfood.viewmodel.core

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.example.wsrfood.data.db.FoodDatabaseObject
import ru.example.wsrfood.data.db.dao.FoodDao
import ru.example.wsrfood.data.models.remote.TokenResponse
import ru.example.wsrfood.data.network.NetworkService
import ru.example.wsrfood.data.network.api.FoodApi

open class BaseViewModel : ViewModel() {

    var api: FoodApi = NetworkService.retrofitService()
    protected lateinit var dao: FoodDao

    /**
     * Получаем результат запроса и сеттим его внутрь лайв даты
     */
    fun <T> requestWithMutableFlow(
        flow: MutableStateFlow<Event<T>>,
        request: suspend () -> T
    ) {

        // В начале запроса сразу отправляем ивент загрузки
        flow.value = Event.loading()

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                flow.value = Event.success(response)
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
                flow.value = Event.error(e.message)
            }
        }
    }

    fun <T> postRequestWithMutableFlow(
        flow: MutableStateFlow<Event<T>>,
        request: suspend () -> Call<T>
    ) {
        viewModelScope.launch {
            request().enqueue(object : Callback<T> {
                override fun onResponse(
                    call: Call<T>,
                    response: Response<T>
                ) {
                    flow.value = Event.success(response.body())
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    flow.value = Event.error(t.message)
                }
            })
        }
    }

    fun setupDatabase(context: Context) {
        FoodDatabaseObject.createDatabase(context)
        dao = FoodDatabaseObject.getFoodDao()
    }

}