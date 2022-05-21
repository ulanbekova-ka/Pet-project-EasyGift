package com.kay.prog.easygift.ui.base

import androidx.fragment.app.Fragment

interface FragmentListener {
    fun openFragment(fragment: Fragment, addToBackStack: Boolean? = true) {}
}