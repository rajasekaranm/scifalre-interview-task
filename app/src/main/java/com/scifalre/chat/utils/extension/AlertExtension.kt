package com.scifalre.chat.utils.extension

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.scifalre.chat.R

typealias OnPositiveButton = () -> Unit

fun Context.alert(message: String, onClick: OnPositiveButton? = null) {
    AlertDialog.Builder(this).setTitle(R.string.app_name).setMessage(message).setCancelable(false)
        .setPositiveButton(
            "OK"
        ) { p0, p1 ->
            p0?.dismiss()
            onClick?.invoke()
        }.show()
}

fun Fragment.alert(message: String, onClick: OnPositiveButton? = null) {
    requireActivity().alert(message, onClick)
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String) {
    requireActivity().toast(msg)
}

fun Fragment.toast(msg: Int) {
    requireActivity().toast(getString(msg))
}

fun Context.toast(msg: Int) {
    toast(getString(msg))
}