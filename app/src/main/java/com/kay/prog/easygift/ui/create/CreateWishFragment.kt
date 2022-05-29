package com.kay.prog.easygift.ui.create

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentCreateWishBinding
import com.kay.prog.easygift.ui.base.*
import com.kay.prog.easygift.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWishFragment: BaseFragment<CreateWishVM, FragmentCreateWishBinding>(
    CreateWishVM::class.java,
    {
        FragmentCreateWishBinding.inflate(it)
    }
) {

    private lateinit var fragmentListener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentListener = context as FragmentListener
        } catch (e: Exception) { print("Activity must implement FragmentListener")}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setNickname(fragmentListener.getPrefs())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            saveBtn.setOnClickListener {
                checkInput(
                    descriptionTxt.text.toString(), urlTxt.text.toString(), priceTxt.text.toString()
                )
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is RegEvent.OnSuccess -> {
                    fragmentListener.openFragment(ProfileFragment(), false)
                }
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }

    private fun checkInput(descriptionTxt: String?, urlTxt: String?, priceTxt: String?) {
        binding.apply {
            when {
                descriptionTxt.isNullOrEmpty() -> {
                    description.helperText = getString(R.string.empty_error)
                }
                else -> {
                    vm.saveWish(descriptionTxt, urlTxt, priceTxt)
                }
            }
        }
    }
}