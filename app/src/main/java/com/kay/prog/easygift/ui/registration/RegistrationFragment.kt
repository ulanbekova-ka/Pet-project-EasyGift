package com.kay.prog.easygift.ui.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentRegistrationBinding
import com.kay.prog.easygift.extensions.showToast
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
                vm.saveUser(
                    name.text.toString(),
                    surname.text.toString(),
                    nickname.text.toString(),
                    email.text.toString(),
                    birthday.text.toString(),
                    password.text.toString()
                )
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is RegEvent.OnEmptyFields -> showToast("Заполните все поля")
                is RegEvent.OnIncorrectPassword -> showToast("Пароль должен состоять из более чем 8 знаков, заглавной и прописной букв, чисел")
                is RegEvent.OnIncorrectBirthdayFormat -> showToast("Формат даты - дд/мм/гггг (используйте '/')")
                is RegEvent.OnTakenNickname -> showToast("Пользователь с таким ником существует. Введите другой")
                is RegEvent.OnRegSuccess -> fragmentListener.setPrefs(binding.nickname.text.toString())
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }
}