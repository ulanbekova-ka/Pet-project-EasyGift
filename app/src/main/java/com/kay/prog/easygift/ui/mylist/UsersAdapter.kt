package com.kay.prog.easygift.ui.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kay.prog.easygift.R
import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.databinding.ItemUserBinding
import com.kay.prog.easygift.extensions.countDaysLeft

class UsersAdapter(
    private val click: (user: User) -> Unit
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var list: List<User> = listOf()

    fun setData(list: List<User>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding : ItemUserBinding,
        private val click: (user: User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                val icon = user.avatar ?: ContextCompat.getDrawable(itemView.context, R.drawable.ic_avatar)
                Glide.with(itemView.context).load(icon).into(avatar)
                name.text = user.nickname
                birthday.text = itemView.context.getString(R.string.left, countDaysLeft(user.birthday))
            }

            itemView.setOnClickListener {
                click.invoke(user)
            }
        }
    }
}