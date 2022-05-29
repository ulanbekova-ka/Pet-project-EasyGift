package com.kay.prog.easygift.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentProfileBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
import com.kay.prog.easygift.ui.detail.WishesAdapter
import com.kay.prog.easygift.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<ProfileVM, FragmentProfileBinding>(
    ProfileVM::class.java,
    {
        FragmentProfileBinding.inflate(it)
    }
) {

    private lateinit var wishesAdapter: WishesAdapter
    private lateinit var fragmentListener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentListener = context as FragmentListener
        } catch (e: Exception) { print("Activity must implement FragmentListener")}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.setNickname(fragmentListener.getPrefs())

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            wishesAdapter = WishesAdapter()

            recycler.adapter = wishesAdapter

            swipeRefresh.setOnRefreshListener {
                vm.getWishes()
                vm.getUser()
            }

            changeBtn.setOnClickListener {
                showToast("Изменить профиль")
            }

            settingsBtn.setOnClickListener {
                showToast("Перейти в настройки")
            }

            logOutBtn.setOnClickListener {
                fragmentListener.openFragment(MainFragment(), false)
                fragmentListener.deletePrefs()
                vm.deleteUsers()
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.user.observe(viewLifecycleOwner) {
            with(binding) {
                Glide.with(requireContext()).load(it.avatar?: R.drawable.ic_avatar).into(profAvatar)
                nickname.text = it.nickname
                birthday.text = it.birthday
                fullName.text = getString(R.string.full_name, it.name, it.surname)
            }
        }

        vm.wishList.observe(viewLifecycleOwner) {
            wishesAdapter.setData(it)
        }

        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingEvent.ShowLoading -> binding.swipeRefresh.isRefreshing = true
                is LoadingEvent.StopLoading -> binding.swipeRefresh.isRefreshing = false
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }
}