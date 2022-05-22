package com.kay.prog.easygift.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.easygift.R
import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.databinding.ItemWishBinding

class WishesAdapter : RecyclerView.Adapter<WishesAdapter.ViewHolder>() {
    private var list: List<Wish> = listOf()

    fun setData(list: List<Wish>) {
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

        fun bind(wish: Wish) {
            binding.apply {
                txt.text = wish.description

                if (wish.webpage == null)
                {
                    webpage.visibility = View.GONE
                }
                else {
                    webpage.text = wish.webpage
                }

                if (wish.price == null)
                {
                    price.visibility = View.GONE
                }
                else {
                    price.text = itemView.context.getString(R.string.price, wish.price)
                }
            }
        }
    }
}