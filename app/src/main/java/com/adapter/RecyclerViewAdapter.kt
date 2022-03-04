package com.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhardviewlesson10.databinding.ItemRecyclerViewBinding
import com.model.Category
import com.model.ImageAbout
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(var list: ArrayList<ImageAbout>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    lateinit var adapterListener: OnMyItemClickListener

    fun interface OnMyItemClickListener {
        fun onClick(image: ImageAbout)
    }

    fun setOnMyItemClickListener(listener: OnMyItemClickListener) {
        adapterListener = listener
    }

    inner class MyViewHolder(val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(image: ImageAbout) {
            Picasso.get().load(image.image).into(binding.recyclerViewImage)
            binding.root.setOnClickListener {
                adapterListener.onClick(image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}