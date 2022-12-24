package com.gulshan.nagarnigam.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gulshan.nagarnigam.R
import com.gulshan.nagarnigam.data.MenuObj
import com.gulshan.nagarnigam.data.model.ShikayatItemObj

class ShikayatAdapter : RecyclerView.Adapter<ShikayatAdapter.RvViewHolder>() {

    var onItemClickListener: ((ShikayatItemObj) -> Unit)? = null


    private val differCallBack = object : DiffUtil.ItemCallback<ShikayatItemObj>() {
        override fun areItemsTheSame(
            oldItem: ShikayatItemObj,
            newItem: ShikayatItemObj
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: ShikayatItemObj,
            newItem: ShikayatItemObj
        ): Boolean {
            return false
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class RvViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_shikayat, parent, false)) {
        private var tvTitle: TextView? = null
        private var backGround: ConstraintLayout? = null
        private var iconIv: ImageView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tv_name)
            backGround = itemView.findViewById(R.id.parent)
            iconIv = itemView.findViewById(R.id.iv_icon)
        }

        fun bind(itemObj: ShikayatItemObj) {

            backGround?.setOnClickListener {
                onItemClickListener?.invoke(itemObj)
            }
            tvTitle?.text = itemObj.name
            if (itemObj.isCategory) {
                iconIv?.visibility = View.VISIBLE
            } else {
                iconIv?.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RvViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return calculateTotalItems()
    }

    private fun calculateTotalItems(): Int {
        return differ.currentList.size
    }

    companion object {
        val shikayatList = listOf(
            ShikayatItemObj("Street Animals Issues", true),
            ShikayatItemObj("Stray Animals ( Cow, Ox , Buffalo)", false),
            ShikayatItemObj("Stray Animals ( Pig, Dog)", false),
            ShikayatItemObj("Stray and Pet dog Poop Problem in vicinity", false),
            ShikayatItemObj("Electricity issues", true),
            ShikayatItemObj("No proper lighting in Sulab Shochalaya", false),
            ShikayatItemObj("Street light not working", false),
            ShikayatItemObj("Water issues", true),
            ShikayatItemObj("Problem in water supply", false),
            ShikayatItemObj("Pipeline leakage/wreckage", false),
            ShikayatItemObj("Contaminated water", false),
            ShikayatItemObj("Sewage and drainage issues", true),
            ShikayatItemObj("Improper waste/ water disposal at Shochalaya", false),
            ShikayatItemObj("Open manhole, sewage and drainage", false),
            ShikayatItemObj("Public health related issues", true),
            ShikayatItemObj("Dead Animals ( Dog, cat, cow and small animals)", false),
            ShikayatItemObj("Garbage disposal bin extraction", false),
            ShikayatItemObj("Garbage heap problem", false),
            ShikayatItemObj("No garbage van facility", false),
            ShikayatItemObj("Cleaning of road", false),
            ShikayatItemObj("No water supply in Public toilets", false),
            ShikayatItemObj("Unclean public toilets"),
            ShikayatItemObj("Imporper sanitation facility"),
            ShikayatItemObj("Cleaning open drainage system"),
            ShikayatItemObj("Non-coperation of garbage pick up ")
        )
    }
}