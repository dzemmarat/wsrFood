package ru.example.wsrfood.viewmodel.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.example.wsrfood.data.db.entity.DishEntity
import ru.example.wsrfood.viewmodel.core.BaseViewModel
import ru.example.wsrfood.viewmodel.core.Event

class MainViewModel: BaseViewModel() {

    private val _dishes = MutableStateFlow<MutableList<DishEntity>>(mutableListOf())
    val dishes: StateFlow<MutableList<DishEntity>> = _dishes

    fun getDishes() {
        viewModelScope.launch {
            _dishes.value = dao.getDishes()
        }
    }
}