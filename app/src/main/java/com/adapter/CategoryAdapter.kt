package com.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidhardviewlesson10.RecyclerView_Fragment
import com.example.androidhardviewlesson10.ui.HomeFragment
import com.model.Category

class CategoryAdapter(val imageList: ArrayList<Category>, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun createFragment(position: Int): Fragment {

        return RecyclerView_Fragment.newInstance(imageList[position])
    }
}