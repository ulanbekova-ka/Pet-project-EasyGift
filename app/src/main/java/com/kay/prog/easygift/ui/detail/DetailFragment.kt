package com.kay.prog.easygift.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentDetailBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment< DetailVM, FragmentDetailBinding>(
    DetailVM::class.java,
    {
        FragmentDetailBinding.inflate(it)
    }
) {

    private lateinit var adapter: WishesAdapter
    private lateinit var fragmentListener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentListener = context as FragmentListener
        } catch (e: Exception) { print("Activity must implement FragmentListener")}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setNickname(arguments?.getString(KEY_NICK))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            adapter = WishesAdapter()

            recycler.adapter = adapter

            swipeRefresh.setOnRefreshListener {
                vm.getWishes()
                vm.getUser()
            }

            subscribeBtn.setOnClickListener {
                // TODO
                showToast("Подписка прошла успешно")
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
            adapter.setData(it)
        }

        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingEvent.ShowLoading -> binding.swipeRefresh.isRefreshing = true
                is LoadingEvent.StopLoading -> binding.swipeRefresh.isRefreshing = false
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }

    // TODO Delete - get by id
    companion object {
        private const val KEY_NICK = "nick"

        fun newInstance(nickname: String): DetailFragment {
            val args = Bundle().apply { putString(KEY_NICK, nickname) }
            return DetailFragment().apply { arguments = args }
        }
    }
}