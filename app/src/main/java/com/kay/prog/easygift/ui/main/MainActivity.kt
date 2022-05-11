package com.kay.prog.easygift.ui.main

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.kay.prog.easygift.databinding.ActivityMainBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.base.BaseActivity
import com.kay.prog.easygift.ui.base.BaseEvent

@AndroidEntryPoint
class MainActivity: BaseActivity<MainVM,ActivityMainBinding>(
    MainVM::class.java,
    { ActivityMainBinding.inflate(it)}
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        binding.run {
            button.setOnClickListener {
                vm.getUser()
            }
        }
    }

    private fun subscribeToLiveData(){
        vm.user.observe(this) {
            showToast(it.toString())
        }

        vm.event.observe(this) {
            when (it) {
                is BaseEvent.ShowToast -> showToast(it.message)
            }
        }
    }
}