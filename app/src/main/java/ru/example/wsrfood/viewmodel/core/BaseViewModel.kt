package ru.example.wsrfood.viewmodel.core

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import ru.example.wsrfood.data.network.NetworkService
import ru.example.wsrfood.data.network.api.FoodApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.example.wsrfood.data.db.FoodDatabase
import ru.example.wsrfood.data.db.FoodDatabaseObject
import ru.example.wsrfood.data.db.dao.FoodDao
import java.lang.Exception

open class BaseViewModel: ViewModel() {

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

    fun setupDatabase(context: Context) {
        FoodDatabaseObject.createDatabase(context)
        dao = FoodDatabaseObject.getFoodDao()
    }

}