package com.kay.prog.easygift.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.showToast(message: String, length: Int? = Toast.LENGTH_LONG){
    Toast.makeText(this,message, length!!).show()
}

fun Fragment.showToast(message: String, length: Int? = Toast.LENGTH_LONG){
    Toast.makeText(requireContext(),message, length!!).show()
}