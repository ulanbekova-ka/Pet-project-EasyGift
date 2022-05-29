package com.kay.prog.easygift.ui.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentRegistrationBinding
import com.kay.prog.easygift.ui.base.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment: BaseFragment<RegistrationVM, FragmentRegistrationBinding>(
    RegistrationVM::class.java,
    {
        FragmentRegistrationBinding.inflate(it)
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
            regBtn.setOnClickListener {
                checkInput(nameTxt.text.toString(),
                    surnameTxt.text.toString(),
                    nicknameTxt.text.toString(),
                    emailTxt.text.toString(),
                    birthdayTxt.text.toString(),
                    passwordTxt.text.toString())
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is RegEvent.OnTakenNickname -> binding.nickname.helperText = getString(R.string.taken_nickname_error)
                is RegEvent.OnSuccess -> fragmentListener.setPrefs(binding.nicknameTxt.text.toString())
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }

    private fun checkInput(nameTxt: String?, surnameTxt: String?, nicknameTxt: String?, emailTxt: String?, birthdayTxt: String?, passwordTxt: String?) {
        binding.apply {
            when {
                nameTxt.isNullOrEmpty() -> {
                    name.helperText = getString(R.string.empty_error)
                }
                surnameTxt.isNullOrEmpty() -> {
                    surname.helperText = getString(R.string.empty_error)
                }
                nicknameTxt.isNullOrEmpty() -> {
                    nickname.helperText = getString(R.string.empty_error)
                }
                emailTxt.isNullOrEmpty() -> {
                    email.helperText = getString(R.string.empty_error)
                }
                birthdayTxt.isNullOrEmpty() -> {
                    birthday.helperText = getString(R.string.empty_error)
                }
                passwordTxt.isNullOrEmpty() -> {
                    password.helperText = getString(R.string.empty_error)
                }
                !birthdayTxt.matches(Regex("(^(((0[1-9]|1[0-9]|2[0-8])[/](0[1-9]|1[012]))|((29|30|31)[/](0[13578]|1[02]))|((29|30)[/](0[4,6,9]|11)))[/](19|[2-9][0-9])\\d\\d\$)|(^29[/]02[/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)\$)")) -> {
                    birthday.helperText = getString(R.string.birthday_format_mismatch)
                }
                !passwordTxt.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}\$")) -> {
                    password.helperText = getString(R.string.password_format_mismatch)
                }
                else -> {
                    vm.checkNickname(nameTxt, surnameTxt, nicknameTxt, emailTxt, birthdayTxt, passwordTxt)
                }
            }
        }
    }
}