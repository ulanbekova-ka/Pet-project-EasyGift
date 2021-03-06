package com.kay.prog.easygift.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kay.prog.easygift.R
import dagger.hilt.android.AndroidEntryPoint
import com.kay.prog.easygift.databinding.ActivityMainBinding
import com.kay.prog.easygift.ui.base.BaseActivity
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.create.CreateWishFragment
import com.kay.prog.easygift.ui.profile.ProfileFragment
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
            openFragment(MylistFragment())
        }

        setNavigationView()
    }

    private fun setNavigationView() {
        binding.navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    openFragment(MylistFragment(), false)
                    true
                }
                R.id.nav_create -> {
                    openFragment(CreateWishFragment(), false)
                    true
                }
                R.id.nav_search -> {
                    openFragment(SearchFragment(), false)
                    true
                }
                R.id.nav_profile -> {
                    openFragment(ProfileFragment(), false)
                    true
                }
                else -> false
            }
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

    override fun setPrefs(nickname: String) {
        val editor = prefs.edit()
        editor.putBoolean(LOGGED_IN, true).apply()
        editor.putString(KEY_NICKNAME, nickname).apply()

        openFragment(MylistFragment(), false)
        binding.navigation.visibility = View.VISIBLE
    }

    override fun deletePrefs() {
        val editor = prefs.edit()
        editor.putBoolean(LOGGED_IN, false).apply()

        binding.navigation.visibility = View.GONE
    }

    override fun getPrefs(): String {
        return prefs.getString(KEY_NICKNAME, "") ?: ""
    }

    companion object {
        private const val LOGGED_IN = "logged successfully"
        private const val KEY_NICKNAME = "nickname"
    }
}