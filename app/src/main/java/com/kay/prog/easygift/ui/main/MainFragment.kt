package com.kay.prog.easygift.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kay.prog.easygift.databinding.FragmentMainBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
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
                onClick(it)
            }

            swipeRefresh.setOnRefreshListener {
                vm.getUsers()
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
                else -> showToast("Something went wrong")
            }
        }
    }

    private fun onClick(index: Int) {
        vm.getUserByIndex(index).let {
//            fragmentListener.openFragment(DetailsFragment.newInstance(it.id))
            showToast(it.toString())
        }
    }

    override fun showLoading() {

    }
}