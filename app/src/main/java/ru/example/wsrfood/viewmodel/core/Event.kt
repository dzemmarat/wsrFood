package ru.example.wsrfood.viewmodel.core

data class Event<out T>(val status: Status, val data: T? = null, val error: String? = null) {

    companion object {
        fun <T> loading(): Event<T> = Event(
            status = Status.LOADING
        )


        fun <T> success(data: T?): Event<T> = Event(
            status = Status.SUCCESS,
            data = data
        )


        fun <T> error(error: String?): Event<T> = Event(
            Status.ERROR,
            error = error
        )

        fun <T> idle(): Event<T> = Event(
            status = Status.IDLE
        )
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    IDLE
}