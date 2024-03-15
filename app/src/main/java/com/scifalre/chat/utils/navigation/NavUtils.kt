package com.scifalre.chat.utils.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.scifalre.chat.R

fun Fragment.findNavController(): NavController? = activity?.findNavController(R.id.nav_host_main)
fun Activity.findNavController(): NavController? = findNavController(R.id.nav_host_main)

fun Fragment.navigateUp() {
    findNavController()?.navigateUp()
}