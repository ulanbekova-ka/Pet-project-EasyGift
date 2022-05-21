package com.kay.prog.easygift.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kay.prog.easygift.R
import dagger.hilt.android.AndroidEntryPoint
import com.kay.prog.easygift.databinding.ActivityMainBinding
import com.kay.prog.easygift.ui.base.BaseActivity
import com.kay.prog.easygift.ui.base.FragmentListener

@AndroidEntryPoint
class MainActivity: FragmentListener, BaseActivity<MainVM,ActivityMainBinding>(
    MainVM::class.java,
    { ActivityMainBinding.inflate(it)}
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            openFragment(MainFragment(), false)
        }
    }

    override fun openFragment(fragment: Fragment, addToBackStack: Boolean?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frg_container, fragment).apply {
                if (addToBackStack == true) {
                    addToBackStack(null)
                }
            }
            .commit()
    }
}