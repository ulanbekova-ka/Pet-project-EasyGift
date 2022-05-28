package com.kay.prog.easygift.ui.mylist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentMylistBinding
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
import com.kay.prog.easygift.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MylistFragment: BaseFragment<MylistVM, FragmentMylistBinding>(
    MylistVM::class.java,
    {
        FragmentMylistBinding.inflate(it)
    }
) {

    private lateinit var usersAdapter: UsersAdapter
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
            usersAdapter = UsersAdapter {
                fragmentListener.openFragment(DetailFragment.newInstance(it.nickname))
            }

            recycler.adapter = usersAdapter

            swipeRefresh.setOnRefreshListener {
                vm.downloadUsers()
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.users.observe(viewLifecycleOwner) {
            usersAdapter.setData(it)
        }

        vm.user.observe(viewLifecycleOwner) {
            vm.downloadUsers()
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