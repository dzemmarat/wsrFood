package ru.example.wsrfood.viewmodel.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.example.wsrfood.data.models.remote.DishResponse
import ru.example.wsrfood.data.models.remote.Versions
import ru.example.wsrfood.viewmodel.core.BaseViewModel
import ru.example.wsrfood.viewmodel.core.Event

class SplashViewModel : BaseViewModel() {

    private val _version = MutableStateFlow<Event<Versions>>(Event.idle())
    val version: StateFlow<Event<Versions>> = _version

    private val _dishes = MutableStateFlow<Event<MutableList<DishResponse>>>(Event.idle())
    val dishes: StateFlow<Event<List<DishResponse>>> = _dishes

    var isVersionsEquals: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getVersion() {
        viewModelScope.launch {
            requestWithMutableFlow(_version) {
                api.getVersions()
            }

            isVersionsEquals.value =
                dao.getVersions() != null
        }
    }

    fun getDishes() {
        requestWithMutableFlow(_dishes) {
            api.getDishes(
                version = _version.value.data!!.version.last()
            )
        }
    }

    fun insertVersionsInDb() {
        viewModelScope.launch {
            _version.value.data?.let {
                dao.setVersions(it.toEntity())
            }
        }
    }

    fun insertDishesInDb() {
        viewModelScope.launch {
            (_dishes.value.data as List<DishResponse>).forEach {
                dao.setDishes(it.toEntity())
            }
        }
    }
}