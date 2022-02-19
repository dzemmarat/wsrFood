package ru.example.wsrfood.viewmodel.signup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.example.wsrfood.data.models.remote.RegisterRequest
import ru.example.wsrfood.data.models.remote.RegisterResponse
import ru.example.wsrfood.data.models.remote.TokenResponse
import ru.example.wsrfood.viewmodel.core.BaseViewModel
import ru.example.wsrfood.viewmodel.core.Event

class SignUpViewModel : BaseViewModel() {

    var isValid: Boolean = true

    private val _authRequest = MutableStateFlow<Event<TokenResponse>>(Event.idle())
    val authRequest: StateFlow<Event<TokenResponse>> = _authRequest

    fun postRegister(email: String, password: String, login: String) {
        viewModelScope.launch {

            _authRequest.value = Event.loading()

            postRequestWithMutableFlow(_authRequest) {
                api.postRegister(RegisterRequest(email, password, login))
            }
        }
    }
}