package com.gulshan.nagarnigam.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gulshan.nagarnigam.data.MenuObj
import com.gulshan.nagarnigam.databinding.ItemMenuLayoutBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    lateinit var binding: ItemMenuLayoutBinding


    var onItemClickListener: ((MenuObj) -> Unit)? = null

    private val differCallBack = object : DiffUtil.ItemCallback<MenuObj>() {
        override fun areItemsTheSame(oldItem: MenuObj, newItem: MenuObj): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: MenuObj, newItem: MenuObj): Boolean {
            return false
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    inner class ViewHolder(private val binding: ItemMenuLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuObj) {
            binding.buttonButton.text = item.name
            binding.buttonButton.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemMenuLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    companion object {
        val menuList = listOf(MenuObj("shikayat"), MenuObj("kachra"), MenuObj("city"))
    }
}