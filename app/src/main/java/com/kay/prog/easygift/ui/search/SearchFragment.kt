package com.kay.prog.easygift.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kay.prog.easygift.R
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.databinding.FragmentSearchBinding
import com.kay.prog.easygift.extensions.toUserEntity
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
import com.kay.prog.easygift.ui.detail.DetailFragment
import com.kay.prog.easygift.ui.mylist.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: BaseFragment<SearchVM, FragmentSearchBinding>(
    SearchVM::class.java,
    {
        FragmentSearchBinding.inflate(it)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            usersAdapter = UsersAdapter {
                fragmentListener.openFragment(DetailFragment.newInstance(it.id ?: 1L))
            }

            recycler.adapter = usersAdapter

            searchBtn.setOnClickListener {
                vm.findUser(searchTxt.text.toString())
            }
        }
    }

    private fun subscribeToLiveData() {
        vm.users.observe(viewLifecycleOwner) { users ->
            val list = mutableListOf<UserEntity>()
            users.forEach {
                list.add(it.toUserEntity())
            }
            usersAdapter.setData(list)
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