package com.kay.prog.easygift.ui.authorisation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentAuthorisationBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.base.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorisationFragment: BaseFragment<AuthorisationVM, FragmentAuthorisationBinding>(
    AuthorisationVM::class.java,
    {
        FragmentAuthorisationBinding.inflate(it)
    }
) {

    private lateinit var fragmentListener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentListener = context as FragmentListener
        } catch (e: Exception) { print("Activity must implement FragmentListener")}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            autoBtn.setOnClickListener {
                vm.checkInput(nickname.text.toString(), password.text.toString())
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is BaseEvent.ShowToast -> showToast(it.message)
                is AuthEvent.OnAuthError -> showToast("Wrong nickname or password")
                is AuthEvent.OnAuthSuccess -> fragmentListener.setPrefs(binding.nickname.text.toString())
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }
}