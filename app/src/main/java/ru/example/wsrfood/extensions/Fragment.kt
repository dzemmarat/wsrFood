package ru.example.wsrfood.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showToast(@StringRes stringRes: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(
        requireContext(),
        stringRes,
        length
    ).show()
}

fun Fragment.showToast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(
        requireContext(),
        text,
        length
    ).show()
}