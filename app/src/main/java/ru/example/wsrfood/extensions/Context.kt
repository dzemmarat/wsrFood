package ru.example.wsrfood.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.let {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities.let {
            if (capabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return true
            else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true
            else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) return false
        }
    }
    return false
}