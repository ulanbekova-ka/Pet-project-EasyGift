package com.kay.prog.easygift.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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

        // not working animation
        binding.navigation.setOnItemSelectedListener {
            onOptionsItemSelected(it)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home -> openFragment(MainFragment(), false)
            R.id.nav_create ->Toast.makeText(this, "CREATE", Toast.LENGTH_SHORT).show()
            R.id.nav_search ->Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show()
            R.id.nav_profile ->Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}