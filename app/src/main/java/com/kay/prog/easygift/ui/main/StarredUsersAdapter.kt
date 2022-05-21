package com.kay.prog.easygift.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kay.prog.easygift.R
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.databinding.ItemStarredUserBinding

class StarredUsersAdapter(
    private val click: (user: UserEntity) -> Unit
) : RecyclerView.Adapter<StarredUsersAdapter.ViewHolder>() {

    private var list: List<UserEntity> = listOf()

    fun setData(list: List<UserEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStarredUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding : ItemStarredUserBinding,
        private val click: (user: UserEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserEntity) {
            binding.apply {
                val icon = user.avatar ?: ContextCompat.getDrawable(itemView.context, R.drawable.ic_avatar)
                Glide.with(itemView.context).load(icon).into(avatar)
                name.text = user.nickname
                birthday.text = user.birthday
            }

            itemView.setOnClickListener {
                click.invoke(user)
            }
        }
    }
}