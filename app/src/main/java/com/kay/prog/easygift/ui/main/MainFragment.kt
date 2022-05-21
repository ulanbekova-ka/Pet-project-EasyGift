package com.kay.prog.easygift.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentMainBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
import com.kay.prog.easygift.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainVM, FragmentMainBinding>(
    MainVM::class.java,
    {
        FragmentMainBinding.inflate(it)
    }
) {

    private lateinit var fragmentListener: FragmentListener

    private lateinit var usersAdapter: StarredUsersAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentListener = context as FragmentListener
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            usersAdapter = StarredUsersAdapter {
                fragmentListener.openFragment(ProfileFragment.newInstance(it.id!!))
            }

            recycler.adapter = usersAdapter

            swipeRefresh.setOnRefreshListener {
                vm.downloadUsers()
            }
        }
    }

    private fun subscribeToLiveData(){
        vm.users.observe(viewLifecycleOwner) {
            usersAdapter.setData(it)
        }

        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingEvent.ShowToast -> showToast(getString(it.resId))
                is LoadingEvent.ShowLoading -> binding.swipeRefresh.isRefreshing = true
                is LoadingEvent.StopLoading -> binding.swipeRefresh.isRefreshing = false
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }
}