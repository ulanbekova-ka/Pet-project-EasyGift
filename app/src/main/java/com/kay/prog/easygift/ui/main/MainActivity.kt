package com.kay.prog.easygift.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kay.prog.easygift.R
import dagger.hilt.android.AndroidEntryPoint
import com.kay.prog.easygift.databinding.ActivityMainBinding
import com.kay.prog.easygift.ui.base.BaseActivity
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.search.SearchFragment
import com.kay.prog.easygift.ui.mylist.MylistFragment
import com.kay.prog.easygift.ui.mylist.MylistVM

@AndroidEntryPoint
class MainActivity: FragmentListener, BaseActivity<MylistVM,ActivityMainBinding>(
    MylistVM::class.java,
    { ActivityMainBinding.inflate(it)}
) {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPreferences(MODE_PRIVATE)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        if (!prefs.getBoolean(LOGGED_IN, false)) {
            openFragment(MainFragment(), false)
            binding.navigation.visibility = View.GONE
        } else {
            openFragment(MylistFragment(), false)
        }

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
            R.id.nav_home -> openFragment(MylistFragment(), false)
            R.id.nav_create -> Toast.makeText(this, "CREATE", Toast.LENGTH_SHORT).show()
            R.id.nav_search -> openFragment(SearchFragment())
            R.id.nav_profile -> Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setPrefs(nickname: String) {
        val editor = prefs.edit()
        editor.putBoolean(LOGGED_IN, true).apply()
        editor.putString(KEY_NICKNAME, nickname)

        openFragment(MylistFragment(), false)
        binding.navigation.visibility = View.VISIBLE
    }

    companion object {
        private const val LOGGED_IN = "logged successfully"
        private const val KEY_NICKNAME = "nickname"
    }
}