package com.kay.prog.easygift.ui.mylist

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentMylistBinding
import com.kay.prog.easygift.extensions.countDaysLeft
import com.kay.prog.easygift.ui.base.BaseFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import com.kay.prog.easygift.ui.base.LoadingEvent
import com.kay.prog.easygift.ui.detail.DetailFragment
import com.kay.prog.easygift.ui.main.MainActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        with(binding) {
            usersAdapter = UsersAdapter {
                fragmentListener.openFragment(DetailFragment.newInstance(it.nickname), false)
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

            it.forEach { user ->
                if (countDaysLeft(user.birthday) == 10) {
                    createNotification()
                }
            }
        }

        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingEvent.ShowLoading -> binding.swipeRefresh.isRefreshing = true
                is LoadingEvent.StopLoading -> binding.swipeRefresh.isRefreshing = false
                else -> Log.e("DEBUG", getString(R.string.unknown_error))
            }
        }
    }

    private fun createNotification() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)

        val builder = NotificationCompat.Builder(requireContext(), "Channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Напоминание")
            .setContentText("Подготовьте подарки!")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(0, builder.build())
        }
    }
}