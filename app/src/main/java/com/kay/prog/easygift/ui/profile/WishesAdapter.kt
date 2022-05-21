package com.kay.prog.easygift.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.easygift.data.models.WishEntity
import com.kay.prog.easygift.databinding.ItemWishBinding

class WishesAdapter : RecyclerView.Adapter<WishesAdapter.ViewHolder>() {
    private var list: List<WishEntity> = listOf()

    fun setData(list: List<WishEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding : ItemWishBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(wish: WishEntity) {
            binding.apply {
                txt.text = wish.description
                webpage.text = wish.webpage
            }
        }
    }
}